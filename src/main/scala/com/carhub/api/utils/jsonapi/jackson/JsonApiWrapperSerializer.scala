package com.carhub.api.utils.jsonapi.jackson

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.{JsonSerializer, SerializerProvider}
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.carhub.api.utils.jsonapi.domain.{JsonApiList, JsonApiWrapper}
import com.typesafe.scalalogging.LazyLogging


class JsonApiWrapperSerializer
  extends StdSerializer[JsonApiWrapper[AnyRef]](classOf[JsonApiWrapper[AnyRef]])
  with LazyLogging {

  override def serialize(wrapperValue: JsonApiWrapper[AnyRef], gen: JsonGenerator, provider: SerializerProvider): Unit = {
    val value = wrapperValue.data

    val serializer: JsonSerializer[_] = provider.findTypedValueSerializer(value.getClass, true, None.orNull).asInstanceOf[JsonSerializer[_]]

    serializer match {
      case jsonApiSerializer: JsonApiSerializer => jsonApiSerializer.serializeWithInclude(value, gen, provider, wrapperValue.toIncludes)
      case listJsonApiSerializer: JsonApiListSerializer => listJsonApiSerializer.serializeWithInclude(value.asInstanceOf[JsonApiList[_]], gen, provider, wrapperValue.toIncludes)
      case _ => logger.error("error you must use JsonApiWrapper only with JsonApi objects")
    }
  }
}
