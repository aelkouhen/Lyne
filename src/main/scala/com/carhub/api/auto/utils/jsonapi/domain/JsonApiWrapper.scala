package com.carhub.api.auto.utils.jsonapi.domain

/**
  * Wrapper of Json api object to add included data.
  */
case class JsonApiWrapper[T](data: T, toIncludes: List[String] = Nil) {

}
