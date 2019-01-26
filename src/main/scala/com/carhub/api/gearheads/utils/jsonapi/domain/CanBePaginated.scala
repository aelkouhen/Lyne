package com.carhub.api.gearheads.utils.jsonapi.domain

import scala.language.higherKinds

trait CanBePaginated[P[_]] {
  def toPage[T](something: P[T]): Page[T]
}
