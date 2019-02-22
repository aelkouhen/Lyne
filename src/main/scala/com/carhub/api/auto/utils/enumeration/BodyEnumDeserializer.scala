package com.carhub.api.auto.utils.enumeration

import com.carhub.api.auto.domain.Body
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.{DeserializationContext, JsonDeserializer}


class BodyEnumDeserializer extends JsonDeserializer[Body.Body]{

  override def deserialize(jsonParser : JsonParser, context: DeserializationContext): Body.Body = {
    Body.valueOf(jsonParser.getText)
  }
}


