package com.carhub.api.auto.domain

import java.util
import java.util.{Date, UUID}

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence._
import org.hibernate.annotations.{GenericGenerator, Type}

import scala.beans.BeanProperty

@Entity
@Table(name = "make")
class Make extends Serializable {

  @Id
  @BeanProperty
  @Column(name = "ID")
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Type(`type` = "uuid-char")
  var id: UUID = _

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
  var logoId: UUID = _

  @BeanProperty
  var founder: String = _

  //The series of the model.
  @BeanProperty
  @JsonIgnore
  @OneToMany(mappedBy = "make", fetch = FetchType.LAZY, cascade = Array(CascadeType.ALL))
  var models: util.List[Model] = new util.ArrayList[Model]()
}
