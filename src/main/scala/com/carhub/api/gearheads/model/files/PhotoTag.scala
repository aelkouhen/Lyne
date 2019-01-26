package com.carhub.api.gearheads.model.files

import java.lang.Long
import java.util

import com.carhub.api.gearheads.model.Gearhead
import com.carhub.api.gearheads.model.locations.Location
import com.carhub.api.gearheads.utils.jsonapi.annotations.{JsonApi, JsonApiId}
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
  var taggedUsers: util.List[Gearhead] = _

  @BeanProperty
  @ManyToOne
  var photo: Photo = _

  /*
  @BeanProperty
  @OneToMany
  var taggedCars: util.List[Car] = _
  */
}
