package com.carhub.api.gearheads.utils.jsonapi.jackson

import com.fasterxml.jackson.databind.{BeanDescription, JsonSerializer, SerializationConfig}
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
import com.carhub.api.gearheads.utils.jsonapi.annotations.JsonApi
import com.carhub.api.gearheads.utils.jsonapi.domain.{JsonApiList, JsonApiWrapper}

class JsonApiSerializerModifier(jsonApiRelationshipLink: JsonApiRelationshipLinkContext) extends BeanSerializerModifier {

  override def modifySerializer(config: SerializationConfig, beanDesc: BeanDescription, serializer: JsonSerializer[_]): JsonSerializer[_] = {
    if (classOf[JsonApiWrapper[_]].isAssignableFrom(beanDesc.getBeanClass)) {
      new JsonApiWrapperSerializer()
    } else if (classOf[JsonApiList[_]].isAssignableFrom(beanDesc.getBeanClass)) {
      new JsonApiListSerializer()
    } else {
      serializer match {
        case jsonApiSerializer: JsonApiSerializer => jsonApiSerializer
        case _ => createCustomSerializer(beanDesc, serializer, jsonApiRelationshipLink)
      }
    }
  }

  private def createCustomSerializer(beanDesc: BeanDescription, serializer: JsonSerializer[_], jsonApiLinkContext: JsonApiRelationshipLinkContext): JsonSerializer[_] = {
    def annotation = Option(beanDesc.getBeanClass().getAnnotation(classOf[JsonApi]))

    annotation match {
      case Some(_) => new JsonApiSerializer(beanDesc.getBeanClass().asInstanceOf[Class[Any]], serializer.asInstanceOf[BeanSerializerBase], jsonApiLinkContext)
      case None => serializer
    }
  }

}
