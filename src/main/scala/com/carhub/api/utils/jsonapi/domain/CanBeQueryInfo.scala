package com.carhub.api.utils.jsonapi.domain

trait CanBeQueryInfo[P] {
  def toQueryInfo(something: P): QueryInfo
}
