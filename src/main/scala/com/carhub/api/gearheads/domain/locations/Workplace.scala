package com.carhub.api.gearheads.domain.locations

import java.io.Serializable
import java.lang.Long
import java.util.Date

import com.carhub.api.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import javax.persistence._

import scala.beans.BeanProperty


@Entity
@Table(name = "workplace")
@JsonApi(apiType = "workplace")
class Workplace extends Serializable{

  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  @JsonApiId
  var id: Long = _

  @BeanProperty
  @Column(name = "COMPANY")
  var companyName: String = _

  @BeanProperty
  var description: String = _

  @BeanProperty
  var position: String = _

  @BeanProperty
  @Temporal(TemporalType.DATE)
  var startDate: Date = _

  @BeanProperty
  @OneToOne
  var location: Location = _

  override def toString = s"Workplace ($position in $companyName)"

}
