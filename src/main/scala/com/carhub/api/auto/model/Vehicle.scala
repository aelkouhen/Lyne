package com.carhub.api.auto.model

import java.io.Serializable
import java.lang.Long
import java.util.Date

import com.carhub.api.gearheads.model.files.Photo
import com.carhub.api.gearheads.utils.enumeration.{EnumValue, EnumValueType}
import com.carhub.api.gearheads.utils.jsonapi.annotations.JsonApiId
import javax.persistence._

import scala.beans.BeanProperty

object WD extends Enumeration with EnumValue{
  val WD2, WD4 = Value
}

class WDConfig extends EnumValueType(WD){}



object Transmission extends Enumeration with EnumValue{
  /*
  Manual Transmission (MT).
  Automated Transmission (AT).
  Semi-Automated Transmission (SAT).
  Continuously Variable Transmission (CVT).
  Dual-Clutch Transmission (DCT).
  DSG (Direct Shift Gearbox).
 */
  val MT, AT, SAT, CVT, DCT, DSG = Value
}

class TransmissionKind extends EnumValueType(Transmission){}


object Fuel extends Enumeration with EnumValue{
  val UNSPECIFIED, DIESEL, HYBRID, ELECTRICAL, LPG, HYDROGEN, GAS, ETHANOL = Value
}

class FuelKind extends EnumValueType(Fuel){}


@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
abstract class Vehicle extends Serializable{

  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.TABLE)
  @Column(name = "ID")
  @JsonApiId
  var id: Long = _

  @BeanProperty
  @OneToOne
  var image: Photo

  @BeanProperty
  var name:String

  @BeanProperty
  var accelerationTime: Float

  @BeanProperty
  var bodyType: BodyKind

  @BeanProperty
  var cargoVolume: Integer

  @BeanProperty
  var driveWheelConfiguration: WDConfig

  @BeanProperty
  var emissionsCO2: Int

  @BeanProperty
  var fuelCapacity: Int

  @BeanProperty
  var AvgFuelConsumption: Float

  @BeanProperty
  var fuelType: FuelKind

  @BeanProperty
  @OneToOne
  var image: Photo

  @BeanProperty
  @OneToOne
  var model: Model

  @BeanProperty
  @OneToOne
  var model: SubModel

  @BeanProperty
  var modelYear: Int

  @BeanProperty
  var numberOfAirbags: Int

  @BeanProperty
  var numberOfAxles: Int

  @BeanProperty
  var numberOfDoors: Int

  @BeanProperty
  var payload: Int

  @BeanProperty
  var productionDate: Date

  @BeanProperty
  var seatingCapacity: Int

  @BeanProperty
  var maxSpeed: Int

  @BeanProperty
  var tongueWeight: Int

  @BeanProperty
  var trailerWeight: Int

  @BeanProperty
  var vehicleConfiguration: String

  @BeanProperty
  @OneToOne
  var vehicleEngine: Engine

  @BeanProperty
  var vehicleTransmission: TransmissionKind

  @BeanProperty
  var maxWeight : Int

  @BeanProperty
  var wheelbase : Int


}
