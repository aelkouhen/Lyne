package com.carhub.api.gearheads.model.activities

import java.lang.Long

import com.carhub.api.gearheads.model.Gearhead
import com.carhub.api.utils.enumeration.{EnumValue, EnumValueType}
import com.carhub.api.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import javax.persistence._
import org.hibernate.annotations.Type

import scala.beans.BeanProperty

object MessageStatus extends Enumeration with EnumValue{
  val SENT, RECEIVED, READ = Value
}

class MessageStatusType extends EnumValueType(MessageStatus){}

@Entity
@Table
@JsonApi(apiType = "message-recipient")
class MessageRecipient extends Serializable {

  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  @JsonApiId
  var id: Long = _

  @BeanProperty
  @ManyToOne
  var message: Message = _

  @BeanProperty
  @OneToOne
  var recipient: Gearhead = _

  @BeanProperty
  @Type(`type` = "com.carhub.api.gearheads.model.activities.MessageStatusType")
  var status: MessageStatus.Value = _
}
