package com.carhub.api.gearheads.domain.activities

import java.lang.Long
import java.util

import com.carhub.api.gearheads.domain.Gearhead
import com.carhub.api.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import javax.persistence._

import scala.beans.BeanProperty

@Entity
@Table(name = "choice")
@JsonApi(apiType = "choice")
class Choice extends Serializable {
  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonApiId
  var id: Long = _

  @BeanProperty
  var content: String = _

  @BeanProperty
  var count: Long = _

  @BeanProperty
  @ManyToOne
  var poll: Poll = _

  @BeanProperty
  @OneToMany
  var voters: util.List[Gearhead] = new util.ArrayList[Gearhead]()
}
