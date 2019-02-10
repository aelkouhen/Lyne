package com.carhub.api.auto.utils.jsonapi.domain

import scala.beans.BeanProperty

class JsonApiPaginationMeta() {

  @BeanProperty
  var totalPages: Int = _
  @BeanProperty
  var totalElements: Long = _
  @BeanProperty
  var pageNumber: Int = _
  @BeanProperty
  var pageSize: Int = _

  def this(page: Page[_] = None.orNull) = {
    this()
    totalPages = page.totalPages
    totalElements = page.totalElements
    pageNumber = page.number
    pageSize = page.size
  }
}
