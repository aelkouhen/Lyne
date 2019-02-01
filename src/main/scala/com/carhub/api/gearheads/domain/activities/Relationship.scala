package com.carhub.api.gearheads.domain.activities

import java.lang.Long
import java.util.Date

import com.carhub.api.gearheads.domain.Gearhead
import com.carhub.api.utils.enumeration.{EnumValue, EnumValueType}
import com.carhub.api.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import javax.persistence._
import org.hibernate.annotations.Type

import scala.beans.BeanProperty

object RelationshipStatus extends Enumeration with EnumValue{
  val CONFIRMED, DECLINED, WAITING, UNSPECIFIED = Value
}

class RelationshipStatusType extends EnumValueType(RelationshipStatus){}


object RelationshipKind extends Enumeration with EnumValue{
  val FRIENDSHIP, FOLLOWERSHIP = Value
}

class RelationshipType extends EnumValueType(RelationshipKind){}

@Entity
@Table(name = "relationship")
@JsonApi(apiType = "relationship")
class Relationship extends Serializable {
  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  @JsonApiId
  var id: Long = _

  @Type(`type` = "com.carhub.api.gearheads.domain.activities.RelationshipType")
  @Column(name = "RELATION_KIND")
  var relationshipKind: RelationshipKind.Value = _

  @Type(`type` = "com.carhub.api.gearheads.domain.activities.RelationshipStatusType")
  var relationshipStatus: RelationshipStatus.Value = _

  @BeanProperty
  @Temporal(TemporalType.TIMESTAMP)
  var since: Date = _

  @BeanProperty
  @Temporal(TemporalType.TIMESTAMP)
  var creationTime: Date = _

  @BeanProperty
  @ManyToOne
  var gearhead: Gearhead = _

  @BeanProperty
  @OneToOne
  var relatedTo: Gearhead = _

}
