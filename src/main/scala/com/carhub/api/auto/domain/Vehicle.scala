package com.carhub.api.auto.domain

import java.io.Serializable
import java.lang.Long
import java.util

import com.carhub.api.auto.utils.enumeration.{EnumValue, EnumValueType}
import com.carhub.api.auto.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import com.fasterxml.jackson.annotation.{JsonFormat, JsonIgnore}
import javax.persistence._
import org.hibernate.annotations.Type

import scala.beans.BeanProperty

object WDEnum extends Enumeration with EnumValue{
  val FRONT_2WD, REAR_2WD, ALL_4WD = Value
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

object Suspension extends Enumeration with EnumValue{

  val MCPHERSON_STRUT, DEPRECIATED_RACK, HELICAL_SPRING, ANTIROLL_SPRING, SPRING, COIL_SPRING, SUSPENSION_WITH_STEERING_ROD, MULTI_LINK_SPRING, MULTI_LINK_SPRING_WITH_ABSORBERS, MCPHERSON_SPRING_WITH_STABILIZER, SPRING_LOADED_RACK, HYDRAULIC, PNEUMATIC, HYDRO_PNEUMATIC, WISHBONE, DOUBLE_WISHBONE, INCLINED_LEVER, TRAPEZOIDAL_LEVER, BEAM_BRIDGE, ROTARY_FIST, TRANSVERSE_STABILIZER, TRAILING, TORSION, THREADED_TWIST_BEAM, ELASTIC_BEAM, DE_DION = Value
}
class SuspensionType extends EnumValueType(Suspension){}

object Break extends Enumeration with EnumValue{

  val DRUM, DISC, VENTILATED_DISC = Value
}
class BreakType extends EnumValueType(Break){}

object Body extends Enumeration with EnumValue{

  val MICRO, ECONOMY, COMBI, HATCHBACK, FASTBACK, LIFTBACK, COUPE, INTERMEDIATE, MONOSPACE, FULL_SEDAN, LUXURY_SEDAN, ROADSTER, CABRIOLET, CC ,SPORT, SUPER, LIMOUSINE, MINIVAN, VAN, CAMPERVAN, WAGON, CROSSOVER, MPV, CUV, SUV, OFF_ROAD, TARGA, GRAND_TOURER, PICKUP_TRUCK, MINI_TRUCK, MONSTER_TRUCK, TRUCK, BIG_TRUCK = Value
}
class BodyType extends EnumValueType(Body){}

object EnginePosition extends Enumeration with EnumValue{

  val MIDDLE_TRANSVERSELY, REAR_TRANSVERSELY, FRONT_TRANSVERSELY, MIDDLE_LONGITUDINAL, REAR_LONGITUDINAL, FRONT_LONGITUDINAL = Value
}
class EnginePositionType extends EnumValueType(EnginePosition){}

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@JsonApi(apiType= "vehicle")
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
  var accelerationTime: Double  = _

  //Indicates the design and body style of the vehicle.
  @BeanProperty
  @Type(`type` = "com.carhub.api.auto.domain.BodyType")
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  var bodyType: Body.Value = _

  //The CO2 emissions in g/km.
  @BeanProperty
  var emissionsCO2: Int = _

  //The capacity of the fuel tank or in the case of electric cars, the battery.
  @BeanProperty
  var fuelCapacity: Int = _

  //The amount of fuel consumed for traveling with the given vehicle (e.g. liters per 100 km).
  @BeanProperty
  var avgFuelConsumption: Double = _

