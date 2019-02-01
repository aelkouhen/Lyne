package com.carhub.api.auto.domain

import java.io.Serializable
import java.lang.Long

import com.carhub.api.gearheads.domain.files.Photo
import com.carhub.api.utils.enumeration.{EnumValue, EnumValueType}
import com.carhub.api.utils.jsonapi.annotations.JsonApiId
import javax.persistence._
import org.hibernate.annotations.Type

import scala.beans.BeanProperty

object WDEnum extends Enumeration with EnumValue{
  val WD2, WD4 = Value
}

class WDType extends EnumValueType(WDEnum){}

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

class TransmissionType extends EnumValueType(Transmission){}

object Body extends Enumeration with EnumValue{
  val MICRO, ECONOMY, HATCHBACK, FASTBACK, COUPE, INTERMEDIATE, MONOSPACE, FULL_SEDAN, LUXURY_SEDAN, ROADSTER, CABRIOLET, SPORT, SUPER, LIMOUSINE, MINIVAN, VAN, CAMPERVAN, WAGON, CROSSOVER, CUV, SUV, PICKUP_TRUCK, MINI_TRUCK, MONSTER_TRUCK, TRUCK, BIG_TRUCK = Value
}

class BodyType extends EnumValueType(Body){}

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="vehicle_type")
abstract class Vehicle extends Serializable {

  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  @JsonApiId
  var id: Long = _

  @BeanProperty
  var name:String  = _

  //The time needed to accelerate the vehicle from a given start velocity to a given target velocity.
  //Typical unit code(s): seconds/0..100 km/h
  @BeanProperty
  var accelerationTime: Float  = _

  //Indicates the design and body style of the vehicle.
  @BeanProperty
  @Type(`type` = "com.carhub.api.auto.domain.BodyType")
  var bodyType: Body.Value = _

  //The available volume for luggage (e.g., trunk volume).
  @BeanProperty
  var cargoVolume: Int = _

  //The drive wheel configuration: which wheels will receive torque from the vehicle's engine via the drive train.

  @BeanProperty
  @Type(`type` = "com.carhub.api.auto.domain.WDType")
  var driveWheelConfiguration: WDEnum.Value = _

  //The CO2 emissions in g/km.
  @BeanProperty
  var emissionsCO2: Int = _

  //The capacity of the fuel tank or in the case of electric cars, the battery.
  @BeanProperty
  var fuelCapacity: Int = _

  //The amount of fuel consumed for traveling with the given vehicle (e.g. liters per 100 km).
  @BeanProperty
  var AvgFuelConsumption: Float = _

  //The image of the vehicle.
  @BeanProperty
  @OneToOne
  var image: Photo = _

  //The sub-model (serie) of the product.
  @BeanProperty
  @OneToOne
  var serie: Serie = _

  //The release date of a vehicle model (often used to differentiate versions of the same make and model).
  @BeanProperty
  var modelYear: Int = _

  //The number or type of airbags in the vehicle.
  @BeanProperty
  var numberOfAirbags: Int = _

  //The number of axles.
  @BeanProperty
  var numberOfAxles: Int = _

  //The number of doors.
  @BeanProperty
  var numberOfDoors: Int = _

  //The total number of forward gears available for the transmission system of the vehicle.
  @BeanProperty
  var numberOfForwardGears: Int = _

  //The permitted weight of passengers and cargo, EXCLUDING the weight of the empty vehicle.
  @BeanProperty
  var payload: Int = _

  //The number of persons that can be seated.
  @BeanProperty
  var seatingCapacity: Int = _

  //The maximum speed of the vehicle in KM/h.
  @BeanProperty
  var maxSpeed: Int = _

  //The permitted vertical load (TWR) of a trailer attached to the vehicle.
  // Also referred to as Tongue Load Rating (TLR) or Vertical Load Rating (VLR).
  @BeanProperty
  var tongueWeight: Int = _

  //The permitted weight of a trailer attached to the vehicle.
  @BeanProperty
  var trailerWeight: Int = _

  //A short text indicating the configuration of the vehicle, e.g. '5dr hatchback ST 2.5 MT 225 hp' or 'limited edition'.
  @BeanProperty
  var vehicleConfiguration: String = _

  //Information about the engine or engines of the vehicle.
  @BeanProperty
  @OneToOne
  var engine: Engine = _

  //The type of component used for transmitting the power from a rotating power source to the wheels or other relevant component(s) (i.e, "gearbox" for cars).
  @BeanProperty
  @Type(`type` = "com.carhub.api.auto.domain.TransmissionType")
  var vehicleTransmission: Transmission.Value = _

  //The permitted total weight in Kg of the loaded vehicle, including passengers and cargo and the weight of the empty vehicle.
  @BeanProperty
  var maxWeight : Int = _

  //The distance in cm between the centers of the front and rear wheels.
  @BeanProperty
  var wheelBase : Int = _
}
