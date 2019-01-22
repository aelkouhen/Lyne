package com.carhub.api.utils.jsonapi.domain

trait Page[T] {
  val number: Int

  val totalElements: Long

  val totalPages: Int

  val content: List[T]

  val size: Int

}