  //Images of the vehicle.
  @BeanProperty
  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY)
  var images: util.List[Photo] = new util.ArrayList[Photo]()

  //Videos of the vehicle.
  @BeanProperty
  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY)
  var videos: util.List[Video] = new util.ArrayList[Video]()

  //Relevant documentations (technical specification, brochures...)
  @BeanProperty
  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY)
  var files: util.List[File] = new util.ArrayList[File]()

  //The sub-model (serie) of the product.
  @BeanProperty
  @JsonIgnore
  @OneToOne(fetch = FetchType.LAZY)
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

  //A short text indicating the configuration of the vehicle (i.e., the trim), e.g. '5dr hatchback ST 2.5 MT 225 hp' or 'limited edition'.
  @BeanProperty
  var vehicleConfiguration: String = _

  //The drive wheel configuration: which wheels will receive torque from the vehicle's engine via the drive train.
  @BeanProperty
  @Type(`type` = "com.carhub.api.auto.domain.WDType")
  var driveWheelConfiguration: WDEnum.Value = _

  //Information about the engine or engines of the vehicle.
  @BeanProperty
  @OneToOne
  var engine: Engine = _

  //The Position of the engine
  @BeanProperty
  @Type(`type` = "com.carhub.api.auto.domain.EnginePositionType")
  var enginePosition: EnginePosition.Value = _

  //The type of component used for transmitting the power from a rotating power source to the wheels or other relevant component(s) (i.e, "gearbox" for cars).
  @BeanProperty
  @Type(`type` = "com.carhub.api.auto.domain.TransmissionType")
  var vehicleTransmission: Transmission.Value = _

  //The front suspension system.
  @BeanProperty
  @Type(`type` = "com.carhub.api.auto.domain.SuspensionType")
  var frontSuspension: Suspension.Value = _

  //The rear suspension system.
  @BeanProperty
  @Type(`type` = "com.carhub.api.auto.domain.SuspensionType")
  var rearSuspension: Suspension.Value = _

  //The front break system.
  @BeanProperty
  @Type(`type` = "com.carhub.api.auto.domain.BreakType")
  var frontBreak: Break.Value = _

  //The rear break system.
  @BeanProperty
  @Type(`type` = "com.carhub.api.auto.domain.BreakType")
  var rearBreak: Break.Value = _


  //Measurements


  //The permitted total weight in Kg of the loaded vehicle, including passengers and cargo and the weight of the empty vehicle.
  @BeanProperty
  var maxWeight : Int = _

  //Curb weight (American English) or kerb weight (British English) is the total mass of a vehicle with standard equipment and hardpoints (all necessary operating consumables such as motor oil, transmission oil, coolant, air conditioning refrigerant, and a full tank of fuel), while not loaded with either passengers, cargo, or weaponry.
  @BeanProperty
  var kerbWeight : Int = _

  //The distance in cm between the centers of the front and rear wheels.
  @BeanProperty
  var wheelBase : Int = _

  //The the distance between the center line of two road wheels on the front axle.
  @BeanProperty
  var frontTrack: Int = _

  //The the distance between the center line of two road wheels on the rear axle.
  @BeanProperty
  var backTrack: Int = _

  //The length of the vehicle..
  @BeanProperty
  var length: Int = _

  //The width of the vehicle.
  @BeanProperty
  var width: Int = _

  //The Width of the vehicle with mirrors folded.
  @BeanProperty
  var widthFolded: Int = _

  //The Height of the vehicle.
  @BeanProperty
  var height: Int = _

  //The drag coefficient is a common measure about the vehicle aerodynamics.
  // Drag is a force that acts parallel and in the same direction as the airflow.
  @BeanProperty
  var dragCoefficient: Double = _

  //Ride height (also called clearance) is the shortest distance between a flat, level surface (the ground) the lowest point of the vehicle other than those parts designed to contact the ground.
  @BeanProperty
  var rideHeight: Int = _

  //The Approach angle is the maximum angle of a ramp onto which a vehicle can climb from a horizontal plane without interference.
  @BeanProperty
  var approachAngle: Double = _

  //The Departure angle is the maximum ramp angle from which the car can descend without damage.
  @BeanProperty
  var departureAngle: Double = _

  //The ramp angle is the maximum angle at which the car can travel at low speed over a ramp or obstacle without the underbody touching the edge of the ramp.
  @BeanProperty
  var rampAngle: Double = _

  //The maximum slope that a vehicle can climb.
  @BeanProperty
  var climbAngle: Double = _

  //The distance between the front and the front axle.
  @BeanProperty
  var frontOverhang: Int = _

  //The distance between the rear and the rear axle.
  @BeanProperty
  var rearOverhang: Int = _

  //The Wading depth is the limit of how the vehicle can safely run through a flood.
  @BeanProperty
  var wadingDepth: Int = _

  //The available volume for luggage (e.g., trunk volume).
  @BeanProperty
  var cargoVolume: Int = _

  //The Tire size.
  @BeanProperty
  var tireSize: String = _

  //The Wheel rims size
  @BeanProperty
  var rimsSize: String = _
}
