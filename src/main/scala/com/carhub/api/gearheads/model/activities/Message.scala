package com.carhub.api.gearheads.model.activities

import java.lang.Long
import java.util._

import com.carhub.api.gearheads.model.Gearhead
import com.carhub.api.gearheads.model.files.File
import com.carhub.api.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import javax.persistence._

import scala.beans.BeanProperty

@Entity
@Table(name = "message")
@JsonApi(apiType = "message")
class Message extends Serializable {

  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  @JsonApiId
  var id: Long = _

  @BeanProperty
  var subject: String = _

  @BeanProperty
  var body: String = _

  @BeanProperty
  @OneToOne
  var repliedToMessage: Message = _

  @BeanProperty
  @OneToOne
  var transferredMessage: Message = _

  @BeanProperty
  @Temporal(TemporalType.TIMESTAMP)
  var creationTime: Date = _

  @BeanProperty
  @OneToOne
  var creator: Gearhead = _

  @BeanProperty
  @OneToMany(mappedBy = "message")
  var recipients: List[MessageRecipient] = _

  @Lob
  @BeanProperty
  @OneToMany
  var uploadedFiles: List[File] = _
}
