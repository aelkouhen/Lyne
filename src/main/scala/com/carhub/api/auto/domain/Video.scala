package com.carhub.api.auto.domain

import com.carhub.api.auto.utils.jsonapi.annotations.JsonApi
import javax.persistence._

import scala.beans.BeanProperty

@Entity
@Table(name = "video")
@JsonApi(apiType = "video")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("VIDEO")
class Video extends Resource with Serializable {

  @BeanProperty
  var (width, height) = (0, 0)

  @BeanProperty
  var definition: String = _
}