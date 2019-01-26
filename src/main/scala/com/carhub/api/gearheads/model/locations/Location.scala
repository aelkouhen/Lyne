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
  var latitude: BigDecimal = _

  @BeanProperty
  var longitude: BigDecimal = _

  @BeanProperty
  var street: String = _

  @BeanProperty
  var city: String = _

  @BeanProperty
  var country: String = _

  @BeanProperty
  var state: String = _

  @BeanProperty
  var zipCode: String = _


  override def toString = s"Location ($street, $city, $state, $country)"

}
