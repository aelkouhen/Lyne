package com.carhub.api.gearheads.entities

import java.io.Serializable
import java.lang.Long
import java.util.Date

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
  @JsonApiId
  var id: Long = _

  @BeanProperty
  @Column(name = "about_me")
  var aboutMe: String = _

  @BeanProperty
  @Column(name = "first_name")
  var firstName: String = _

  @BeanProperty
  @Column(name = "last_name")
  var lastName: String = _

  @BeanProperty
  @Column(name = "birthday")
  @Temporal(TemporalType.DATE)
  var birthDay: Date = _

  @BeanProperty
  @Column(name = "username")
  var username: String = _

  @BeanProperty
  @Column(name = "email")
  var email: String = _

  @BeanProperty
  @Column(name = "password")
  var password: String = _

  @BeanProperty
  @Column(name = "creation_date")
  @Temporal(TemporalType.TIMESTAMP)
  var creationDate: Date = _

  @BeanProperty
  @Column(name = "last_connexion_time")
  @Temporal(TemporalType.TIMESTAMP)
  var lastConnexion: Date = _

  @BeanProperty
  @Column(name = "enabled")
  var enabled: Boolean = _

  @Type(`type` = "com.carhub.api.gearheads.entities.GenderType")
  var gender: Gender.Value = _

  @BeanProperty
  @OneToOne
  var location: Location = _

  @BeanProperty
  @OneToOne
  var workplace: Workplace = _

  override def toString = s"Gearhead($username)"

}
