package com.carhub.api.gearheads.model.files

import java.util

import com.carhub.api.gearheads.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import javax.persistence._

import scala.beans.BeanProperty

@Entity
@Table(name = "photo")
@JsonApi(apiType = "photo")
class Photo extends File with Serializable {

  @BeanProperty
  @OneToMany(mappedBy = "photo")
  var tags: util.List[PhotoTag] = _
}
