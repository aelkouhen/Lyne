package com.carhub.api.gearheads.entities

import java.io.Serializable
import java.util.UUID

import com.carhub.api.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import javax.persistence._


@Entity
@Table(name = "gearhead")
@JsonApi(apiType = "gearhead")
class Gearhead extends Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonApiId
  var id: UUID = _

  var name: String = ""
  var email: String = ""


  override def toString = s"Gearhead($id, $name, $email)"

}
