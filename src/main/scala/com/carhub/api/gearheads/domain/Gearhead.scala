package com.carhub.api.gearheads.domain

import java.io.Serializable
import java.lang.Long
import java.util
import java.util.Date

import com.carhub.api.gearheads.domain.activities.{EventMembership, Message, Relationship, TopicMembership}
import com.carhub.api.gearheads.domain.locations.{Location, Workplace}
import com.carhub.api.gearheads.domain.files.{Photo, PhotoAlbum}
import com.carhub.api.utils.enumeration.{EnumValue, EnumValueType}
import com.carhub.api.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import javax.persistence._
import org.hibernate.annotations.Type

import scala.beans.BeanProperty

object Gender extends Enumeration with EnumValue{
  val UNSPECIFIED, MALE, FEMALE = Value
}

class GenderType extends EnumValueType(Gender){}

@Entity
@Table(name = "gearhead")
@JsonApi(apiType = "gearhead")
class Gearhead extends Serializable{

  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  @JsonApiId
  var id: Long = _

  @BeanProperty
  var aboutMe: String = _

  @BeanProperty
  var firstName: String = _

  @BeanProperty
  var lastName: String = _

  @BeanProperty
  @Temporal(TemporalType.DATE)
  var birthDay: Date = _

  @BeanProperty
  var username: String = _

  @BeanProperty
  var email: String = _

  @BeanProperty
  var password: String = _

  @BeanProperty
  @Temporal(TemporalType.TIMESTAMP)
  var creationTime: Date = _

  @BeanProperty
  @Column(name = "LAST_CONNEXION")
  @Temporal(TemporalType.TIMESTAMP)
  var updateTime: Date = _

  @BeanProperty
  var enabled: Boolean = _

  @BeanProperty
  @Type(`type` = "com.carhub.api.gearheads.domain.GenderType")
  var gender: Gender.Value = _

  @BeanProperty
  @OneToOne
  var hometownLocation: Location = _

  @BeanProperty
  @OneToOne
  var currentLocation: Location = _

  @BeanProperty
  @OneToOne
  var workplace: Workplace = _

  @BeanProperty
  @OneToOne
  var profilePhoto: Photo = _

  @BeanProperty
  @OneToOne
  var coverPhoto: Photo = _

  @OneToMany(mappedBy = "gearhead")
  var photoAlbums: util.List[PhotoAlbum] = _

  @BeanProperty
  @OneToMany(mappedBy = "gearhead")
  var relationship: util.List[Relationship] = _

  @BeanProperty
  @OneToMany(mappedBy = "gearhead")
  var topicMemberships: util.List[TopicMembership] = _

  @BeanProperty
  @OneToMany(mappedBy = "gearhead")
  var eventMemberships: util.List[EventMembership] = _

  @BeanProperty
  @OneToMany
  var sentMessages: util.List[Message] = _

  @BeanProperty
  @OneToMany
  var receivedMessages: util.List[Message] = _


  override def toString = s"Gearhead($username)"
}
