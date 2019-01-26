package com.carhub.api.gearheads.model.activities

import java.lang.Long
import java.util._
import com.carhub.api.gearheads.model.Gearhead
import com.carhub.api.gearheads.model.files.File
import com.carhub.api.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import javax.persistence._

import scala.beans.BeanProperty

@Entity
@Table(name = "post")
@JsonApi(apiType = "post")
class Post extends Serializable {

  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  @JsonApiId
  var id: Long = _

  @BeanProperty
  var subject: String = _

  @BeanProperty
  var message: String = _

  @BeanProperty
  @OneToOne
  var quotedPost: Post = _

  @BeanProperty
  @OneToOne
  var repliedTo: Post = _

  @BeanProperty
  @Temporal(TemporalType.TIMESTAMP)
  var creationTime: Date = _

  @BeanProperty
  @OneToOne
  var creator: Gearhead = _

  @BeanProperty
  @ManyToOne
  var thread: Thread = _

  @Lob
  @BeanProperty
  @OneToMany
  var uploadedFiles: List[File] = _

}
