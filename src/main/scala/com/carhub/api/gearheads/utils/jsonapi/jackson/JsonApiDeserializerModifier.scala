package com.carhub.api.gearheads.utils.jsonapi.jackson

import com.fasterxml.jackson.databind.{BeanDescription, DeserializationConfig, JsonDeserializer}
import com.fasterxml.jackson.databind.deser.{BeanDeserializerBase, BeanDeserializerBuilder, BeanDeserializerModifier}
import com.carhub.api.gearheads.utils.jsonapi.annotations.JsonApi
import com.carhub.api.gearheads.utils.jsonapi.domain.JsonApiDataBean

import scala.collection.JavaConverters._

class JsonApiDeserializerModifier extends BeanDeserializerModifier {

  override def modifyDeserializer(config: DeserializationConfig, beanDesc: BeanDescription, deserializer: JsonDeserializer[_]): JsonDeserializer[_] = {
    deserializer match {
      case jsonApiDes: JsonApiDeserializer => jsonApiDes
      case _ => createCustomDeserializer(beanDesc, deserializer)
    }
  }

  private def createCustomDeserializer(beanDesc: BeanDescription, deserializer: JsonDeserializer[_]): JsonDeserializer[_] = {
    def annotation = Option(beanDesc.getBeanClass().getAnnotation(classOf[JsonApi]))

    annotation match {
      case Some(_) => new JsonApiDeserializer(beanDesc.getBeanClass(), deserializer.asInstanceOf[BeanDeserializerBase])
      case None => deserializer
    }
  }

  override def updateBuilder(config: DeserializationConfig, beanDesc: BeanDescription, builder: BeanDeserializerBuilder): BeanDeserializerBuilder = {
    val beanClass = beanDesc.getType.getRawClass


    if (JsonApiDataBean.isJsonApiClass(beanClass)) {
      val jsonApiDataBean = JsonApiDataBean(beanClass)

      val toSerializerProperties = builder.getProperties().asScala.toList.view
                                          .map(p => (p, jsonApiDataBean.getRelationship(p.getName)))
                                          .filter(tuple => tuple._2.isDefined)
                                          .map(tuple => tuple._1.withValueDeserializer(new JsonApiRelationShipDeserializer(tuple._2.get)))

      toSerializerProperties.foreach(builder.addOrReplaceProperty(_, true))

      // if a case class is used. There are to properties but creatorProperties
      val creatorProperties = builder.getValueInstantiator.getFromObjectArguments(config)
      if (Option(creatorProperties).nonEmpty) {
        for { i <- 0 until creatorProperties.length } yield {
          val property = creatorProperties(i)
          val relationship = jsonApiDataBean.getRelationship(property.getName)
          if (relationship.isDefined) {
            val newRelationship = property.withValueDeserializer(new JsonApiRelationShipDeserializer(relationship.get))
            creatorProperties(i) = newRelationship
          }
        }
      }
    }

    builder
  }
}
