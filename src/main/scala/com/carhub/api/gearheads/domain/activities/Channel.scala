package com.carhub.api.gearheads.domain.activities

import java.{lang, util}

import com.carhub.api.utils.jsonapi.annotations.JsonApi
import javax.persistence._

import scala.beans.BeanProperty

@Entity
@Table(name = "channel")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@JsonApi(apiType = "channel")
class Channel extends Topic {

  @BeanProperty
  var name: String = _

  @BeanProperty
  var followersCount: lang.Long = _

  @BeanProperty
  @OneToMany(mappedBy = "channel")
  var treads: util.List[Thread] = new util.ArrayList[Thread]()

  @BeanProperty
  @OneToMany(mappedBy = "channel")
  var polls: util.List[Poll] = new util.ArrayList[Poll]()
}
