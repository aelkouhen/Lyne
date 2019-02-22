package com.carhub.api.auto.utils.enumeration

import com.carhub.api.auto.domain.Body
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer


class BodyEnumDeserializer extends StdDeserializer[Body.Body](classOf[Body.Body]){

  override def deserialize(jsonParser : JsonParser, context: DeserializationContext): Body.Body = {
    Body.valueOf(jsonParser.getText)
  }
}


