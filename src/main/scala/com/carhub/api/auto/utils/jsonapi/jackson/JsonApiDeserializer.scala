package com.carhub.api.auto.utils.jsonapi.jackson

import com.fasterxml.jackson.core.{JsonParser, JsonToken, JsonTokenId}
import com.fasterxml.jackson.databind._
import com.fasterxml.jackson.databind.deser.impl.{PropertyBasedCreator, PropertyValueBuffer}
import com.fasterxml.jackson.databind.deser.{BeanDeserializer, BeanDeserializerBase}
import com.carhub.api.auto.utils.jsonapi.domain.JsonApiDataBean
import com.carhub.api.auto.utils.jsonapi.exception.InconsistentIdException

import scala.util.Try

class JsonApiDeserializer(clazz: Class[_], defaultDeserializer: BeanDeserializerBase) extends BeanDeserializer(defaultDeserializer) {

  _vanillaProcessing = false

  val jsonApiDataBean = JsonApiDataBean(clazz)

  override def deserializeFromObject(p: JsonParser, ctxt: DeserializationContext): AnyRef = {
    if (_nonStandardCreation) {
      deserializeFromObjectUsingNonDefault(p, ctxt)
    } else {
      val bean = _valueInstantiator.createUsingDefault(ctxt)
      deserialize(p, ctxt, bean)
    }
  }

  override def deserializeFromObjectUsingNonDefault(p: JsonParser, ctxt: DeserializationContext): AnyRef = {
    val creator: PropertyBasedCreator = _propertyBasedCreator
    val buffer: PropertyValueBuffer = creator.startBuilding(p, ctxt, _objectIdReader)

    abstractParsing(
      p,
      ctxt,
      idParsing = () => {
        jsonApiDataBean.idJsonFieldName match {
          case Some(name) => deserializePropertyAndBuffered(p, ctxt, buffer, creator, name)
          case None => throw ctxt.mappingException("You can't define an ID field in data.")
        }
      },
      attributeParsing = () => parseAttributesForCaseClass(p, ctxt, buffer, creator),
      relationshipsParsing = () => parseAttributesForCaseClass(p, ctxt, buffer, creator),
      metaParsing = () => parseAttributesForCaseClass(p, ctxt, buffer, creator) ,
      returnBean = () => Try(creator.build(ctxt, buffer)).recover({ case e: Throwable => wrapInstantiationProblem(e, ctxt) }).get
    )
  }

  override def deserialize(p: JsonParser, ctxt: DeserializationContext, bean: AnyRef): AnyRef = {
    p.setCurrentValue(bean)

    abstractParsing(
      p,
      ctxt,
      idParsing = () => {
        jsonApiDataBean.idJsonFieldName match {
          case Some(name) => updateIdOfBean(name, p, ctxt, bean)
          case None => throw ctxt.mappingException("You can't define an ID field in data.")
        }
      },
      attributeParsing = () => defaultDeserializer.deserialize(p, ctxt, bean),
      relationshipsParsing = () => defaultDeserializer.deserialize(p, ctxt, bean),
      metaParsing = () => defaultDeserializer.deserialize(p, ctxt, bean),
      returnBean = () => bean
    )
  }

  // add is cache to avoid mutliple call of JsonApiDeserializerModifier needlessly. See https://github.com/FasterXML/jackson-databind/issues/1860
  override def isCachable(): Boolean = true

  private def updateIdOfBean(idFieldName: String, p: JsonParser, ctxt: DeserializationContext, bean: AnyRef) = {
    jsonApiDataBean.getId(bean) match {
      case None => this.findProperty(idFieldName).deserializeAndSet(p, ctxt, bean)
      case Some(idValue) if !idValue.equals(this.findProperty(idFieldName).deserialize(p, ctxt)) => throw new InconsistentIdException(p)
      case Some(_) => Unit
    }
  }

  private def parseAttributesForCaseClass(p: JsonParser, ctxt: DeserializationContext, buffer: PropertyValueBuffer, creator: PropertyBasedCreator): Unit = {
    p.nextToken()

    while (p.currentToken() == JsonToken.FIELD_NAME) {
      val propName = p.getCurrentName
      p.nextToken()
      deserializePropertyAndBuffered(p, ctxt, buffer, creator, propName)

      p.nextToken()
    }
  }

