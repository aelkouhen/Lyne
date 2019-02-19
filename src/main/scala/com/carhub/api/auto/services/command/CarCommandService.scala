package com.carhub.api.auto.services.command

import java.util

import com.carhub.api.auto.domain._
import com.carhub.api.auto.repositories.{CarRepository, EngineRepository}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Autowired
@Transactional
@Service
class CarCommandService(carRepository: CarRepository, engineRepository: EngineRepository){

  def addCar (car :Car) = carRepository.save(car)

  def updateCar(car : Car) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.engine = car.engine
    carToUpdate.accelerationTime = car.accelerationTime
    carToUpdate.approachAngle = car.approachAngle
    carToUpdate.avgFuelConsumption = car.avgFuelConsumption
    carToUpdate.backTrack = car.backTrack
    carToUpdate.bodyType = car.bodyType
    carToUpdate.cargoVolume = car.cargoVolume
    carToUpdate.climbAngle = car.climbAngle
    carToUpdate.departureAngle = car.departureAngle
    carToUpdate.dragCoefficient = car.dragCoefficient
    carToUpdate.driveWheelConfiguration = car.driveWheelConfiguration
    carToUpdate.emissionsCO2 = car.emissionsCO2
    carToUpdate.enginePosition = car.enginePosition
    carToUpdate.frontBreak = car.frontBreak
    carToUpdate.frontOverhang = car.frontOverhang
    carToUpdate.frontSuspension = car.frontSuspension
    carToUpdate.frontTrack = car.frontTrack
    carToUpdate.fuelCapacity = car.fuelCapacity
    carToUpdate.height = car.height
    carToUpdate.kerbWeight = car.kerbWeight
    carToUpdate.length = car.length
    carToUpdate.maxSpeed = car.maxSpeed
    carToUpdate.maxWeight = car.maxWeight
    carToUpdate.modelYear = car.modelYear
    carToUpdate.name = car.name
    carToUpdate.numberOfAirbags = car.numberOfAirbags
    carToUpdate.numberOfAxles = car.numberOfAxles
    carToUpdate.numberOfDoors = car.numberOfDoors
    carToUpdate.numberOfForwardGears = car.numberOfForwardGears
    carToUpdate.payload = car.payload
    carToUpdate.rampAngle = car.rampAngle
    carToUpdate.rearBreak = car.rearBreak
    carToUpdate.rearOverhang = car.rearOverhang
    carToUpdate.rearBreak = car.rearBreak
    carToUpdate.rearSuspension = car.rearSuspension
    carToUpdate.rideHeight = car.rideHeight
    carToUpdate.rimsSize = car.rimsSize
    carToUpdate.seatingCapacity = car.seatingCapacity
    carToUpdate.serie = car.serie
    carToUpdate.tireSize = car.tireSize
    carToUpdate.tongueWeight = car.tongueWeight
    carToUpdate.trailerWeight = car.trailerWeight
    carToUpdate.vehicleConfiguration = car.vehicleConfiguration
    carToUpdate.vehicleTransmission = car.vehicleTransmission
    carToUpdate.wadingDepth = car.wadingDepth
    carToUpdate.wheelBase = car.wheelBase
    carToUpdate.width = car.width
    carToUpdate.widthFolded = car.widthFolded

    carToUpdate.files.addAll(car.files)
    carToUpdate.images.addAll(car.images)
    carToUpdate.videos.addAll(car.videos)

