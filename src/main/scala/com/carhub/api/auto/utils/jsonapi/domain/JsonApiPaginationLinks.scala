package com.carhub.api.auto.utils.jsonapi.domain

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include

import scala.beans.BeanProperty

class JsonApiPaginationLinks {

  @BeanProperty
  @JsonInclude(Include.NON_NULL)
  var self: String = _

  @BeanProperty
  @JsonInclude(Include.NON_NULL)
  var first: String = _

  @BeanProperty
  @JsonInclude(Include.NON_NULL)
  var prev: String = _

  @BeanProperty
  @JsonInclude(Include.NON_NULL)
  var next: String = _

  @BeanProperty
  @JsonInclude(Include.NON_NULL)
  var last: String = _

  def this(page: JsonApiPaginationMeta, requestURL: String, queryString: String) = {
    this()
    computeLinks(page, requestURL, queryString)
  }

  def computeLinks(page: JsonApiPaginationMeta, requestUrl: String, queryString: String): Unit = {
    val currentPage = page.pageNumber
    val currentSize = page.pageSize
    val totalPages = page.totalPages

    var query = Option(queryString).getOrElse("")

    if (!query.contains(s"${JsonApiPaginationAndSort.PAGE_NUMBER_PARAMETER}=")) {
      query = addMissingParameter(query, s"${JsonApiPaginationAndSort.PAGE_NUMBER_PARAMETER}=$currentPage")
    }

    if (!query.contains(s"${JsonApiPaginationAndSort.PAGE_SIZE_PARAMETER}=")) {
      query = addMissingParameter(query, s"${JsonApiPaginationAndSort.PAGE_SIZE_PARAMETER}=$currentSize")
    }

    self  = s"$requestUrl?$query"
    if (totalPages > 0) {
      first = s"$requestUrl?${changePageParameters(query, 1, currentSize)}"
      last  = s"$requestUrl?${changePageParameters(query, totalPages, currentSize)}"
      if (currentPage > 1) {
        val prevPageNumber = if (currentPage > totalPages) totalPages else currentPage - 1
        prev  = s"$requestUrl?${changePageParameters(query, prevPageNumber, currentSize)}"
      }
      if (currentPage < totalPages) {
        next  = s"$requestUrl?${changePageParameters(query, currentPage + 1, currentSize)}"
      }
    }

  }

  private def changePageParameters(input: String, pageNumber: Int, pageSize: Int): String = {
    input.replaceAll(s"${JsonApiPaginationAndSort.PAGE_NUMBER_PARAMETER}=\\d*", s"${JsonApiPaginationAndSort.PAGE_NUMBER_PARAMETER}=$pageNumber")
         .replaceAll(s"${JsonApiPaginationAndSort.PAGE_SIZE_PARAMETER}=\\d*", s"${JsonApiPaginationAndSort.PAGE_SIZE_PARAMETER}=$pageSize")
  }

  private def addMissingParameter(input: String, parameter: String) = {
    if (input.isEmpty) parameter else s"$input&$parameter"
  }
}
