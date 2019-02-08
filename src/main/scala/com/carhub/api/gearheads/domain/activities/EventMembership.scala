package com.carhub.api.gearheads.domain.activities

import java.lang.Long

import com.carhub.api.gearheads.domain.Gearhead
import com.carhub.api.utils.enumeration.{EnumValue, EnumValueType}
import com.carhub.api.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import javax.persistence._
import org.hibernate.annotations.Type

import scala.beans.BeanProperty

object EventParticipationStatus extends Enumeration with EnumValue{
  val ATTENDING, DECLINED, NOT_REPLIED, UNSURE, UNSPECIFIED = Value
}

class EventParticipationType extends EnumValueType(EventParticipationStatus){}

@Entity
@Table(name = "event_membership")
@JsonApi(apiType = "event_membership")
class EventMembership extends Serializable {

  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  @JsonApiId
  var id: Long = _

  @Type(`type` = "com.carhub.api.gearheads.domain.activities.EventParticipationType")
  var status: EventParticipationStatus.Value = _

  @BeanProperty
  @OneToOne
  var event: Event = _

  @BeanProperty
  @ManyToOne
  var gearhead: Gearhead = _
}
