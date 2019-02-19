package com.carhub.api.auto.domain

import java.io.Serializable
import java.util.Date

import com.carhub.api.auto.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence._
import org.hibernate.validator.constraints.URL

import scala.beans.BeanProperty

@Entity
@JsonApi(apiType= "abstract_file")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="file_type")
abstract class AbstractFile extends Serializable {

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
  var extension: String = _

  @BeanProperty
  @Column(name = "CREATION_TIME")
  @Temporal(TemporalType.TIMESTAMP)
  var created: Date = _

  @BeanProperty
  @URL
  var link: String = _

  @Lob
  @BeanProperty
  @JsonIgnore
  var content: Array[Byte] = _
}
