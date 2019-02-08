package com.carhub.api.gearheads.domain.activities

import java.lang.Long
import java.util.Date

import com.carhub.api.gearheads.domain.Gearhead
import com.carhub.api.gearheads.domain.files.Photo
import com.carhub.api.utils.jsonapi.annotations.JsonApiId
import javax.persistence._

import scala.beans.BeanProperty


@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
abstract class Topic extends Serializable{

  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.TABLE)
  @Column(name = "ID")
  @JsonApiId
  var id: Long = _

  @BeanProperty
  @OneToOne
  var coverPhoto: Photo = _

  @BeanProperty
  @OneToOne
  var logo: Photo = _

  @BeanProperty
  @Temporal(TemporalType.TIMESTAMP)
  var creationTime: Date = _

  @BeanProperty
  @OneToOne
  var creator: Gearhead = _

  @BeanProperty
  @Temporal(TemporalType.TIMESTAMP)
  var updateTime: Date = _
}
