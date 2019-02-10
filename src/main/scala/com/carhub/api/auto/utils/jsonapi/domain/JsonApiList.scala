package com.carhub.api.auto.utils.jsonapi.domain

import java.util

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include

import scala.beans.BeanProperty
import scala.collection.JavaConverters._
import scala.language.higherKinds

object JsonApiList {

  def apply[T](data: List[T]): JsonApiList[T] = {
    new JsonApiList(data)
  }

  def apply[X[_], T, Y](data: X[T], request: Y)(implicit ev: CanBePaginated[X], ev2: CanBeQueryInfo[Y]): JsonApiList[T] = {
    new JsonApiList[T](ev.toPage(data), ev2.toQueryInfo(request))
  }
}

class JsonApiList[T] {

  @BeanProperty
  @JsonInclude(Include.NON_NULL)
  var meta: JsonApiPaginationMeta = _

  @BeanProperty
  var data: java.util.List[T] = new util.ArrayList[T]()

  @BeanProperty
  @JsonInclude(Include.NON_NULL)
  var links: JsonApiPaginationLinks = _

  private def this(data: List[T]) {
    this()
    this.data = data.asJava
  }

  private def this(page: Page[T], queryInfo: QueryInfo) {
    this()
    this.data = page.content.asJava
    this.meta = new JsonApiPaginationMeta(page)
    this.links = new JsonApiPaginationLinks(this.meta, queryInfo.requestURL, queryInfo.queryString)
  }

  override def equals(other: Any): Boolean = other match {
    case other: JsonApiList[T] => canEquals(other) && hashCode() == other.hashCode()
    case _ => false
  }

  override def hashCode(): Int = data.hashCode()

  def canEquals(other: Any): Boolean = {
    other.isInstanceOf[JsonApiList[T]]
  }
}
