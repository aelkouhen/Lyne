package com.carhub.api.auto.utils.enumeration

import com.carhub.api.auto.domain.Body
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.{JsonSerializer, SerializerProvider}


class BodyEnumSerializer extends JsonSerializer[Body.Body]{

  override def serialize(value: Body.Body, gen : JsonGenerator, provider: SerializerProvider)= {
    gen.writeStartObject
    gen.writeStringField("body", value.toString);
    gen.writeEndObject();
  }
}


