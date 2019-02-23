package com.carhub.api.auto.controllers.command

import com.carhub.api.auto.domain.{Car, Engine}
import com.carhub.api.auto.services.command.{CarCommandService, EngineCommandService}
import com.carhub.api.auto.utils.exception.{ElementNotCreatedException, ElementNotUpdatedException}
import io.swagger.annotations.{Api, ApiOperation, ApiParam}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._

@Api(value = "Car", tags = Array("Car"), description = "This API queries the Car concept.")
@RestController
@RequestMapping(Array("/api/cars"))
class CarCommandRestController(@Autowired val carCommandService : CarCommandService,
                                          val engineCommandService : EngineCommandService)  {

  @ApiOperation(value = "Create a Car.", response = classOf[Car])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @PostMapping
  def createCar(@ApiParam(name = "car", value = "A Car object.", required = true) @RequestBody car: Car): ResponseEntity[_] = {
    val engineCreated = engineCommandService.addEngine(car.engine)
    if(engineCreated == null) throw new ElementNotCreatedException[Engine](classOf[Engine])

    val created = carCommandService.addCar(car)
    if(created == null) throw new ElementNotCreatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.CREATED).body(created)
  }

  @ApiOperation(value = "Update a Car.", response = classOf[Car])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PutMapping(value = Array("/{id}"))
  def updateCar(@ApiParam(name = "id", value = "The Car's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @RequestBody car: Car): ResponseEntity[_] = {
    val updated = carCommandService.updateCar(id, car)

    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Car's acceleration time.", response = classOf[Car])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("acceleration"))
  def updateCarAccelerationTime(@ApiParam(name = "id", value = "The Car's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "acceleration", value = "The acceleration time (from 0 to 100 KmH) of the Car.", required = true, example = "0.0") @RequestParam(name = "acceleration") accelerationTime : Double) = {
    val updated = carCommandService.updateCarAccelerationTime(id, accelerationTime)
    if(updated == null) throw new ElementNotUpdatedException[Car](classOf[Car])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  /*
  def updateCarApproachAngle(carId : Long, approachAngle : Double) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.approachAngle = approachAngle

    carRepository.save(carToUpdate)
  }

  def updateCarFuelConsumption(carId : Long, avgFuelConsumption : Double) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.avgFuelConsumption = avgFuelConsumption

    carRepository.save(carToUpdate)
  }

  def updateCarBackTrack(carId : Long, backTrack : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.backTrack = backTrack

    carRepository.save(carToUpdate)
  }

  def updateCarBody(carId : Long, body : String) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.bodyType = Body.valueOf(body)

    carRepository.save(carToUpdate)
  }

  def updateCarClimbAngle(carId : Long, climbAngle : Double) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.climbAngle = climbAngle

    carRepository.save(carToUpdate)
  }

  def updateCarDepartureAngle(carId : Long, departureAngle : Double) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.departureAngle = departureAngle

    carRepository.save(carToUpdate)
  }

  def updateCarDragCoefficient(carId : Long, dragCoefficient : Double) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.dragCoefficient = dragCoefficient

    carRepository.save(carToUpdate)
  }

  def updateCarDriveWheelConfiguration(carId : Long, driveWheelConfiguration : String) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.driveWheelConfiguration = WDEnum.valueOf(driveWheelConfiguration)

    carRepository.save(carToUpdate)
  }

  def updateCarEmissionCO2(carId : Long, emissionCO2 : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.emissionsCO2 = emissionCO2

    carRepository.save(carToUpdate)
  }

  def updateCarEnginePosition(carId : Long, position : String) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.enginePosition = EnginePosition.valueOf(position)

    carRepository.save(carToUpdate)
  }

  def updateCarFrontBreak(carId : Long, frontBreak : String) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.frontBreak = Break.valueOf(frontBreak)

    carRepository.save(carToUpdate)
  }

  def updateCarFrontOverhang(carId : Long, frontOverhang : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.frontOverhang = frontOverhang

    carRepository.save(carToUpdate)
  }

  def updateCarFrontSuspension(carId : Long, frontSuspension : String) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.frontSuspension = Suspension.valueOf(frontSuspension)

    carRepository.save(carToUpdate)
  }

  def updateCarFrontTrack(carId : Long, frontTrack : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.frontTrack = frontTrack

    carRepository.save(carToUpdate)
  }

  def updateCarFuelCapacity(carId : Long, fuelCapacity : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.fuelCapacity = fuelCapacity

    carRepository.save(carToUpdate)
  }

  def updateCarHeight(carId : Long, height : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.height = height

    carRepository.save(carToUpdate)
  }

  def updateCarKerbWeight(carId : Long, kerbWeight : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.kerbWeight = kerbWeight

    carRepository.save(carToUpdate)
  }

  def updateCarLength(carId : Long, length : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.length = length

    carRepository.save(carToUpdate)
  }

  def updateCarMaxSpeed(carId : Long, maxSpeed : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.maxSpeed = maxSpeed

    carRepository.save(carToUpdate)
  }

  def updateCarMaxWeight(carId : Long, maxWeight : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.maxWeight = maxWeight

    carRepository.save(carToUpdate)
  }

  def updateCarModelYear(carId : Long, modelYear : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.modelYear = modelYear

    carRepository.save(carToUpdate)
  }

  def updateCarName(carId : Long, name : String) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.name = name

    carRepository.save(carToUpdate)
  }

  def updateCarNumberOfAirbags(carId : Long, numberOfAirbags : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.numberOfAirbags = numberOfAirbags

    carRepository.save(carToUpdate)
  }

  def updateCarNumberOfAxles(carId : Long, numberOfAxles : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.numberOfAxles = numberOfAxles

    carRepository.save(carToUpdate)
  }

  def updateCarNumberOfDoors(carId : Long, numberOfDoors : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.numberOfDoors = numberOfDoors

    carRepository.save(carToUpdate)
  }

  def updateCarNumberOfForwardGears(carId : Long, numberOfForwardGears : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.numberOfForwardGears = numberOfForwardGears

    carRepository.save(carToUpdate)
  }

  def updateCarPayload(carId : Long, payload : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.payload = payload

    carRepository.save(carToUpdate)
  }

  def updateCarRampAngle(carId : Long, rampAngle : Double) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.rampAngle = rampAngle

    carRepository.save(carToUpdate)
  }

  def updateCarRearBreak(carId : Long, rearBreak : String) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.rearBreak = Break.valueOf(rearBreak)

    carRepository.save(carToUpdate)
  }

  def updateCarRearOverhang(carId : Long, rearOverhang : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.rearOverhang = rearOverhang

    carRepository.save(carToUpdate)
  }

  def updateCarRearSuspension(carId : Long, rearSuspension : String) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.rearSuspension = Suspension.valueOf(rearSuspension)

    carRepository.save(carToUpdate)
  }

  def updateCarRideHeight(carId : Long, rideHeight : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.rideHeight = rideHeight

    carRepository.save(carToUpdate)
  }

  def updateCarRimSize(carId : Long, rimsSize : String) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.rimsSize = rimsSize

    carRepository.save(carToUpdate)
  }

  def updateCarSeatingCapacity(carId : Long, seatingCapacity : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.seatingCapacity = seatingCapacity

    carRepository.save(carToUpdate)
  }

  def updateCarSerie(carId : Long, serie : Serie) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.serie = serie

    carRepository.save(carToUpdate)
  }

  def updateCarTireSize(carId : Long, tireSize : String) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.tireSize = tireSize

    carRepository.save(carToUpdate)
  }

  def updateCarTongueWeight(carId : Long, tongueWeight : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.tongueWeight = tongueWeight

    carRepository.save(carToUpdate)
  }

  def updateCarTrailerWeight(carId : Long, trailerWeight : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.trailerWeight = trailerWeight

    carRepository.save(carToUpdate)
  }

  def updateCarConfiguration(carId : Long, configuration : String) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.vehicleConfiguration = configuration

    carRepository.save(carToUpdate)
  }

  def updateCarTransmission(carId : Long, transmission : String) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.vehicleTransmission = Transmission.valueOf(transmission)

    carRepository.save(carToUpdate)
  }

  def updateCarWadingDepth(carId : Long, wadingDepth : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.wadingDepth = wadingDepth

    carRepository.save(carToUpdate)
  }

  def updateCarWheelBase(carId : Long, wheelBase : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.wheelBase = wheelBase

    carRepository.save(carToUpdate)
  }

  def updateCarWidth(carId : Long, width : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.width = width

    carRepository.save(carToUpdate)
  }

  def updateCarWidthFolded(carId : Long, widthFolded : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.widthFolded = widthFolded

    carRepository.save(carToUpdate)
  }

  def updateCarCargoVolume(carId : Long, cargoVolume : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.cargoVolume = cargoVolume

    carRepository.save(carToUpdate)
  }

  def updateCarEngine(carId : Long, engine : Engine) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.engine = engine
    carRepository.save(carToUpdate)
  }

  def updateCarFiles(carId : Long, files : util.List[File]) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.files.addAll(files)
    carRepository.save(carToUpdate)
  }

  def updateCarPhotos(carId : Long, photos : util.List[Photo]) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.images.addAll(photos)
    carRepository.save(carToUpdate)
  }

  def updateCarVideos(carId : Long, videos : util.List[Video]) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.videos.addAll(videos)
    carRepository.save(carToUpdate)
  }

  def updateCarAddFile(carId : Long, file: File) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.files.add(file)
    carRepository.save(carToUpdate)
  }

  def updateCarAddPhoto(carId : Long, photo : Photo) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.images.add(photo)
    carRepository.save(carToUpdate)
  }

  def updateCarAddVideo(carId : Long, video : Video) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.videos.add(video)
    carRepository.save(carToUpdate)
  }

*/

  @ApiOperation(value = "Delete the Car.", response = classOf[Car])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @DeleteMapping(Array("/{id}"))
  def deleteCar(@ApiParam(name = "id", value = "The Car ID.", required = true, example = "1") @PathVariable(value = "id") id : Long) = {
    carCommandService.deleteCar(id)
    ResponseEntity.status(HttpStatus.NO_CONTENT).build()
  }
}
