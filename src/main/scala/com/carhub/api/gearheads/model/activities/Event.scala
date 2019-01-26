package com.carhub.api.gearheads.model.activities

import java.lang.Long
import java.util.Date

import com.carhub.api.gearheads.model.Gearhead
import com.carhub.api.gearheads.model.locations.Location
import com.carhub.api.gearheads.model.files.Photo
import com.carhub.api.gearheads.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import javax.persistence._

import scala.beans.BeanProperty

@Entity
@Table(name = "event")
@JsonApi(apiType = "event")
class Event extends Serializable {

  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  @JsonApiId
  var id: Long = _

  @BeanProperty
  var name: String = _

  @BeanProperty
  var description: String = _

  /*Event Type*/

  @BeanProperty
  @OneToOne
  var coverPhoto: Photo = _

  @BeanProperty
  @Temporal(TemporalType.TIMESTAMP)
  var creationTime: Date = _

  @BeanProperty
  @Temporal(TemporalType.TIMESTAMP)
  var startTime: Date = _

  @BeanProperty
  @Temporal(TemporalType.TIMESTAMP)
  var endTime: Date = _

  @BeanProperty
  @OneToOne
  var creator: Gearhead = _

  @BeanProperty
  @Temporal(TemporalType.TIMESTAMP)
  var updateTime: Date = _

  @BeanProperty
  @OneToOne
  var location: Location = _

}
