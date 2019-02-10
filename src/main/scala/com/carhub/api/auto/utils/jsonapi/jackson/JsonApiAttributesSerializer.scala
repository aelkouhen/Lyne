package com.carhub.api.auto.utils.jsonapi.jackson

import com.fasterxml.jackson.databind.ser.BeanSerializer
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
import com.carhub.api.auto.utils.jsonapi.domain.JsonApiDataBean

import scala.collection.JavaConverters._

object JsonApiAttributesSerializer {

  private def findFieldToExclude(jsonApiDataBean: JsonApiDataBean): Set[String] = {
    jsonApiDataBean.relationshipsByName.keys.toSet ++
    jsonApiDataBean.metaFieldByName.keys.toSet +
    jsonApiDataBean.idJsonFieldName.getOrElse("")
  }
}

class JsonApiAttributesSerializer(defaultSerializer: BeanSerializerBase, jsonApiDataBean: JsonApiDataBean)
  extends BeanSerializer(defaultSerializer, JsonApiAttributesSerializer.findFieldToExclude(jsonApiDataBean).asJava) {

}