    carRepository.save(carToUpdate)
  }

  def updateCarAccelerationTime(car : Car, accelerationTime : Double) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.accelerationTime = accelerationTime

    carRepository.save(carToUpdate)
  }

  def updateCarApproachAngle(car : Car, approachAngle : Double) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.approachAngle = approachAngle

    carRepository.save(carToUpdate)
  }

  def updateCarFuelConsumption(car : Car, avgFuelConsumption : Double) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.avgFuelConsumption = avgFuelConsumption

    carRepository.save(carToUpdate)
  }

  def updateCarBackTrack(car : Car, backTrack : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.backTrack = backTrack

    carRepository.save(carToUpdate)
  }

  def updateCarBody(car : Car, body : String) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.bodyType = Body.valueOf(body)

    carRepository.save(carToUpdate)
  }

  def updateCarClimbAngle(car : Car, climbAngle : Double) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.climbAngle = climbAngle

    carRepository.save(carToUpdate)
  }

  def updateCarDepartureAngle(car : Car, departureAngle : Double) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.departureAngle = departureAngle

    carRepository.save(carToUpdate)
  }

  def updateCarDragCoefficient(car : Car, dragCoefficient : Double) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.dragCoefficient = dragCoefficient

    carRepository.save(carToUpdate)
  }

  def updateCarDriveWheelConfiguration(car : Car, driveWheelConfiguration : String) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.driveWheelConfiguration = WDEnum.valueOf(driveWheelConfiguration)

    carRepository.save(carToUpdate)
  }

  def updateCarEmissionCO2(car : Car, emissionCO2 : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.emissionsCO2 = emissionCO2

    carRepository.save(carToUpdate)
  }

  def updateCarEnginePosition(car : Car, position : String) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.enginePosition = EnginePosition.valueOf(position)

    carRepository.save(carToUpdate)
  }

  def updateCarFrontBreak(car : Car, frontBreak : String) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.frontBreak = Break.valueOf(frontBreak)

    carRepository.save(carToUpdate)
  }

  def updateCarFrontOverhang(car : Car, frontOverhang : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.frontOverhang = frontOverhang

    carRepository.save(carToUpdate)
  }

  def updateCarFrontSuspension(car : Car, frontSuspension : String) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.frontSuspension = Suspension.valueOf(frontSuspension)

    carRepository.save(carToUpdate)
  }

  def updateCarFrontTrack(car : Car, frontTrack : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.frontTrack = frontTrack

    carRepository.save(carToUpdate)
  }

  def updateCarFuelCapacity(car : Car, fuelCapacity : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.fuelCapacity = fuelCapacity

    carRepository.save(carToUpdate)
  }

  def updateCarHeight(car : Car, height : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.height = height

    carRepository.save(carToUpdate)
  }

  def updateCarKerbWeight(car : Car, kerbWeight : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.kerbWeight = kerbWeight

    carRepository.save(carToUpdate)
  }

  def updateCarLength(car : Car, length : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.length = length

    carRepository.save(carToUpdate)
  }

  def updateCarMaxSpeed(car : Car, maxSpeed : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.maxSpeed = maxSpeed

    carRepository.save(carToUpdate)
  }

  def updateCarMaxWeight(car : Car, maxWeight : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.maxWeight = maxWeight

    carRepository.save(carToUpdate)
  }

  def updateCarModelYear(car : Car, modelYear : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.modelYear = modelYear

    carRepository.save(carToUpdate)
  }

  def updateCarName(car : Car, name : String) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.name = name

    carRepository.save(carToUpdate)
  }

  def updateCarNumberOfAirbags(car : Car, numberOfAirbags : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.numberOfAirbags = numberOfAirbags

    carRepository.save(carToUpdate)
  }

  def updateCarNumberOfAxles(car : Car, numberOfAxles : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.numberOfAxles = numberOfAxles

    carRepository.save(carToUpdate)
  }

  def updateCarNumberOfDoors(car : Car, numberOfDoors : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.numberOfDoors = numberOfDoors

    carRepository.save(carToUpdate)
  }

  def updateCarNumberOfForwardGears(car : Car, numberOfForwardGears : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.numberOfForwardGears = numberOfForwardGears

    carRepository.save(carToUpdate)
  }

  def updateCarPayload(car : Car, payload : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.payload = payload

    carRepository.save(carToUpdate)
  }

  def updateCarRampAngle(car : Car, rampAngle : Double) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.rampAngle = rampAngle

    carRepository.save(carToUpdate)
  }

  def updateCarRearBreak(car : Car, rearBreak : String) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.rearBreak = Break.valueOf(rearBreak)

    carRepository.save(carToUpdate)
  }

  def updateCarRearOverhang(car : Car, rearOverhang : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.rearOverhang = rearOverhang

    carRepository.save(carToUpdate)
  }

  def updateCarRearSuspension(car : Car, rearSuspension : String) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.rearSuspension = Suspension.valueOf(rearSuspension)

    carRepository.save(carToUpdate)
  }

  def updateCarRideHeight(car : Car, rideHeight : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.rideHeight = rideHeight

    carRepository.save(carToUpdate)
  }

  def updateCarRimSize(car : Car, rimsSize : String) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.rimsSize = rimsSize

    carRepository.save(carToUpdate)
  }

  def updateCarSeatingCapacity(car : Car, seatingCapacity : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.seatingCapacity = seatingCapacity

    carRepository.save(carToUpdate)
  }

  def updateCarSerie(car : Car, serie : Serie) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.serie = serie

    carRepository.save(carToUpdate)
  }

  def updateCarTireSize(car : Car, tireSize : String) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.tireSize = tireSize

    carRepository.save(carToUpdate)
  }

  def updateCarTongueWeight(car : Car, tongueWeight : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.tongueWeight = tongueWeight

    carRepository.save(carToUpdate)
  }

  def updateCarTrailerWeight(car : Car, trailerWeight : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.trailerWeight = trailerWeight

    carRepository.save(carToUpdate)
  }

  def updateCarConfiguration(car : Car, configuration : String) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.vehicleConfiguration = configuration

    carRepository.save(carToUpdate)
  }

  def updateCarTransmission(car : Car, transmission : String) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.vehicleTransmission = Transmission.valueOf(transmission)

    carRepository.save(carToUpdate)
  }

  def updateCarWadingDepth(car : Car, wadingDepth : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.wadingDepth = wadingDepth

    carRepository.save(carToUpdate)
  }

  def updateCarWheelBase(car : Car, wheelBase : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.wheelBase = wheelBase

    carRepository.save(carToUpdate)
  }

  def updateCarWidth(car : Car, width : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.width = width

    carRepository.save(carToUpdate)
  }

  def updateCarWidthFolded(car : Car, widthFolded : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.widthFolded = widthFolded

    carRepository.save(carToUpdate)
  }

  def updateCarCargoVolume(car : Car, cargoVolume : Int) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.cargoVolume = cargoVolume

    carRepository.save(carToUpdate)
  }

  def updateCarEngine(car : Car, engine : Engine) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.engine = engine
    carRepository.save(carToUpdate)
  }

  def updateCarFiles(car : Car, files : util.List[File]) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.files.addAll(files)
    carRepository.save(carToUpdate)
  }

  def updateCarPhotos(car : Car, photos : util.List[Photo]) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.images.addAll(photos)
    carRepository.save(carToUpdate)
  }

  def updateCarVideos(car : Car, videos : util.List[Video]) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.videos.addAll(videos)
    carRepository.save(carToUpdate)
  }

  def updateCarAddFile(car : Car, file: File) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.files.add(file)
    carRepository.save(carToUpdate)
  }

  def updateCarAddPhoto(car : Car, photo : Photo) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.images.add(photo)
    carRepository.save(carToUpdate)
  }

  def updateCarAddVideo(car : Car, video : Video) = {
    val carToUpdate = carRepository.getOne(car.id)
    carToUpdate.videos.add(video)
    carRepository.save(carToUpdate)
  }

  def deleteCar(car : Car)= carRepository.delete(car)
}
