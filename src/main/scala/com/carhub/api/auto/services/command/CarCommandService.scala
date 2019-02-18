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
