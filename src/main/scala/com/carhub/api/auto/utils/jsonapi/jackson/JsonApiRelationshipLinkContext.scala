package com.carhub.api.auto.utils.jsonapi.jackson

import java.net.URL

import com.carhub.api.auto.utils.jsonapi.jackson.JsonApiRelationshipLinkContext.{JsonApiResourceLink, JsonApiResourceType}

class JsonApiRelationshipLinkContext(linkByJsonApiType: Map[JsonApiResourceType, JsonApiResourceLink]) {

  private val linkByJsonApiTypeFormated = linkByJsonApiType.mapValues(url =>
    if (url.endsWith("/")) {
      url
    } else {
      url + "/"
    }
  )

  def createResourceUrl(resourceType: String, resourceId: Any): Option[String] = {
    resourceId match {
      case None | null => throw new IllegalArgumentException(s"In order to create link of relation ship ['$resourceType'], we need an id.")
      case Some(id) => linkByJsonApiTypeFormated.get(resourceType).map(_ + id)
      case _ => linkByJsonApiTypeFormated.get(resourceType).map(_ + resourceId)
    }
  }
}

object JsonApiRelationshipLinkContext {
  type JsonApiResourceType = String
  type JsonApiResourceLink = String

  def apply(host: String, linkByJsonApiType: Map[JsonApiResourceType, JsonApiResourceLink]): JsonApiRelationshipLinkContext = {
    val url = new URL(host)
    apply(url, linkByJsonApiType)
  }

  def apply(host: URL, linkByJsonApiType: Map[JsonApiResourceType, JsonApiResourceLink]): JsonApiRelationshipLinkContext = {
    val linkWithHost = linkByJsonApiType
      .mapValues(new URL(host, _).toString)

    new JsonApiRelationshipLinkContext(linkWithHost)
  }
}
