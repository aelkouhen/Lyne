package com.carhub.api.gearheads.model.activities

import java.lang.Long

import com.carhub.api.gearheads.model.Gearhead
import com.carhub.api.utils.enumeration.{EnumValue, EnumValueType}
import com.carhub.api.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import javax.persistence._
import org.hibernate.annotations.Type

import scala.beans.BeanProperty

object UserRole extends Enumeration with EnumValue{
  val ADMIN, MODERATOR, MEMBER, FOLLOWER, VOTER = Value
}

class UserRoleType extends EnumValueType(UserRole){}

@Entity
@Table(name = "topic_membership")
@JsonApi(apiType = "topic_membership")
class TopicMembership extends Serializable {

  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  @JsonApiId
  var id: Long = _

  @Type(`type` = "com.carhub.api.gearheads.model.activities.UserRoleType")
  @Column(name = "USER_ROLE")
  var userType: UserRole.Value = _

  @BeanProperty
  @ManyToOne
  var gearhead: Gearhead = _

  @BeanProperty
  @OneToOne
  var topic: Topic = _
}
