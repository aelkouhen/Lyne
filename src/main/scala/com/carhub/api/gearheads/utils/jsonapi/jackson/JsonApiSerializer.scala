package com.carhub.api.gearheads.utils.jsonapi.jackson

import java.util

import com.carhub.api.gearheads.utils.jsonapi.JsonApiHelper
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.BeanSerializer
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
import com.carhub.api.gearheads.utils.jsonapi.domain.{JsonApiDataBean, JsonApiRelationShipDataBean}


class JsonApiSerializer(clazz: Class[Any], defaultSerializer: BeanSerializerBase, jsonApiRelationshipLink: JsonApiRelationshipLinkContext) extends BeanSerializer(defaultSerializer) {

  val jsonApiDataBean = JsonApiDataBean(clazz)

  val attributeSerializer = new JsonApiAttributesSerializer(defaultSerializer, jsonApiDataBean)

  def this() {
    this(None.orNull, None.orNull, new JsonApiRelationshipLinkContext(Map.empty))
  }

  override def serializeFields(value: Any, gen: JsonGenerator, provider: SerializerProvider): Unit = {
    val isOnArray = Option(gen.getOutputContext.getParent).forall(_.inArray())

    val relationships = relationshipsWithValues(value)

    serializeWithoutInclude(value, gen, provider, relationships, isOnArray)
  }

  def serializeWithInclude(value: Any, gen: JsonGenerator, provider: SerializerProvider, includes: List[String]): Unit = {
    val isOnArray = gen.getOutputContext.inArray()
    gen.writeStartObject()

    val relationships = relationshipsWithValues(value)

    serializeWithoutInclude(value, gen, provider, relationships, isOnArray)
    if (!isOnArray) {
      serializeIncludes(gen, relationships, includes)
    }

    gen.writeEndObject()
  }

  def serializeWithoutInclude(dataBeanObject: Any, gen: JsonGenerator, provider: SerializerProvider, relationships: Map[JsonApiRelationShipDataBean, AnyRef], isOnArray: Boolean): Unit = {
    if (!isOnArray) {
      gen.writeObjectFieldStart("data")
    }
    jsonApiDataBean.getId(dataBeanObject)
        .foreach(gen.writeObjectField("id", _))

    gen.writeStringField("type", jsonApiDataBean.jsonApiType)
    gen.writeFieldName("attributes")
    attributeSerializer.serialize(dataBeanObject, gen, provider)

    serializeRelationships(dataBeanObject, gen, provider, relationships)

    serializeMetaData(gen, dataBeanObject)

    if (!isOnArray) {
      gen.writeEndObject()
    }
  }

  def serializeMetaData(gen: JsonGenerator, dataBeanObject: Any): Unit = {
    val isFieldValueEmpty = (value: Any) => value match {
      case None | null => true
      case t:Traversable[_] => t.isEmpty
      case c: util.Collection[_] => c.isEmpty
      case m: util.Map[_, _] => m.isEmpty
      case _ => false
    }

    val fieldValueByFieldName = jsonApiDataBean.metaFieldByName.map(entry => {
        entry._1 -> jsonApiDataBean.getFieldValue(entry._2, dataBeanObject)
      }).filter(entry => !isFieldValueEmpty(entry._2))

    if(fieldValueByFieldName.nonEmpty) {
      gen.writeObjectFieldStart("meta")

      fieldValueByFieldName.foreach(entry => {
        gen.writeObjectField(entry._1, entry._2)
      })

      gen.writeEndObject()
    }
  }

  private def relationshipsWithValues(bean: Any): Map[JsonApiRelationShipDataBean, AnyRef] = {
    jsonApiDataBean.relationshipsByName.values
      .map(rlDataBean => rlDataBean -> JsonApiHelper.extractOptionalValue(jsonApiDataBean.getFieldValue(rlDataBean.field, bean)))
      .filter({case (_, relation) => Option(relation).isDefined})
      .toMap
  }

  def serializeRelationships(value: Any, gen: JsonGenerator, provider: SerializerProvider, valuedRelationship: Map[JsonApiRelationShipDataBean, AnyRef]): Unit = {

    if (valuedRelationship.nonEmpty) {
      gen.writeObjectFieldStart("relationships")

      valuedRelationship.foreach({case (relationshipDataBean, relationshipValue) => {
        gen.writeObjectFieldStart(relationshipDataBean.name)
        val relationshipJsonApiDataBean = relationshipDataBean.jsonApiDataBean
        if (!relationshipDataBean.isCollection) {
          serializeRelationshipLink(gen, relationshipJsonApiDataBean, relationshipValue)
        }
        gen.writeFieldName("data")


        if (relationshipDataBean.isCollection) {
          gen.writeStartArray()
          val value = JsonApiHelper.toIterable(relationshipValue)

          for {item <- value} {
            gen.writeStartObject()
            gen.writeObjectField("id", relationshipJsonApiDataBean.getId(item))
            gen.writeStringField("type", relationshipJsonApiDataBean.jsonApiType)
            gen.writeEndObject()
          }

          gen.writeEndArray()
        } else {
          gen.writeStartObject()
          gen.writeObjectField("id", relationshipJsonApiDataBean.getId(relationshipValue))
          gen.writeStringField("type", relationshipJsonApiDataBean.jsonApiType)
          gen.writeEndObject()
        }

        gen.writeEndObject()
      }})
      gen.writeEndObject()
    }
  }

  private def serializeRelationshipLink(jsonGenerator: JsonGenerator, jsonApiDataBean: JsonApiDataBean, relationshipValue: AnyRef) = {
    val link = jsonApiRelationshipLink.createResourceUrl(jsonApiDataBean.jsonApiType, jsonApiDataBean.getId(relationshipValue))
    if (link.isDefined) {
      jsonGenerator.writeFieldName("links")
      jsonGenerator.writeStartObject()
      jsonGenerator.writeObjectField("related", link)
      jsonGenerator.writeEndObject()
    }
  }

  def serializeIncludes(gen: JsonGenerator, relationships: Map[JsonApiRelationShipDataBean, AnyRef], includedResources: List[String]): Unit = {
    val includes = relationships.flatMap({case (relationshipDataBean, relationshipValue) => {
      if (includedResources.contains(relationshipDataBean.name)) {
        if (relationshipDataBean.isCollection) {
          JsonApiHelper.toIterable(relationshipValue)
        } else {
          Some(relationshipValue)
        }
      } else {
        None
      }
    }}).toSet

    if (includes.nonEmpty) {
      gen.writeArrayFieldStart("included")
      includes.foreach(gen.writeObject)
      gen.writeEndArray()
    }
  }

}
