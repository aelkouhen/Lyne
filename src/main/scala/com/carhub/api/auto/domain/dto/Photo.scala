package com.carhub.api.auto.domain.dto

import scala.beans.BeanProperty

class Photo extends Resource {

  @BeanProperty
  var (width, height) = (0, 0)

}
