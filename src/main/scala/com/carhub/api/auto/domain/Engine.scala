package com.carhub.api.auto.domain

import com.carhub.api.auto.utils.enumeration.{EnumValue, EnumValueType}
import com.carhub.api.auto.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import javax.persistence._
import org.hibernate.annotations.Type

import scala.beans.BeanProperty

object Fuel extends Enumeration with EnumValue{
  val UNSPECIFIED, DIESEL, HYBRID, ELECTRICAL, LPG, CNG, HYDROGEN, GAS, ETHANOL = Value
}
class FuelType extends EnumValueType(Fuel){}

object InjectionSystem extends Enumeration with EnumValue{
  //TDI= Turbocharged Direct Injection
  //SDI = Standard Diesel Injection
  //SPI= Single Point Injection
  //MPI= Multi-Point Injection
  //CPFI= Central Port Fuel Injection
  //SPFI= Sequential Port Fuel Injection
  //CRDI= Common Rail Direct injection
  //HDI= High Pressure Direct Injection
  val HYBRID, SDI, CRDI, CARBURETTOR, SPI, HDI, DIRECT_INJECTION, MPI, SPFI = Value
}
class InjectionSystemType extends EnumValueType(InjectionSystem) {}

object CylinderPosition extends Enumeration with EnumValue{
  val V_ENGINE, W_ENGINE, BOXER, WANKEL, INLINE = Value
}
class CylinderPositionType extends EnumValueType(CylinderPosition){}

object TurbineSystem extends Enumeration with EnumValue{
  /*
  TWIN_POWER_TURBO= Twin-Scroll Turbo
  VGT= Variable Geometry Turbocharger
  VTS= Variable Twin-Scroll Turbocharger
  */
  val COMPRESSOR, TURBO, TWIN_TURBO , TWIN_SCROLL_TURBO, VGT, VTS, ELECTRIC_TURBO = Value
}
class TurbineSystemType extends EnumValueType(TurbineSystem){}

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

  //The name of the engine.
  @BeanProperty
  var name: String = _

  //The volume swept by all of the pistons inside the cylinders of an internal combustion engine in a single movement.
  //Typical unit code(s): CMQ for cubic centimeter
  @BeanProperty
  var engineDisplacement: Double = _

  //The power of the vehicle's engine. Typical unit code(s): KWT for kilowatt
  @BeanProperty
  var enginePower: Double  = _

  //The type of fuel injection system powering the engine.
  @BeanProperty
  @Type(`type` = "com.carhub.api.auto.domain.InjectionSystemType")
  var injectionSystem: InjectionSystem.Value = _

  //The type of fuel injection system powering the engine.
  @BeanProperty
  @Type(`type` = "com.carhub.api.auto.domain.TurbineSystemType")
  var turbineSystem: TurbineSystem.Value = _

  //The type of fuel suitable for the engine.
  @BeanProperty
  @Type(`type` = "com.carhub.api.auto.domain.FuelType")
  var fuelType: Fuel.Value = _

  //The torque (turning force) of the vehicle's engine.
  //Typical unit code(s): NU for newton metre (N m)
  @BeanProperty
  var torque: Int = _

  //The number of cylinders.
  @BeanProperty
  var numberOfCylinders: Int = _

  //The position of the cylinders.
  @BeanProperty
  @Type(`type` = "com.carhub.api.auto.domain.CylinderPositionType")
  var positionOfCylinders: CylinderPosition.Value = _

  //The bore is the diameter the cylinder in which a piston travels in (mm).
  @BeanProperty
  var cylinderBore: Double = _

  //The Piston Stroke
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
