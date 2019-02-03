package com.carhub.api.auto.domain

import java.util
import java.util.Date

import com.carhub.api.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import javax.persistence._

import scala.beans.BeanProperty

@Entity
@Table(name = "model")
@JsonApi(apiType = "model")
class Model extends Serializable {

  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  @JsonApiId
  var id: Long = _

  @BeanProperty
  var name:String  = _

  @BeanProperty
  @Temporal(TemporalType.DATE)
  var creationDate: Date = _

  @BeanProperty
  var generation:String  = _

  //The series of the model.
  @BeanProperty
  @OneToMany(mappedBy = "model")
  var series: util.List[Serie] = _

  //The Make of the model.
  @BeanProperty
  @OneToOne
  var make: Make = _
}
