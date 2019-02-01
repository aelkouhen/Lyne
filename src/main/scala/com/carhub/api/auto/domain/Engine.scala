package com.carhub.api.auto.domain

import com.carhub.api.utils.enumeration.{EnumValue, EnumValueType}
import com.carhub.api.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import javax.persistence._
import org.hibernate.annotations.Type

import scala.beans.BeanProperty


object Fuel extends Enumeration with EnumValue{
  val UNSPECIFIED, DIESEL, HYBRID, ELECTRICAL, LPG, HYDROGEN, GAS, ETHANOL = Value
}

class FuelType extends EnumValueType(Fuel){}


object EngineKind extends Enumeration with EnumValue{
  val COMBUSTION_ENGINE, NON_THERMAL_ENGINE, HYDRAULIC_MOTOR, PNEUMATIC_MOTOR, ELECTRIC_MOTOR, MOLECULAR_MOTOR, TURBO_MOTOR = Value
}

class EngineType extends EnumValueType(EngineKind){}

@Entity
@Table(name = "engine")
@JsonApi(apiType = "engine")
class Engine extends Serializable {

  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  @JsonApiId
  var id: Long = _

  //The volume swept by all of the pistons inside the cylinders of an internal combustion engine in a single movement.
  //Typical unit code(s): CMQ for cubic centimeter
  @BeanProperty
  var engineDisplacement: Float = _

  //The power of the vehicle's engine. Typical unit code(s): KWT for kilowatt
  @BeanProperty
  var enginePower: Float  = _

  //The type of engine powering the vehicle
  @BeanProperty
  @Type(`type` = "com.carhub.api.auto.domain.EngineType")
  var engineType: EngineKind.Value = _

  //The type of fuel suitable for the engine
  @BeanProperty
  @Type(`type` = "com.carhub.api.auto.domain.FuelType")
  var fuelType: Fuel.Value = _

  //The torque (turning force) of the vehicle's engine.
  //Typical unit code(s): NU for newton metre (N m)
  @BeanProperty
  var torque: Int = _

}
