package com.carhub.api.gearheads.domain.files

import java.lang.Long
import java.util.Date

import com.carhub.api.gearheads.domain.Gearhead
import com.carhub.api.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import javax.persistence._
import org.hibernate.validator.constraints.URL

import scala.beans.BeanProperty

@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Entity
@Table(name = "file")
@JsonApi(apiType = "file")
class File extends Serializable {

  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.TABLE)
  @Column(name = "ID")
  @JsonApiId
  var id: Long = _

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
  var content: Array[Byte] = _

  @BeanProperty
  @OneToOne
  var owner: Gearhead = _
}
