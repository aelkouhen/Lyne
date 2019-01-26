package com.carhub.api.gearheads.utils.jsonapi.domain

import java.lang.reflect.{Field, ParameterizedType}

import com.carhub.api.gearheads.utils.jsonapi.JsonApiHelper
import com.fasterxml.jackson.annotation.JsonProperty

class JsonApiRelationShipDataBean(val field: Field) {
  val name: String = Option(field.getAnnotation(classOf[JsonProperty])).map(_.value()).filter(_.nonEmpty).getOrElse(field.getName())
  val isCollection: Boolean = JsonApiHelper.isList(field.getType())
  val isOption: Boolean = JsonApiHelper.isOption(field.getType())

  val relationshipType: Class[_] = {
    if (isCollection || isOption) {
      field.getGenericType().asInstanceOf[ParameterizedType].getActualTypeArguments()(0).asInstanceOf[Class[_]]
    } else {
      field.getType()
    }
  }

  lazy val jsonApiDataBean = JsonApiDataBean(relationshipType)
}
