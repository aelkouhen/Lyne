package com.carhub.api.auto.domain

import java.util
import java.util.{Date, UUID}

import com.carhub.api.auto.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence._
import org.hibernate.annotations.{GenericGenerator, Type}

import scala.beans.BeanProperty

@Entity
@Table(name = "model")
@JsonApi(apiType = "model")
class Model extends Serializable {

  @Id
  @BeanProperty
  @Column(name = "ID")
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @JsonApiId
  @Type(`type` = "uuid-char")
  var id: UUID = _

  @BeanProperty
  var name:String  = _

  @BeanProperty
  @Temporal(TemporalType.DATE)
  var creationDate: Date = _

  @BeanProperty
  var generation:String  = _

  //The series of the model.
  @BeanProperty
  @JsonIgnore
  @OneToMany(mappedBy = "model", fetch = FetchType.LAZY, cascade = Array(CascadeType.ALL))
  var series: util.List[Serie] = new util.ArrayList[Serie]()

  //The Make of the model.
  @BeanProperty
  @JsonIgnore
  @OneToOne
  var make: Make = _
}
