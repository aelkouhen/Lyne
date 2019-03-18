package com.carhub.api.auto.controllers.command

import java.util.UUID

import com.carhub.api.auto.domain._
import com.carhub.api.auto.services.command.{CarCommandService, EngineCommandService}
import com.carhub.api.auto.utils.exception.{ElementNotCreatedException, ElementNotUpdatedException}
import io.swagger.annotations.{Api, ApiOperation, ApiParam}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._
import org.springframework.web.multipart.MultipartFile

@Api(value = "Car", tags = Array("Car Commands"), description = "This API commands the Car concept.")
@RestController
@RequestMapping(value = Array("/v1"))
class CarCommandRestController(@Autowired val carCommandService : CarCommandService,
                                          val engineCommandService : EngineCommandService)  {

  @ApiOperation(value = "Create a Car.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('CREATE_PRIVILEGE')")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @PostMapping(value = Array("/cars"))
  def createCar(@ApiParam(name = "car", value = "A Car object.", required = true) @RequestBody car: Car): ResponseEntity[_] = {
    val created = carCommandService.addCar(car)
    if(created == null) throw new ElementNotCreatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.CREATED).body(created)
  }

  @ApiOperation(value = "Update a Car.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PutMapping(value = Array("/cars/{id}"))
  def updateCar(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @RequestBody car: Car): ResponseEntity[_] = {
    val updated = carCommandService.updateCar(UUID.fromString(id), car)

    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's acceleration time.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("acceleration"))
  def updateCarAccelerationTime(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "acceleration", value = "The time needed to accelerate the vehicle from a given start velocity to a given target velocity. Typical unit code(s): seconds/0..100 km/h.", required = true, example = "0.0") @RequestParam(name = "acceleration") accelerationTime : Double) = {
    val updated = carCommandService.updateCarAccelerationTime(UUID.fromString(id), accelerationTime)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }


  @ApiOperation(value = "Update the Car's approach angle.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("approach"))
  def updateCarApproachAngle(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "approach", value = "The Approach angle is the maximum angle of a ramp onto which a vehicle can climb from a horizontal plane without interference.", required = true, example = "0.0") @RequestParam(name = "approach") approachAngle : Double) = {
    val updated = carCommandService.updateCarApproachAngle(UUID.fromString(id), approachAngle)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's fuel consumption.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("consumption"))
  def updateCarFuelConsumption(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "consumption", value = "The amount of fuel consumed for traveling with the given vehicle (e.g. liters per 100 km).", required = true, example = "0.0") @RequestParam(name = "consumption") avgFuelConsumption : Double) = {
    val updated = carCommandService.updateCarFuelConsumption(UUID.fromString(id), avgFuelConsumption)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's back track.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("backTrack"))
  def updateCarBackTrack(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "backTrack", value = "The distance between the center line of two road wheels on the rear axle.", required = true, example = "0") @RequestParam(name = "backTrack") backTrack : Int) = {
    val updated = carCommandService.updateCarBackTrack(UUID.fromString(id), backTrack)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's body category.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("body"))
  def updateCarBody(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "body", value = "Indicates the design and body style of the vehicle.", required = true) @RequestParam(name = "body") body : String) = {
    val updated = carCommandService.updateCarBody(UUID.fromString(id), body)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's climb angle.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("climb"))
  def updateCarClimbAngle(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "climb", value = "The maximum slope that a vehicle can climb.", required = true, example = "0.0") @RequestParam(name = "climb") climbAngle : Double) = {
    val updated = carCommandService.updateCarClimbAngle(UUID.fromString(id), climbAngle)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's departure angle.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("departure"))
  def updateCarDepartureAngle(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "departure", value = "The Departure angle is the maximum ramp angle from which the car can descend without damage.", required = true, example = "0.0") @RequestParam(name = "departure") departureAngle : Double) = {
    val updated = carCommandService.updateCarDepartureAngle(UUID.fromString(id), departureAngle)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's drag coefficient.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("drag"))
  def updateCarDragCoefficient(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "drag", value = "The drag coefficient is a common measure about the vehicle aerodynamics. Drag is a force that acts parallel and in the same direction as the airflow.", required = true, example = "0.0") @RequestParam(name = "drag") dragCoefficient : Double) = {
    val updated = carCommandService.updateCarDragCoefficient(UUID.fromString(id), dragCoefficient)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's drive wheel configuration.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("wd"))
  def updateCarDriveWheelConfiguration(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "wd", value = "The drive wheel configuration: which wheels will receive torque from the vehicle's engine via the drive train.", required = true) @RequestParam(name = "wd") driveWheelConfiguration : String) = {
    val updated = carCommandService.updateCarDriveWheelConfiguration(UUID.fromString(id), driveWheelConfiguration)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's CO2 emission.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("emission"))
  def updateCarEmissionCO2(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "emission", value = "The CO2 emissions in g/km.", required = true, example = "0") @RequestParam(name = "emission") emissionCO2 : Int) = {
    val updated = carCommandService.updateCarEmissionCO2(UUID.fromString(id), emissionCO2)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's engine position.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("position"))
  def updateCarEnginePosition(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "position", value = "The Position of the engine.", required = true) @RequestParam(name = "position") position : String) = {
    val updated = carCommandService.updateCarEnginePosition(UUID.fromString(id), position)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's front break.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("frontBreak"))
  def updateCarFrontBreak(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "frontBreak", value = "The front break system.", required = true) @RequestParam(name = "frontBreak") frontBreak : String) = {
    val updated = carCommandService.updateCarFrontBreak(UUID.fromString(id), frontBreak)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's front overhang.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("frontOverhang"))
  def updateCarFrontOverhang(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "frontOverhang", value = "The distance between the front and the front axle.", required = true, example = "0") @RequestParam(name = "frontOverhang") frontOverhang : Int) = {
    val updated = carCommandService.updateCarFrontOverhang(UUID.fromString(id), frontOverhang)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's front suspension.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("frontSuspension"))
  def updateCarFrontSuspension(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "frontSuspension", value = "The front suspension system.", required = true) @RequestParam(name = "frontSuspension") frontSuspension : String) = {
    val updated = carCommandService.updateCarFrontSuspension(UUID.fromString(id), frontSuspension)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's front track.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("frontTrack"))
  def updateCarFrontTrack(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "frontTrack", value = "The distance between the center line of two road wheels on the front axle.", required = true, example = "0") @RequestParam(name = "frontTrack") frontTrack : Int) = {
    val updated = carCommandService.updateCarFrontTrack(UUID.fromString(id), frontTrack)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's fuel capacity.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("fuelCapacity"))
  def updateCarFuelCapacity(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "fuelCapacity", value = "The capacity of the fuel tank or in the case of electric cars, the battery.", required = true, example = "0") @RequestParam(name = "fuelCapacity") fuelCapacity : Int) = {
    val updated = carCommandService.updateCarFuelCapacity(UUID.fromString(id), fuelCapacity)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's height.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("height"))
  def updateCarHeight(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "height", value = "The Height of the car.", required = true, example = "0") @RequestParam(name = "height") height : Int) = {
    val updated = carCommandService.updateCarHeight(UUID.fromString(id), height)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's kerb weight.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("kerbWeight"))
  def updateCarKerbWeight(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "kerbWeight", value = "The Curb weight (American English) or kerb weight (British English) is the total mass of a vehicle with standard equipment and hardpoints (all necessary operating consumables such as motor oil, transmission oil, coolant, air conditioning refrigerant, and a full tank of fuel), while not loaded with either passengers, cargo, or weaponry.", required = true, example = "0") @RequestParam(name = "kerbWeight") kerbWeight : Int) = {
    val updated = carCommandService.updateCarKerbWeight(UUID.fromString(id), kerbWeight)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's length.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("length"))
  def updateCarLength(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "length", value = "The length of the car.", required = true, example = "0") @RequestParam(name = "length") length : Int) = {
    val updated = carCommandService.updateCarLength(UUID.fromString(id), length)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's maximum speed.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("speed"))
  def updateCarMaxSpeed(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "speed", value = "The maximum speed of the vehicle in KM/h.", required = true, example = "0") @RequestParam(name = "speed") speed : Int) = {
    val updated = carCommandService.updateCarMaxSpeed(UUID.fromString(id), speed)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's max weight.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("maxWeight"))
  def updateCarMaxWeight(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "maxWeight", value = "The permitted total weight in Kg of the loaded vehicle, including passengers and cargo and the weight of the empty vehicle.", required = true, example = "0") @RequestParam(name = "maxWeight") maxWeight : Int) = {
    val updated = carCommandService.updateCarMaxWeight(UUID.fromString(id), maxWeight)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's manufacturing year.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("modelYear"))
  def updateCarModelYear(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "modelYear", value = "The release date of a vehicle model (often used to differentiate versions of the same make and model).", required = true, example = "0") @RequestParam(name = "modelYear") modelYear : Int) = {
    val updated = carCommandService.updateCarModelYear(UUID.fromString(id), modelYear)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's name.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("name"))
  def updateCarName(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "name", value = "The car's name.", required = true) @RequestParam(name = "name") name : String) = {
    val updated = carCommandService.updateCarName(UUID.fromString(id), name)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's number of airbags.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("airbags"))
  def updateCarNumberOfAirbags(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "airbags", value = "The car's number of airbags.", required = true, example = "0") @RequestParam(name = "airbags") numberOfAirbags : Int) = {
    val updated = carCommandService.updateCarNumberOfAirbags(UUID.fromString(id), numberOfAirbags)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's number of axles.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("axles"))
  def updateCarNumberOfAxles(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "axles", value = "The car's number of axles.", required = true, example = "0") @RequestParam(name = "axles") numberOfAxles : Int) = {
    val updated = carCommandService.updateCarNumberOfAxles(UUID.fromString(id), numberOfAxles)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's number of doors.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("doors"))
  def updateCarNumberOfDoors(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "doors", value = "The car's number of doors.", required = true, example = "0") @RequestParam(name = "doors") numberOfDoors : Int) = {
    val updated = carCommandService.updateCarNumberOfDoors(UUID.fromString(id), numberOfDoors)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's number of forward gears.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("forwardGears"))
  def updateCarForwardGears(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "forwardGears", value = "The total number of forward gears available for the transmission system of the vehicle.", required = true, example = "0") @RequestParam(name = "forwardGears") forwardGears : Int) = {
    val updated = carCommandService.updateCarNumberOfForwardGears(UUID.fromString(id), forwardGears)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's payload.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("payload"))
  def updateCarPayload(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "payload", value = "The permitted weight of passengers and cargo, EXCLUDING the weight of the empty vehicle.", required = true, example = "0") @RequestParam(name = "payload") payload : Int) = {
    val updated = carCommandService.updateCarPayload(UUID.fromString(id), payload)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's ramp angle.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("ramp"))
  def updateCarRampAngle(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "ramp", value = "The ramp angle is the maximum angle at which the car can travel at low speed over a ramp or obstacle without the underbody touching the edge of the ramp.", required = true, example = "0") @RequestParam(name = "ramp") rampAngle : Int) = {
    val updated = carCommandService.updateCarRampAngle(UUID.fromString(id), rampAngle)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's rear break.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("rearBreak"))
  def updateCarRearBreak(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "rearBreak", value = "The rear break system.", required = true) @RequestParam(name = "rearBreak") rearBreak : String) = {
    val updated = carCommandService.updateCarRearBreak(UUID.fromString(id), rearBreak)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's rear overhang.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("rearOverhang"))
  def updateCarRearOverhang(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "rearOverhang", value = "The distance between the rear and the rear axle.", required = true, example = "0") @RequestParam(name = "rearOverhang") rearOverhang : Int) = {
    val updated = carCommandService.updateCarRearOverhang(UUID.fromString(id), rearOverhang)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's rear suspension.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("rearSuspension"))
  def updateCarRearSuspension(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "rearSuspension", value = "The rear suspension system.", required = true) @RequestParam(name = "rearSuspension") rearSuspension : String) = {
    val updated = carCommandService.updateCarRearSuspension(UUID.fromString(id), rearSuspension)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's ride height.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("rideHeight"))
  def updateCarRideHeight(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "rideHeight", value = "The Ride height (also called clearance) is the shortest distance between a flat, level surface (the ground) the lowest point of the vehicle other than those parts designed to contact the ground.", required = true, example = "0") @RequestParam(name = "rideHeight") rideHeight : Int) = {
    val updated = carCommandService.updateCarRideHeight(UUID.fromString(id), rideHeight)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's rim size.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("rimsSize"))
  def updateCarRimsSize(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "rimsSize", value = "The Wheel rims size.", required = true) @RequestParam(name = "rimsSize") rimsSize : String) = {
    val updated = carCommandService.updateCarRimSize(UUID.fromString(id), rimsSize)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's seating capacity.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("seatingCapacity"))
  def updateCarSeatingCapacity(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "seatingCapacity", value = "The number of persons that can be seated.", required = true, example = "0") @RequestParam(name = "seatingCapacity") seatingCapacity : Int) = {
    val updated = carCommandService.updateCarSeatingCapacity(UUID.fromString(id), seatingCapacity)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's Serie.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("serie"))
  def updateCarSerie(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "serie", value = "The Serie's ID.", required = true) @RequestParam(name = "serie") serieId : String) = {
    val updated = carCommandService.updateCarSerie(UUID.fromString(id), UUID.fromString(serieId))
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's tire size.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("tireSize"))
  def updateCarTireSize(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "tireSize", value = "The Tire size.", required = true) @RequestParam(name = "tireSize") tireSize : String) = {
    val updated = carCommandService.updateCarTireSize(UUID.fromString(id), tireSize)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's tongue weight.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("tongueWeight"))
  def updateCarTongueWeight(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "tongueWeight", value = "The permitted vertical load (TWR) of a trailer attached to the vehicle. Also referred to as Tongue Load Rating (TLR) or Vertical Load Rating (VLR).", required = true, example = "0") @RequestParam(name = "tongueWeight") tongueWeight : Int) = {
    val updated = carCommandService.updateCarTongueWeight(UUID.fromString(id), tongueWeight)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's trailer weight.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("trailerWeight"))
  def updateCarTrailerWeight(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "trailerWeight", value = "The permitted weight of a trailer attached to the vehicle.", required = true, example = "0") @RequestParam(name = "trailerWeight") trailerWeight : Int) = {
    val updated = carCommandService.updateCarTrailerWeight(UUID.fromString(id), trailerWeight)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's configuration.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("configuration"))
  def updateCarConfiguration(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "configuration", value = "A short text indicating the configuration of the vehicle (i.e., the trim), e.g. '5dr hatchback ST 2.5 MT 225 hp' or 'limited edition'..", required = true) @RequestParam(name = "configuration") configuration : String) = {
    val updated = carCommandService.updateCarConfiguration(UUID.fromString(id), configuration)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's transmission system.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("transmission"))
  def updateCarTransmission(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "transmission", value = "The type of component used for transmitting the power from a rotating power source to the wheels or other relevant component(s) (i.e, 'gearbox' for cars).", required = true) @RequestParam(name = "transmission") transmission : String) = {
    val updated = carCommandService.updateCarTransmission(UUID.fromString(id), transmission)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's wading depth.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("wadingDepth"))
  def updateCarWadingDepth(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "wadingDepth", value = "The Wading depth is the limit of how the vehicle can safely run through a flood.", required = true, example = "0") @RequestParam(name = "wadingDepth") wadingDepth : Int) = {
    val updated = carCommandService.updateCarWadingDepth(UUID.fromString(id), wadingDepth)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's wheel base.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("wheelBase"))
  def updateCarWheelBase(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "wheelBase", value = "The distance in cm between the centers of the front and rear wheels.", required = true, example = "0") @RequestParam(name = "wheelBase") wheelBase : Int) = {
    val updated = carCommandService.updateCarWheelBase(UUID.fromString(id), wheelBase)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's width.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("width"))
  def updateCarWidth(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "width", value = "The width of the vehicle.", required = true, example = "0") @RequestParam(name = "width") width : Int) = {
    val updated = carCommandService.updateCarWidth(UUID.fromString(id), width)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's width.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("widthFolded"))
  def updateCarWidthFolded(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "widthFolded", value = "The Width of the vehicle with mirrors folded.", required = true, example = "0") @RequestParam(name = "widthFolded") widthFolded : Int) = {
    val updated = carCommandService.updateCarWidthFolded(UUID.fromString(id), widthFolded)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's cargo volume.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}"), params = Array("cargoVolume"))
  def updateCarCargoVolume(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "cargoVolume", value = "The available volume for luggage (e.g., trunk volume).", required = true, example = "0") @RequestParam(name = "cargoVolume") cargoVolume : Int) = {
    val updated = carCommandService.updateCarCargoVolume(UUID.fromString(id), cargoVolume)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's engine.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}/engines"))
  def updateCarEngine(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "engine", value = "Information about the engine or engines of the vehicle.", required = true) @RequestBody engine : Engine) = {
    val updated = carCommandService.updateCarEngine(UUID.fromString(id), engine)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Upload a Car's related file.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}/files"))
  def updateCarUploadFile(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "file", value = "Relevant documentations (technical specification, brochures...).", required = true) @RequestParam(name = "file") file : MultipartFile) = {
    val updated = carCommandService.updateCarUploadFile(UUID.fromString(id), file)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Upload a Car's related photo.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}/photos"))
  def updateCarUploadPhoto(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "photo", value = "An images of the Car.", required = true) @RequestParam(name = "photo") photo : MultipartFile) = {
    val updated = carCommandService.updateCarUploadPhoto(UUID.fromString(id), photo)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Upload a Car's related videos.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/cars/{id}/videos"))
  def updateCarUploadVideo(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "video", value = "A video of the Car.", required = true) @RequestParam(name = "video") video : MultipartFile) = {
    val updated = carCommandService.updateCarUploadVideo(UUID.fromString(id), video)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Delete the Car.", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('DELETE_PRIVILEGE')")
  @ResponseBody
  @DeleteMapping(Array("/cars/{id}"))
  def deleteCar(@ApiParam(name = "id", value = "The Car ID.", required = true) @PathVariable(value = "id") id : String) = {
    carCommandService.deleteCar(UUID.fromString(id))
    ResponseEntity.status(HttpStatus.NO_CONTENT).build()
  }
}
