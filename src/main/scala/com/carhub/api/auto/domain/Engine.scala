package com.carhub.api.auto.domain

import java.util.UUID

import com.carhub.api.auto.domain.enumerations._
import com.carhub.api.auto.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import javax.persistence._
import org.hibernate.annotations.{GenericGenerator, Type}

import scala.beans.BeanProperty

@Entity
@Table(name = "engine")
@JsonApi(apiType = "engine")
class Engine extends Serializable {

  @Id
  @BeanProperty
  @Column(name = "ID")
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @JsonApiId
  @Type(`type` = "uuid-char")
  var id: UUID = _

  //The name of the engine.
  @BeanProperty
  var name: String = _

  //The volume swept by all of the pistons inside the cylinders of an internal combustion engine in a single movement.
  //Typical unit code(s): CMQ for cubic centimeter
  @BeanProperty
  var engineDisplacement: Double = _

  //The power of the vehicle's engine. Typical unit code(s): KWT for kilowatt.
  @BeanProperty
  var enginePower: Double  = _

  //The type of fuel injection system powering the engine.
  @BeanProperty
  @Enumerated(EnumType.STRING)
  var injectionSystem: InjectionSystem = _

  //The type of turbine system in the engine.
  @BeanProperty
  @Enumerated(EnumType.STRING)
  var turbineSystem: TurbineSystem = _

  //The type of fuel suitable for the engine.
  @BeanProperty
  @Enumerated(EnumType.STRING)
  var fuelType: FuelType = _

  //The torque (turning force) of the vehicle's engine.
  //Typical unit code(s): NU for newton metre (N m)
  @BeanProperty
  var torque: Int = _

  //The number of cylinders.
  @BeanProperty
  var numberOfCylinders: Int = _

  //The position of the cylinders.
  @BeanProperty
  @Enumerated(EnumType.STRING)
  var cylinderPosition: CylinderPosition = _

  //The bore is the diameter the cylinder in which a piston travels in (mm).
  @BeanProperty
  var cylinderBore: Double = _

  //The Piston Stroke.
  @BeanProperty
  var pistonStroke: Double = _

  //The Compression ratio of the piston.
  @BeanProperty
  var compressionRatio: Double = _

  //The Number of valves per cylinder.
  @BeanProperty
  var valvesPerCylinder: Int = _

  //The Engine Oil capacity in (l).
  @BeanProperty
  var oilCapacity: Double = _

  //The Engine Coolant capacity in (l).
  @BeanProperty
  var coolantCapacity: Double = _
}
