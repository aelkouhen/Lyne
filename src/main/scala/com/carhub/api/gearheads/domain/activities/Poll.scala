package com.carhub.api.gearheads.domain.activities

import java.util

import com.carhub.api.utils.jsonapi.annotations.JsonApi
import javax.persistence._

import scala.beans.BeanProperty

@Entity
@Table(name = "poll")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@JsonApi(apiType = "poll")
class Poll extends Topic {

  @BeanProperty
  var subject: String = _

  @BeanProperty
  @OneToMany(mappedBy = "poll")
  var choices: util.List[Choice] = new util.ArrayList[Choice]()

  @BeanProperty
  @ManyToOne
  var channel: Channel = _

}
