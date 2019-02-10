package com.carhub.api.auto.utils.jsonapi.domain

trait CanBeQueryInfo[P] {
  def toQueryInfo(something: P): QueryInfo
}
