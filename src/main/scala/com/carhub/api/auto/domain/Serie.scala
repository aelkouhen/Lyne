package com.carhub.api.auto.domain

import java.util

import com.carhub.api.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import javax.persistence._

import scala.beans.BeanProperty

@Entity
@Table(name = "serie")
@JsonApi(apiType = "serie")
class Serie extends Serializable {

  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  @JsonApiId
  var id: Long = _

  @BeanProperty
  var name:String  = _

  //The model of the serie.
  @BeanProperty
  @OneToOne
  var model: Model = _

  //The vehicles of the Serie.
  @BeanProperty
  @OneToMany(mappedBy = "serie")
  var vehicles: util.List[Vehicle] = new util.ArrayList[Vehicle]()

  //Begin year of production
  @BeanProperty
  var productionStartYear: Int = _

  //End year of production
  @BeanProperty
  var productionEndYear: Int = _
}
