package com.carhub.api.auto.domain

import java.util
import java.util.UUID

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence._
import org.hibernate.annotations.{GenericGenerator, Type}

import scala.beans.BeanProperty

@Entity
@Table(name = "serie")
class Serie extends Serializable {

  @Id
  @BeanProperty
  @Column(name = "ID")
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Type(`type` = "uuid-char")
  var id: UUID = _

  @BeanProperty
  var name:String  = _

  //The model of the serie.
  @BeanProperty
  @JsonIgnore
  @OneToOne
  var model: Model = _

  //The vehicles of the Serie.
  @BeanProperty
  @JsonIgnore
  @OneToMany(mappedBy = "serie", fetch = FetchType.LAZY, cascade = Array(CascadeType.ALL))
  var vehicles: util.List[Vehicle] = new util.ArrayList[Vehicle]()

  //Begin year of production
  @BeanProperty
  var productionStartYear: Int = _

  //End year of production
  @BeanProperty
  var productionEndYear: Int = _
}
