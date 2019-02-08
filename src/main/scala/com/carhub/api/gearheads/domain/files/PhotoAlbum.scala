package com.carhub.api.gearheads.domain.files

import java.lang.Long
import java.util
import java.util.Date

import com.carhub.api.gearheads.domain.Gearhead
import com.carhub.api.gearheads.domain.locations.Location
import com.carhub.api.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import javax.persistence._

import scala.beans.BeanProperty

@Entity
@Table(name = "photo_album")
@JsonApi(apiType = "photo_album")
class PhotoAlbum extends Serializable {

  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  @JsonApiId
  var albumId: Long = _

  @BeanProperty
  var name: String = _

  @BeanProperty
  @OneToOne
  var coverPhoto: Photo = _

  @BeanProperty
  var description: String = _

  @BeanProperty
  @Column(name = "CREATION_TIME")
  @Temporal(TemporalType.TIMESTAMP)
  var created: Date = _

  @BeanProperty
  @Column(name = "MODIFICATION_TIME")
  @Temporal(TemporalType.TIMESTAMP)
  var modified: Date = _

  @BeanProperty
  @OneToOne
  var location: Location = _

  @BeanProperty
  @OneToMany
  var photos: util.List[Photo] = new util.ArrayList[Photo]()

  @BeanProperty
  @ManyToOne
  var gearhead: Gearhead = _
}
