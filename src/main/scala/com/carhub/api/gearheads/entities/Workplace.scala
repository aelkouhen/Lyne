package com.carhub.api.gearheads.entities

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
  @JsonApiId
  var id: Long = _

  @BeanProperty
  @Column(name = "company")
  var companyName: String = _

  @BeanProperty
  @Column(name = "description")
  var description: String = _

  @BeanProperty
  @Column(name = "position")
  var position: String = _

  @BeanProperty
  @Column(name = "start_date")
  @Temporal(TemporalType.DATE)
  var startDate: Date = _

  @BeanProperty
  @Column(name = "location")
  @OneToOne
  var location: Location = _



  override def toString = s"Workplace ($position in $companyName)"

}
