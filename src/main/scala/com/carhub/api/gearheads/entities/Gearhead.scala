package com.carhub.api.gearheads.entities

import java.io.Serializable
import java.lang.Long

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
class Gearhead extends Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: Long = _

  var name: String = ""
  var email: String = ""


}
