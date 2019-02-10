package com.carhub.api.auto.utils.jsonapi.jackson

import java.lang.reflect.Field
import java.util.Optional

import com.fasterxml.jackson.core.{JsonParser, JsonToken}
import com.fasterxml.jackson.databind.{DeserializationContext, JavaType, JsonDeserializer}
import com.carhub.api.auto.utils.jsonapi.domain.JsonApiRelationShipDataBean

import scala.collection.JavaConverters._
import scala.collection.immutable.Traversable
import scala.collection.mutable

class JsonApiRelationShipDeserializer(relationship: JsonApiRelationShipDataBean) extends JsonDeserializer[Any]() {

  type IdType = AnyRef
  type RelationshipType = Any
  val beanClass = relationship.jsonApiDataBean.dataBeanClass
  val beanConstructor: (IdType) => RelationshipType =
    try {
      relationship.jsonApiDataBean.idField match {
        case Some(field) =>
          val constructor = beanClass.getConstructor(field.getType)
          (idValue: AnyRef) => constructor.newInstance(idValue)
        case None => throw new RuntimeException("class " + relationship.jsonApiDataBean.dataBeanClass + " must have an @JsonApiId")
      }
    } catch {
      case _: NoSuchMethodException =>
        (idValue: AnyRef) => {
          val dataBean = beanClass.newInstance()
          relationship.jsonApiDataBean.updateId(dataBean, idValue)
          dataBean
        }
    }

  override def deserialize(p: JsonParser, ctxt: DeserializationContext): Any = {
    if ("data" != p.nextFieldName()) {
      throw ctxt.mappingException(s"missing data field on ${relationship.name} relationship")
    }

    p.nextToken() // eat start data object
    if (p.isExpectedStartArrayToken) {
      if (!relationship.isCollection) {
        throw ctxt.mappingException(s"${relationship.name} is not an array")
      }
      val relationships = new java.util.ArrayList[RelationshipType]()
      while (p.nextToken() != JsonToken.END_ARRAY) {
        val relationshipValue = deserializeOneRelationship(p, ctxt)
        relationships.add(relationshipValue)
      }
      p.nextToken() // eat of object
      toListObject(relationships)
    } else if (p.isExpectedStartObjectToken) {
      if (relationship.isCollection) {
        throw ctxt.mappingException(s"${relationship.name} is an array")
      }
      val relationshipValue = deserializeOneRelationship(p, ctxt)
      p.nextToken() // eat end of object
      if (relationship.isOption) {
        toOptionObject(relationshipValue)
      } else {
        relationshipValue
      }
    } else if (JsonToken.VALUE_NULL == p.currentToken()) {
      getNullValue(ctxt)
    } else {
        throw ctxt.mappingException(s"data field should be an object with (type and id properties)")
    }
  }

  def deserializeOneRelationship(p: JsonParser, ctxt: DeserializationContext): RelationshipType = {
    val relationshipJsonApiDataBean = relationship.jsonApiDataBean
    var relationshipType: String = None.orNull
    var optionalId: Option[IdType] = None

    while (p.nextToken() != JsonToken.END_OBJECT) {
      if ("type" == p.getCurrentName) {
        relationshipType = p.nextTextValue()
      } else if ("id" == p.getCurrentName) {
        optionalId = relationshipJsonApiDataBean.idField match {
          case Some(field) => deserializeId(p, ctxt, field)
          case None => throw new RuntimeException("class " + relationship.jsonApiDataBean.dataBeanClass + " must have an @JsonApiId")
        }
      } else {
        p.skipChildren()
      }
    }

    if (Option(relationshipType).isEmpty) {
      throw ctxt.mappingException(s"missing type field on relationship ${relationship.name}")
    }
    if (optionalId.isEmpty) {
      throw ctxt.mappingException(s"missing id field on relationship ${relationship.name}")
    }

    if (!relationshipType.equals(relationshipJsonApiDataBean.jsonApiType)) {
      throw ctxt.mappingException(s"Type of relationship ${relationship.name} is not consistent with the expected type [${relationshipJsonApiDataBean.jsonApiType}]")
    }

    try {
      beanConstructor(optionalId.get)
    } catch {
      case e: InstantiationException =>
        throw ctxt.instantiationException(
          relationshipJsonApiDataBean.dataBeanClass,
          s"Error while creation relationship ${relationship.name} unable to find empty constructor or constructor with only id parameter"
        )
    }
  }

  private def deserializeId(p: JsonParser, ctxt: DeserializationContext, idField: Field): Option[AnyRef] = {
    p.nextToken()
    val jacksonType = ctxt.getTypeFactory().constructSimpleType(idField.getType(), Array[JavaType]())
    val deserializer = ctxt.findRootValueDeserializer(jacksonType)

    if (p.getCurrentToken == JsonToken.VALUE_NULL) {
      None
    } else {
      Some(deserializer.deserialize(p, ctxt))
    }
  }

  private def toListObject(iterator: java.util.List[RelationshipType]): Any = {
    val field = relationship.field
    if (classOf[java.util.List[_]].isAssignableFrom(field.getType)) {
      iterator
    } else if (classOf[Traversable[_]].isAssignableFrom(field.getType)) {
      iterator.asScala.to[List]
    } else if (classOf[mutable.Traversable[_]].isAssignableFrom(field.getType)) {
      iterator.asScala
    } else {
      throw new RuntimeException("only java.util.List and scala List can be used for relationship")
    }
  }

  private def toOptionObject(value: RelationshipType): AnyRef = {
    val fieldType = relationship.field.getType
    if (fieldType.isAssignableFrom(classOf[Option[AnyRef]])) {
      Option(value)
    } else if (fieldType.isAssignableFrom(classOf[Optional[AnyRef]])) {
      Optional.of(value)
    } else {
      throw new RuntimeException("only scala.Option and java.util.Optional can be used for optional relationship")
    }
  }

  override def getNullValue(ctxt: DeserializationContext): AnyRef = {
    if (relationship.isOption) {
      toOptionObject(null.asInstanceOf[RelationshipType])
    } else {
      null
    }
  }
}
