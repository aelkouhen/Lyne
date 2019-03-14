package com.carhub.api.auto.domain

import java.io.Serializable
import java.util
import java.util.UUID

import com.carhub.api.auto.domain.enumerations._
import com.carhub.api.auto.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import com.fasterxml.jackson.annotation._
import javax.persistence._
import org.hibernate.annotations.{GenericGenerator, Type}
import scala.beans.BeanProperty

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@JsonApi(apiType= "vehicle")
@DiscriminatorColumn(name="vehicle_type")
abstract class Vehicle extends Serializable {

  @Id
  @BeanProperty
  @Column(name = "ID")
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @JsonApiId
  @Type(`type` = "uuid-char")
  var id: UUID = _

  @BeanProperty
  var name :String  = _

  //The time needed to accelerate the vehicle from a given start velocity to a given target velocity.
  //Typical unit code(s): seconds/0..100 km/h
  @BeanProperty
  var accelerationTime : Double  = _

  //Indicates the design and body shape of the vehicle.
  @BeanProperty
  @Enumerated(EnumType.STRING)
  var bodyShape : Body = _

  //The CO2 emissions in g/km.
  @BeanProperty
  var emissionsCO2 : Int = _

  //The capacity of the fuel tank or in the case of electric cars, the battery.
  @BeanProperty
  var fuelCapacity: Int = _

  //The amount of fuel consumed for traveling with the given vehicle (e.g. liters per 100 km).
  @BeanProperty
  var avgFuelConsumption : Double = _

  //Images of the vehicle.
  @BeanProperty
  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
  var images : util.List[Photo] = new util.ArrayList[Photo]()

  //Videos of the vehicle.
  @BeanProperty
  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
  var videos : util.List[Video] = new util.ArrayList[Video]()

  //Relevant documentations (technical specification, brochures...).
  @BeanProperty
  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
  var files : util.List[File] = new util.ArrayList[File]()

  //The sub-model (serie) of the product.
  @BeanProperty
  @JsonIgnore
  @OneToOne(fetch = FetchType.LAZY)
  var serie : Serie = _

  //The release date of a vehicle model (often used to differentiate versions of the same make and model).
  @BeanProperty
  var modelYear : Int = _

  //The number of airbags in the vehicle.
  @BeanProperty
  var numberOfAirbags : Int = _

  //The number of axles.
  @BeanProperty
  var numberOfAxles : Int = _

  //The number of doors.
  @BeanProperty
  var numberOfDoors : Int = _

  //The total number of forward gears available for the transmission system of the vehicle.
  @BeanProperty
  var numberOfForwardGears : Int = _

  //The permitted weight of passengers and cargo, EXCLUDING the weight of the empty vehicle.
  @BeanProperty
  var payload : Int = _

  //The number of persons that can be seated.
  @BeanProperty
  var seatingCapacity : Int = _

  //The maximum speed of the vehicle in KM/h.
  @BeanProperty
  var maxSpeed : Int = _

  //The permitted vertical load (TWR) of a trailer attached to the vehicle.
  // Also referred to as Tongue Load Rating (TLR) or Vertical Load Rating (VLR).
  @BeanProperty
  var tongueWeight : Int = _

  //The permitted weight of a trailer attached to the vehicle.
  @BeanProperty
  var trailerWeight : Int = _

  //A short text indicating the configuration of the vehicle (i.e., the trim), e.g. '5dr hatchback ST 2.5 MT 225 hp' or 'limited edition'.
  @BeanProperty
  var vehicleConfiguration : String = _

  //The drive wheel configuration: which wheels will receive torque from the vehicle's engine via the drive train.
  @BeanProperty
  @Enumerated(EnumType.STRING)
  var driveWheelConfiguration : DriveWheel = _

  //Information about the engine or engines of the vehicle.
  @BeanProperty
  @OneToOne(orphanRemoval = true)
  var engine : Engine = _

  //The Position of the engine.
  @BeanProperty
  @Enumerated(EnumType.STRING)
  var enginePosition : EnginePosition = _

  //The type of component used for transmitting the power from a rotating power source to the wheels or other relevant component(s) (i.e, "gearbox" for cars).
  @BeanProperty
  @Enumerated(EnumType.STRING)
  var transmissionMode : TransmissionMode = _

  //The front suspension system.
  @BeanProperty
  @Enumerated(EnumType.STRING)
  var frontSuspensionSystem : SuspensionSystem = _

  //The rear suspension system.
  @BeanProperty
  @Enumerated(EnumType.STRING)
  var rearSuspensionSystem : SuspensionSystem = _

  //The front break system.
  @BeanProperty
  @Enumerated(EnumType.STRING)
  var frontBreak : BreakType = _

  //The rear break system.
  @BeanProperty
  @Enumerated(EnumType.STRING)
  var rearBreak : BreakType = _


  //Measurements


  //The permitted total weight in Kg of the loaded vehicle, including passengers and cargo and the weight of the empty vehicle.
  @BeanProperty
  var maxWeight : Int = _

  //The Curb weight (American English) or kerb weight (British English) is the total mass of a vehicle with standard equipment and hardpoints (all necessary operating consumables such as motor oil, transmission oil, coolant, air conditioning refrigerant, and a full tank of fuel), while not loaded with either passengers, cargo, or weaponry.
  @BeanProperty
  var kerbWeight : Int = _

  //The distance in cm between the centers of the front and rear wheels.
  @BeanProperty
  var wheelBase : Int = _

  //The distance between the center line of two road wheels on the front axle.
  @BeanProperty
  var frontTrack : Int = _

  //The distance between the center line of two road wheels on the rear axle.
  @BeanProperty
  var backTrack : Int = _

  //The length of the vehicle..
  @BeanProperty
  var length : Int = _

  //The width of the vehicle.
  @BeanProperty
  var width : Int = _

  //The Width of the vehicle with mirrors folded.
  @BeanProperty
  var widthFolded : Int = _

  //The Height of the vehicle.
  @BeanProperty
  var height : Int = _

  //The drag coefficient is a common measure about the vehicle aerodynamics.
  // Drag is a force that acts parallel and in the same direction as the airflow.
  @BeanProperty
  var dragCoefficient : Double = _

  //The Ride height (also called clearance) is the shortest distance between a flat, level surface (the ground) the lowest point of the vehicle other than those parts designed to contact the ground.
  @BeanProperty
  var rideHeight : Int = _

  //The Approach angle is the maximum angle of a ramp onto which a vehicle can climb from a horizontal plane without interference.
  @BeanProperty
  var approachAngle : Double = _

  //The Departure angle is the maximum ramp angle from which the car can descend without damage.
  @BeanProperty
  var departureAngle : Double = _

  //The ramp angle is the maximum angle at which the car can travel at low speed over a ramp or obstacle without the underbody touching the edge of the ramp.
  @BeanProperty
  var rampAngle : Double = _

  //The maximum slope that a vehicle can climb.
  @BeanProperty
  var climbAngle : Double = _

  //The distance between the front and the front axle.
  @BeanProperty
  var frontOverhang : Int = _

  //The distance between the rear and the rear axle.
  @BeanProperty
  var rearOverhang : Int = _

  //The Wading depth is the limit of how the vehicle can safely run through a flood.
  @BeanProperty
  var wadingDepth : Int = _

  //The available volume for luggage (e.g., trunk volume).
  @BeanProperty
  var cargoVolume : Int = _

  //The Tire size.
  @BeanProperty
  var tireSize : String = _

  //The Wheel rims size.
  @BeanProperty
  var rimsSize : String = _
}
