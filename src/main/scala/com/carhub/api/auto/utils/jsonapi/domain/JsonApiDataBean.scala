package com.carhub.api.auto.utils.jsonapi.domain

import java.lang.reflect.{Field, Method}

import com.fasterxml.jackson.annotation.JsonProperty
import com.carhub.api.auto.utils.jsonapi.annotations.{JsonApi, JsonApiId, JsonApiMeta, JsonApiRelationShip}
import com.typesafe.scalalogging.LazyLogging

import scala.collection._
import scala.util.Try

object JsonApiDataBean extends LazyLogging {
  private val dataBeans: mutable.Map[Class[_], JsonApiDataBean] = mutable.Map()

  def apply(dataBeanClass: Class[_]): JsonApiDataBean = {
    dataBeans.getOrElseUpdate(dataBeanClass, new JsonApiDataBean(dataBeanClass))
  }

  def isJsonApiClass(clazz: Class[_]): Boolean = {
    Option(clazz.getAnnotation(classOf[JsonApi])).isDefined
  }
}

class JsonApiDataBean private(val dataBeanClass: Class[_]) {

  lazy val relationshipsByName: Map[String, JsonApiRelationShipDataBean] = dataBeanClass.getDeclaredFields
    .filter(field => Option(field.getAnnotation(classOf[JsonApiRelationShip])).isDefined)
    .map(new JsonApiRelationShipDataBean(_))
    .map(rl => rl.name -> rl)
    .toMap
  val jsonApiType: String = Option(dataBeanClass.getAnnotation(classOf[JsonApi])) match {
    case Some(field) => field.apiType()
    case None => throw new RuntimeException("class " + dataBeanClass + " must be annotated with @JsonApi")
  }

  val idField: Option[Field] = dataBeanClass.getDeclaredFields
    .find(field => Option(field.getAnnotation(classOf[JsonApiId])).isDefined)
  val idJsonFieldName: Option[String] = idField.flatMap(field => Option(field.getAnnotation(classOf[JsonProperty]))).map(_.value()).orElse(idField.map(_.getName))

  val metaFieldByName: Map[String, Field] = dataBeanClass.getDeclaredFields
    .filter(field => Option(field.getAnnotation(classOf[JsonApiMeta])).isDefined)
    .map(field => {
      val jsonPropertyAnnotation = Option(field.getAnnotation(classOf[JsonProperty]))
      val fieldName = jsonPropertyAnnotation.map(_.value).getOrElse(field.getName)
      fieldName -> field
    })
    .toMap

  def shouldFieldPresentOnAttributes(jsonFieldName: String): Boolean = {
    (!relationshipsByName.contains(jsonFieldName)) &&
      (!idJsonFieldName.contains(jsonFieldName)) &&
      (!metaFieldByName.contains(jsonFieldName))
  }

  def getRelationship(name: String): Option[JsonApiRelationShipDataBean] = relationshipsByName.get(name)

  def updateId(dataBeanObject: Any, value: Any): Unit = this.idField match {
    case Some(field) => updateFieldValue(field, dataBeanObject, value)
    case None => throw new RuntimeException("class " + dataBeanClass + " must have an @JsonApiId")
  }

  def updateFieldValue(field: Field, dataBeanObject: Any, value: Any): Unit = {
    getScalaSetter(field).orElse(getJavaSetter(field)) match {
      case None => field.set(dataBeanObject, value)
      case Some(setter) => setter.invoke(dataBeanObject, value.asInstanceOf[Object])
    }
  }

  private def getScalaSetter(field: Field): Option[Method] = {
    val fieldName = field.getName()
    val setterName = fieldName + "_$eq"

    Try(dataBeanClass.getDeclaredMethod(setterName, field.getType())).toOption
  }

  private def getJavaSetter(field: Field): Option[Method] = {
    val fieldName = field.getName()
    val setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)

    Try(dataBeanClass.getDeclaredMethod(setterName, field.getType())).toOption
  }

  def getId(dataBeanObject: Any): Option[Any] = this.idField.flatMap(field => Option(getFieldValue(field, dataBeanObject)))

  def getFieldValue(field: Field, dataBeanObject: Any): AnyRef = {
    getScalaGetter(field).orElse(getJavaGetter(field)) match {
      case None => field.get(dataBeanObject)
      case Some(getter) => getter.invoke(dataBeanObject)
    }
  }

  private def getJavaGetter(field: Field): Option[Method] = {
    val fieldName = field.getName()
    val getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)

    Try(dataBeanClass.getDeclaredMethod(getterName)).toOption
  }

  private def getScalaGetter(field: Field): Option[Method] = {
    val fieldName = field.getName()

    Try(dataBeanClass.getDeclaredMethod(fieldName)).toOption
  }
}
