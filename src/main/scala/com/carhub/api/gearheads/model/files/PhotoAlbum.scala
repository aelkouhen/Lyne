package com.carhub.api.gearheads.model.files

import java.lang.Long
import java.util
import java.util.Date

import com.carhub.api.gearheads.model.Gearhead
import com.carhub.api.gearheads.model.locations.Location
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
  @Column(name = "NAME")
  var name: String = _

  @BeanProperty
  @OneToOne
  var coverPhoto: Photo = _

  @BeanProperty
  @Column(name = "DESCRIPTION")
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
  var photos: util.List[Photo] = _

  @BeanProperty
  @ManyToOne
  var gearhead: Gearhead = _

}
