package com.carhub.api.auto.domain

import java.util
import java.util.Date

import com.carhub.api.auto.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import com.fasterxml.jackson.annotation.JsonIgnore
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
  var oldName:String  = _

  @BeanProperty
  @Column(length = 1000)
  var about:String  = _

  @BeanProperty
  var headquarterLocation: String  = _

  @BeanProperty
  @Temporal(TemporalType.DATE)
  var foundationDate: Date = _

  @BeanProperty
  var closed: Boolean = _

  @BeanProperty
  @OneToOne(cascade = Array(CascadeType.REMOVE))
  @JsonIgnore
  var logo: Photo = _

  @BeanProperty
  var founder:String = _

  //The series of the model.
  @BeanProperty
  @JsonIgnore
  @OneToMany(mappedBy = "make", fetch = FetchType.LAZY, cascade = Array(CascadeType.ALL))
  var models: util.List[Model] = new util.ArrayList[Model]()
}