  private def deserializePropertyAndBuffered(p: JsonParser, ctxt: DeserializationContext, buffer: PropertyValueBuffer, creator: PropertyBasedCreator, propName: String) = {
    val creatorProperty = creator.findCreatorProperty(propName)
    if (Option(creatorProperty).isDefined) {
      val propertyValue = _deserializeWithErrorWrapping(p, ctxt, creatorProperty)
      buffer.assignParameter(creatorProperty, propertyValue)
    } else {
      val prop = _beanProperties.find(propName)
      if (Option(prop).isDefined) { // normal case
        try {
          buffer.bufferProperty(prop, prop.deserialize(p, ctxt))
        } catch {
          case e: Exception =>
            wrapAndThrow(e, jsonApiDataBean.dataBeanClass, propName, ctxt)
        }
      } else {
        handleUnknownProperty(p, ctxt, jsonApiDataBean.dataBeanClass, propName)
      }
    }
  }

  private def abstractParsing(
                               p: JsonParser,
                               ctxt: DeserializationContext,
                               idParsing: () => Unit,
                               attributeParsing: () => Unit,
                               relationshipsParsing: () => Unit,
                               metaParsing: () => Unit,
                               returnBean: () => AnyRef
                             ): AnyRef = {
    val isInArray = Option(p.getParsingContext.getParent).exists(_.inArray)

    checkDataPropertyPresence(p, ctxt, isInArray)

    var isTypePresent = false

    do {
      val propName = p.getCurrentName
      p.nextToken()

      propName match {
        case "id" => parseWithErrorHandling(idParsing, propName)
        case "type" => {
          isTypePresent = true
          val typeValue = p.getText()
          if (!jsonApiDataBean.jsonApiType.equals(typeValue)) {
            throw ctxt.mappingException(s"Type in the json [$typeValue] is not consistent with the expected type [${jsonApiDataBean.jsonApiType}]")
          }
        }
        case "attributes" => parseWithErrorHandling(attributeParsing, propName)
        case "relationships" => parseWithErrorHandling(relationshipsParsing, propName)
        case "meta" => parseWithErrorHandling(metaParsing, propName)
        case "data" => // data is an empty object
        case _ => handleUnknownProperty(p, ctxt, jsonApiDataBean.dataBeanClass, propName)
      }
    } while (p.nextToken() == JsonToken.FIELD_NAME)

    checkTypePresence(p, ctxt, isInArray, isTypePresent)

    returnBean()
  }

  private def parseWithErrorHandling(attributeParsing: () => Unit, propName: String) = {
    try {
      attributeParsing()
    } catch {
      case e: JsonMappingException =>
        e.prependPath(jsonApiDataBean.dataBeanClass, propName)
        e.prependPath(jsonApiDataBean.dataBeanClass, "data")
        throw e
      case e: Throwable => throw e
    }
  }

  private def checkDataPropertyPresence(p: JsonParser, ctxt: DeserializationContext, isInArray: Boolean) = {
    // bug on jackson. On some case we start reading on top of json, an other time start at property field name
    val nextFieldName = if (p.isExpectedStartObjectToken) {
      p.nextFieldName
    } else if (p.hasTokenId(JsonTokenId.ID_FIELD_NAME)) {
      p.getCurrentName
    }

    if (!isInArray) {
      if ("data" != nextFieldName) {
        throw ctxt.mappingException("missing data field")
      }
      p.nextToken() // eat start of data object
      if (!p.isExpectedStartObjectToken) {
        throw ctxt.mappingException(s"Data must be an object.")
      }
      p.nextToken() // go to first field name on data object
    }
  }

  private def checkTypePresence(p: JsonParser, ctxt: DeserializationContext, isInArray: Boolean, isTypePresent: Boolean) = {
    if (!isTypePresent) {
      throw ctxt.mappingException("missing type field")
    }
    if (!isInArray) {
      p.nextToken() // eat end of data object
    }
  }
}
