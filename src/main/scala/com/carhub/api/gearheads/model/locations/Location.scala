package com.carhub.api.gearheads.model.locations

import java.io.Serializable
import java.lang.Long

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
  @Column(name = "ID")
  var id: Long = _

  @BeanProperty
  @Column(name = "LATITUDE")
  var latitude: BigDecimal = _

  @BeanProperty
  @Column(name = "LONGITUDE")
  var longitude: BigDecimal = _

  @BeanProperty
  @Column(name = "STREET")
  var street: String = _

  @BeanProperty
  @Column(name = "CITY")
  var city: String = _

  @BeanProperty
  @Column(name = "COUNTRY")
  var country: String = _

  @BeanProperty
  @Column(name = "STATE")
  var state: String = _

  @BeanProperty
  @Column(name = "ZIP_CODE")
  var zipCode: String = _


  override def toString = s"Location ($street, $city, $state, $country)"

}
