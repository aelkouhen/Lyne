package com.carhub.api.auto.domain

import java.io.Serializable
import java.util.Date

import com.carhub.api.auto.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence._
import org.hibernate.validator.constraints.URL

import scala.beans.BeanProperty

@Entity
@JsonApi(apiType= "resource")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="resource_type")
abstract class Resource extends Serializable {

  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  @JsonApiId
  var id: Long = _

  @BeanProperty
  var name: String = _

  @BeanProperty
  var caption: String = _

  @BeanProperty
  var size: Long = _

  @BeanProperty
  var mimeType: String = _

  @BeanProperty
  var format: String = _

  @BeanProperty
  @Column(name = "CREATION_TIME")
  @Temporal(TemporalType.TIMESTAMP)
  var created: Date = _

  @BeanProperty
  @URL
  var url: String = _

  @Lob
  @BeanProperty
  @JsonIgnore
  var content: Array[Byte] = _
}
