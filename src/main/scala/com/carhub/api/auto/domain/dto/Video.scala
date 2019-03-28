package com.carhub.api.auto.domain.dto

import scala.beans.BeanProperty

class Video extends Resource{

  @BeanProperty
  var (width, height) = (0, 0)

  @BeanProperty
  var definition: String = _


}
