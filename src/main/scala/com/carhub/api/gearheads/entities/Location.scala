package com.carhub.api.gearheads.entities

import java.io.Serializable
import java.lang.Long
import java.util.Date

import com.carhub.api.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import javax.persistence._

import scala.beans.BeanProperty


@Entity
@Table(name = "Location")
@JsonApi(apiType = "Location")
class Location extends Serializable{

  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonApiId
  var id: Long = _

  @BeanProperty
  @Column(name = "street")
  var street: String = _

  @BeanProperty
  @Column(name = "city")
  var city: String = _

  @BeanProperty
  @Column(name = "country")
  var country: String = _

  @BeanProperty
  @Column(name = "state")
  var state: String = _

  @BeanProperty
  @Column(name = "zip_code")
  var zipCode: String = _


  override def toString = s"Location ($street, $city, $state, $country)"

}
