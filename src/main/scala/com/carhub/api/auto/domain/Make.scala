package com.carhub.api.auto.domain

import java.util
import java.util.Date

import com.carhub.api.gearheads.domain.files.Photo
import com.carhub.api.gearheads.domain.locations.Location
import com.carhub.api.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import javax.persistence._

import scala.beans.BeanProperty

@Entity
@Table(name = "make")
@JsonApi(apiType = "make")
class Make extends Serializable {

  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  @JsonApiId
  var id: Long = _

  @BeanProperty
  var name:String  = _

  @BeanProperty
  var about:String  = _

  @BeanProperty
  @OneToOne
  var headquarterLocation:Location  = _

  @BeanProperty
  @Temporal(TemporalType.DATE)
  var foundationDate: Date = _

  @BeanProperty
  var isClosed: Boolean = _

  @BeanProperty
  @OneToOne
  var logo:Photo = _

  @BeanProperty
  var founder:String = _

  //The series of the model.
  @BeanProperty
  @OneToMany(mappedBy = "make")
  var models: util.List[Model] = _
}
