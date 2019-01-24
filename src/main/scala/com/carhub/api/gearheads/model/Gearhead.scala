package com.carhub.api.gearheads.model

import java.io.Serializable
import java.lang.Long
import java.util
import java.util.Date

import com.carhub.api.gearheads.model.activities.{EventMembership, Relationship, TopicMembership}
import com.carhub.api.gearheads.model.locations.{Location, Workplace}
import com.carhub.api.gearheads.model.files.{Photo, PhotoAlbum}
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
  @Column(name = "ABOUT_ME")
  var aboutMe: String = _

  @BeanProperty
  @Column(name = "FIRST_NAME")
  var firstName: String = _

  @BeanProperty
  @Column(name = "LAST_NAME")
  var lastName: String = _

  @BeanProperty
  @Column(name = "BIRTHDAY")
  @Temporal(TemporalType.DATE)
  var birthDay: Date = _

  @BeanProperty
  @Column(name = "USERNAME")
  var username: String = _

  @BeanProperty
  @Column(name = "EMAIL")
  var email: String = _

  @BeanProperty
  @Column(name = "PASSWORD")
  var password: String = _

  @BeanProperty
  @Column(name = "CREATION_TIME")
  @Temporal(TemporalType.TIMESTAMP)
  var creationTime: Date = _

  @BeanProperty
  @Column(name = "LAST_CONNEXION")
  @Temporal(TemporalType.TIMESTAMP)
  var updateTime: Date = _

  @BeanProperty
  @Column(name = "ENABLED")
  var enabled: Boolean = _

  @Type(`type` = "com.carhub.api.gearheads.model.GenderType")
  @Column(name = "GENDER")
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

  @OneToOne
  var profilePhoto: Photo = _

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

  override def toString = s"Gearhead($username)"

}
