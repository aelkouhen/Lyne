package com.carhub.api.gearheads.model.files

import java.util

import com.carhub.api.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import javax.persistence._

import scala.beans.BeanProperty

@Entity
@Table(name = "photo")
@JsonApi(apiType = "photo")
class Photo extends File with Serializable {

  @Lob
  @BeanProperty
  var smallContent: Array[Byte] = _

  @BeanProperty
  @OneToMany(mappedBy = "photo")
  var tags: util.List[PhotoTag] = _
}
