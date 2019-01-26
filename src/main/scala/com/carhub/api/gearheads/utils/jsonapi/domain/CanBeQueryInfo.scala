package com.carhub.api.gearheads.utils.jsonapi.domain

trait CanBeQueryInfo[P] {
  def toQueryInfo(something: P): QueryInfo
}
