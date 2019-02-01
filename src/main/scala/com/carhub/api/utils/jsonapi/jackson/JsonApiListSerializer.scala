package com.carhub.api.utils.jsonapi.jackson

import com.carhub.api.utils.jsonapi.JsonApiHelper
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.carhub.api.utils.jsonapi.domain.{JsonApiDataBean, JsonApiList}

import scala.collection.JavaConverters._

class JsonApiListSerializer() extends StdSerializer[JsonApiList[_]](classOf[JsonApiList[_]]) {

  def serializeWithInclude(value: JsonApiList[_], gen: JsonGenerator, provider: SerializerProvider, includes: List[String]): Unit = {
    gen.writeStartObject()

    serializeWithoutIncludes(value, gen, provider)

    val includedResources = includes
    if (includedResources.nonEmpty && !value.data.isEmpty) {
      val jsonApiDataBean = JsonApiDataBean(value.data.get(0).getClass())
      val relationshipList = jsonApiDataBean.relationshipsByName.values
                                                                .filter(bean => includedResources.contains(bean.name))

      val included = for {
        bean <- value.data.asScala
        relationship <- relationshipList
      } yield
        if (relationship.isCollection)
          JsonApiHelper.toIterable(jsonApiDataBean.getFieldValue(relationship.field, bean))
        else
          Option.option2Iterable(Option(jsonApiDataBean.getFieldValue(relationship.field, bean)))

      provider.defaultSerializeField("included", included.toSet.flatten, gen)
    }

    gen.writeEndObject()
  }

  def serializeWithoutIncludes(value: JsonApiList[_], gen: JsonGenerator, provider: SerializerProvider): Unit = {
    provider.defaultSerializeField("data", value.data, gen)
    if (Option(value.meta).isDefined) {
      provider.defaultSerializeField("meta", value.meta, gen)
    }
    if (Option(value.links).isDefined) {
      provider.defaultSerializeField("links", value.links, gen)
    }
  }

  override def serialize(value: JsonApiList[_], gen: JsonGenerator, provider: SerializerProvider): Unit = {
    gen.writeStartObject()

    serializeWithoutIncludes(value, gen, provider)

    gen.writeEndObject()
  }
}
