package com.carhub.api.gearheads.domain.files

import java.lang.Long
import java.util

import com.carhub.api.auto.domain.Car
import com.carhub.api.gearheads.domain.Gearhead
import com.carhub.api.gearheads.domain.locations.Location
import com.carhub.api.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import javax.persistence._

import scala.beans.BeanProperty

@Entity
@Table(name = "photo_tag")
@JsonApi(apiType = "photo_tag")
class PhotoTag extends Serializable {

  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  @JsonApiId
  var tagId: Long = _

  @BeanProperty
  @OneToOne
  var location: Location = _

  @BeanProperty
  @OneToMany
  var taggedUsers: util.List[Gearhead] = new util.ArrayList[Gearhead]()

  @BeanProperty
  @ManyToOne
  var photo: Photo = _

  @BeanProperty
  @OneToOne
  var taggedCar: Car = _
}
