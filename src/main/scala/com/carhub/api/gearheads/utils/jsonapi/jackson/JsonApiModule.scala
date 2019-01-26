package com.carhub.api.gearheads.utils.jsonapi.jackson

import com.fasterxml.jackson.databind.module.SimpleModule

object JsonApiModule {

  def apply(): SimpleModule = {
    new SimpleModule()
      .setSerializerModifier(new JsonApiSerializerModifier(new JsonApiRelationshipLinkContext(Map.empty)))
      .setDeserializerModifier(new JsonApiDeserializerModifier())
  }

  def apply(jsonApiRelationshipLink: JsonApiRelationshipLinkContext): SimpleModule = {
    new SimpleModule()
      .setSerializerModifier(new JsonApiSerializerModifier(jsonApiRelationshipLink))
      .setDeserializerModifier(new JsonApiDeserializerModifier())
  }
}
