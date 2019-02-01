package com.carhub.api.gearheads.domain.activities

import java.{lang, util}

import com.carhub.api.utils.jsonapi.annotations.JsonApi
import javax.persistence._

import scala.beans.BeanProperty

@Entity
@Table(name = "thread")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@JsonApi(apiType = "thread")
class Thread extends Topic {

  @BeanProperty
  var subject: String = _

  @BeanProperty
  var viewCount: lang.Long = _

  @BeanProperty
  @OneToMany(mappedBy = "thread")
  var posts: util.List[Post] = _

  @BeanProperty
  @ManyToOne
  var channel: Channel = _

}
