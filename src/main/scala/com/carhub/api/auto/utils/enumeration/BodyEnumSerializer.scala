package com.carhub.api.auto.utils.enumeration

import com.carhub.api.auto.domain.Body
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer


class BodyEnumSerializer extends StdSerializer[Body.Body](classOf[Body.Body]){

  override def serialize(value: Body.Body, gen : JsonGenerator, provider: SerializerProvider)= {
    gen.writeStartObject
    gen.writeStringField("body", value.toString);
    gen.writeEndObject();
  }
}


