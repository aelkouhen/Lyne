package com.carhub.api.auto.services.command

import java.awt.image.BufferedImage
import java.util.{Calendar, UUID}

import com.carhub.api.auto.domain._
import com.carhub.api.auto.domain.dto.{File, Photo, Video}
import com.carhub.api.auto.domain.enumerations._
import com.carhub.api.auto.repositories.CarRepository
import com.carhub.api.auto.services.query._
import com.carhub.api.auto.utils.jwt.JwtUtil
import com.google.common.io.Files
import com.google.common.net.HttpHeaders
import javax.imageio.ImageIO
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.cloud.client.ServiceInstance
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient

@Autowired
@Transactional
@Service
class CarCommandService(carRepository: CarRepository,
                        engineCommandService: EngineCommandService,
                        serieQueryService: SerieQueryService){

  val mediaServiceInstance : ServiceInstance = null

  @Value("${security.oauth2.resource.token-type}")
  val tokenType : String = null

  @Value("${media.photo-endpoint}")
  val photoEndpoint : String = null

  @Value("${media.file-endpoint}")
  val fileEndpoint : String = null

  @Value("${media.video-endpoint}")
  val videoEndpoint : String = null

  def addCar (car :Car) = {
    if(car.engine != null) engineCommandService.addEngine(car.engine)
    carRepository.save(car)
  }

  def updateCar(carId : UUID, car : Car) = {
    val carToUpdate = carRepository.getOne(carId)
    if(car.engine != null) engineCommandService.addEngine(car.engine)
    carToUpdate.engine = car.engine
    carToUpdate.accelerationTime = car.accelerationTime
    carToUpdate.approachAngle = car.approachAngle
    carToUpdate.avgFuelConsumption = car.avgFuelConsumption
    carToUpdate.backTrack = car.backTrack
    carToUpdate.bodyShape = car.bodyShape
    carToUpdate.cargoVolume = car.cargoVolume
    carToUpdate.climbAngle = car.climbAngle
    carToUpdate.departureAngle = car.departureAngle
    carToUpdate.dragCoefficient = car.dragCoefficient
    carToUpdate.driveWheelConfiguration = car.driveWheelConfiguration
    carToUpdate.emissionsCO2 = car.emissionsCO2
    carToUpdate.enginePosition = car.enginePosition
    carToUpdate.frontBreak = car.frontBreak
    carToUpdate.frontOverhang = car.frontOverhang
    carToUpdate.frontSuspensionSystem = car.frontSuspensionSystem
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
    carToUpdate.rearSuspensionSystem = car.rearSuspensionSystem
    carToUpdate.rideHeight = car.rideHeight
    carToUpdate.rimsSize = car.rimsSize
    carToUpdate.seatingCapacity = car.seatingCapacity
    carToUpdate.serie = car.serie
    carToUpdate.tireSize = car.tireSize
    carToUpdate.tongueWeight = car.tongueWeight
    carToUpdate.trailerWeight = car.trailerWeight
    carToUpdate.vehicleConfiguration = car.vehicleConfiguration
    carToUpdate.transmissionMode = car.transmissionMode
    carToUpdate.wadingDepth = car.wadingDepth
    carToUpdate.wheelBase = car.wheelBase
    carToUpdate.width = car.width
    carToUpdate.widthFolded = car.widthFolded

    carToUpdate.files.addAll(car.files)
    carToUpdate.images.addAll(car.images)
    carToUpdate.videos.addAll(car.videos)

    carRepository.save(carToUpdate)
  }

  def updateCarAccelerationTime(carId : UUID, accelerationTime : Double) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.accelerationTime = accelerationTime

    carRepository.save(carToUpdate)
  }

  def updateCarApproachAngle(carId : UUID, approachAngle : Double) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.approachAngle = approachAngle

    carRepository.save(carToUpdate)
  }

  def updateCarFuelConsumption(carId : UUID, avgFuelConsumption : Double) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.avgFuelConsumption = avgFuelConsumption

    carRepository.save(carToUpdate)
  }

  def updateCarBackTrack(carId : UUID, backTrack : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.backTrack = backTrack

    carRepository.save(carToUpdate)
  }

  def updateCarBody(carId : UUID, body : String) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.bodyShape = Body.valueOf(body)

    carRepository.save(carToUpdate)
  }

  def updateCarClimbAngle(carId : UUID, climbAngle : Double) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.climbAngle = climbAngle

    carRepository.save(carToUpdate)
  }

  def updateCarDepartureAngle(carId : UUID, departureAngle : Double) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.departureAngle = departureAngle

    carRepository.save(carToUpdate)
  }

  def updateCarDragCoefficient(carId : UUID, dragCoefficient : Double) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.dragCoefficient = dragCoefficient

    carRepository.save(carToUpdate)
  }

  def updateCarDriveWheelConfiguration(carId : UUID, driveWheelConfiguration : String) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.driveWheelConfiguration = DriveWheel.valueOf(driveWheelConfiguration)

    carRepository.save(carToUpdate)
  }

  def updateCarEmissionCO2(carId : UUID, emissionCO2 : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.emissionsCO2 = emissionCO2

    carRepository.save(carToUpdate)
  }

  def updateCarEnginePosition(carId : UUID, position : String) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.enginePosition = EnginePosition.valueOf(position)

    carRepository.save(carToUpdate)
  }

  def updateCarFrontBreak(carId : UUID, frontBreak : String) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.frontBreak = BreakType.valueOf(frontBreak)

    carRepository.save(carToUpdate)
  }

  def updateCarFrontOverhang(carId : UUID, frontOverhang : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.frontOverhang = frontOverhang

    carRepository.save(carToUpdate)
  }

  def updateCarFrontSuspension(carId : UUID, frontSuspension : String) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.frontSuspensionSystem = SuspensionSystem.valueOf(frontSuspension)

    carRepository.save(carToUpdate)
  }

  def updateCarFrontTrack(carId : UUID, frontTrack : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.frontTrack = frontTrack

    carRepository.save(carToUpdate)
  }

  def updateCarFuelCapacity(carId : UUID, fuelCapacity : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.fuelCapacity = fuelCapacity

    carRepository.save(carToUpdate)
  }

  def updateCarHeight(carId : UUID, height : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.height = height

    carRepository.save(carToUpdate)
  }

  def updateCarKerbWeight(carId : UUID, kerbWeight : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.kerbWeight = kerbWeight

    carRepository.save(carToUpdate)
  }

  def updateCarLength(carId : UUID, length : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.length = length

    carRepository.save(carToUpdate)
  }

  def updateCarMaxSpeed(carId : UUID, maxSpeed : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.maxSpeed = maxSpeed

    carRepository.save(carToUpdate)
  }

  def updateCarMaxWeight(carId : UUID, maxWeight : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.maxWeight = maxWeight

    carRepository.save(carToUpdate)
  }

  def updateCarModelYear(carId : UUID, modelYear : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.modelYear = modelYear

    carRepository.save(carToUpdate)
  }

  def updateCarName(carId : UUID, name : String) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.name = name

    carRepository.save(carToUpdate)
  }

  def updateCarNumberOfAirbags(carId : UUID, numberOfAirbags : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.numberOfAirbags = numberOfAirbags

    carRepository.save(carToUpdate)
  }

  def updateCarNumberOfAxles(carId : UUID, numberOfAxles : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.numberOfAxles = numberOfAxles

    carRepository.save(carToUpdate)
  }

  def updateCarNumberOfDoors(carId : UUID, numberOfDoors : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.numberOfDoors = numberOfDoors

    carRepository.save(carToUpdate)
  }

  def updateCarNumberOfForwardGears(carId : UUID, numberOfForwardGears : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.numberOfForwardGears = numberOfForwardGears

    carRepository.save(carToUpdate)
  }

  def updateCarPayload(carId : UUID, payload : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.payload = payload

    carRepository.save(carToUpdate)
  }

  def updateCarRampAngle(carId : UUID, rampAngle : Double) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.rampAngle = rampAngle

    carRepository.save(carToUpdate)
  }

  def updateCarRearBreak(carId : UUID, rearBreak : String) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.rearBreak = BreakType.valueOf(rearBreak)

    carRepository.save(carToUpdate)
  }

  def updateCarRearOverhang(carId : UUID, rearOverhang : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.rearOverhang = rearOverhang

    carRepository.save(carToUpdate)
  }

  def updateCarRearSuspension(carId : UUID, rearSuspension : String) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.rearSuspensionSystem = SuspensionSystem.valueOf(rearSuspension)

    carRepository.save(carToUpdate)
  }

  def updateCarRideHeight(carId : UUID, rideHeight : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.rideHeight = rideHeight

    carRepository.save(carToUpdate)
  }

  def updateCarRimSize(carId : UUID, rimsSize : String) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.rimsSize = rimsSize

    carRepository.save(carToUpdate)
  }

  def updateCarSeatingCapacity(carId : UUID, seatingCapacity : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.seatingCapacity = seatingCapacity

    carRepository.save(carToUpdate)
  }

  def updateCarSerie(carId : UUID, serieId : UUID) : Car = {
    val serie = serieQueryService.findSerieById(serieId)
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.serie = serie

    carRepository.save(carToUpdate)
  }

  def updateCarSerie(carId : UUID, serie: Serie) : Car = {
    updateCarSerie(carId, serie.id)
  }

  def updateCarTireSize(carId : UUID, tireSize : String) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.tireSize = tireSize

    carRepository.save(carToUpdate)
  }

  def updateCarTongueWeight(carId : UUID, tongueWeight : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.tongueWeight = tongueWeight

    carRepository.save(carToUpdate)
  }

  def updateCarTrailerWeight(carId : UUID, trailerWeight : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.trailerWeight = trailerWeight

    carRepository.save(carToUpdate)
  }

  def updateCarConfiguration(carId : UUID, configuration : String) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.vehicleConfiguration = configuration

    carRepository.save(carToUpdate)
  }

  def updateCarTransmission(carId : UUID, transmission : String) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.transmissionMode = TransmissionMode.valueOf(transmission)

    carRepository.save(carToUpdate)
  }

  def updateCarWadingDepth(carId : UUID, wadingDepth : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.wadingDepth = wadingDepth

    carRepository.save(carToUpdate)
  }

  def updateCarWheelBase(carId : UUID, wheelBase : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.wheelBase = wheelBase

    carRepository.save(carToUpdate)
  }

  def updateCarWidth(carId : UUID, width : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.width = width

    carRepository.save(carToUpdate)
  }

  def updateCarWidthFolded(carId : UUID, widthFolded : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.widthFolded = widthFolded

    carRepository.save(carToUpdate)
  }

  def updateCarCargoVolume(carId : UUID, cargoVolume : Int) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.cargoVolume = cargoVolume

    carRepository.save(carToUpdate)
  }

  def updateCarEngine(carId : UUID, engine : Engine) = {
    val engineToAdd = engineCommandService.addEngine(engine)
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.engine = engineToAdd
    carRepository.save(carToUpdate)
  }


  def updateCarUploadFile(carId : UUID, file: MultipartFile) = {
    val fileToAdd = addFile(file)
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.files.add(fileToAdd)
    carRepository.save(carToUpdate)
  }

  def updateCarUploadPhoto(carId : UUID, photo : MultipartFile) = {
    val photoToAdd = addPhoto(photo)
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.images.add(photoToAdd)
    carRepository.save(carToUpdate)
  }

  def updateCarUploadVideo(carId : UUID, video : MultipartFile) = {
    val videoToAdd = addVideo(video)
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.videos.add(videoToAdd)
    carRepository.save(carToUpdate)
  }

  def updateCarUploadFile(carId : UUID, fileId: UUID) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.files.add(fileId)
    carRepository.save(carToUpdate)
  }

  def updateCarUploadPhoto(carId : UUID, photoId : UUID) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.images.add(photoId)
    carRepository.save(carToUpdate)
  }

  def updateCarUploadVideo(carId : UUID, videoId : UUID) = {
    val carToUpdate = carRepository.getOne(carId)
    carToUpdate.videos.add(videoId)
    carRepository.save(carToUpdate)
  }

  def deleteCar(carId : UUID) = {
    val carToDelete = carRepository.getOne(carId)
    if (carToDelete != null) carRepository.delete(carToDelete)
  }

  def addPhoto(photo: MultipartFile): UUID ={
    val photoMeta = new Photo
    photoMeta.size = photo.getSize
    photoMeta.name = Files.getNameWithoutExtension(photo.getOriginalFilename)
    photoMeta.format = Files.getFileExtension(photo.getOriginalFilename)
    photoMeta.content = photo.getBytes
    photoMeta.mimeType = photo.getContentType
    photoMeta.created = Calendar.getInstance().getTime()

    val bimg : BufferedImage = ImageIO.read(photo.getInputStream)
    photoMeta.width = bimg.getWidth
    photoMeta.height = bimg.getHeight

    val client = WebClient.builder()
      .baseUrl(mediaServiceInstance.getUri.toString)
      .defaultHeader(HttpHeaders.AUTHORIZATION, tokenType + " " + JwtUtil.token())
      .build()

    var request = client
      .method(HttpMethod.POST)
      .uri(photoEndpoint).body(BodyInserters.fromObject(photo))

    val p = request.retrieve()
      .bodyToMono(classOf[Photo])
      .block()

    p.id
  }

  def addVideo(video: MultipartFile): UUID ={
    val photoMeta = new Photo
    photoMeta.size = video.getSize
    photoMeta.name = Files.getNameWithoutExtension(video.getOriginalFilename)
    photoMeta.format = Files.getFileExtension(video.getOriginalFilename)
    photoMeta.content = video.getBytes
    photoMeta.mimeType = video.getContentType
    photoMeta.created = Calendar.getInstance().getTime()

    val bimg : BufferedImage = ImageIO.read(video.getInputStream)
    photoMeta.width = bimg.getWidth
    photoMeta.height = bimg.getHeight

    val client = WebClient.builder()
      .baseUrl(mediaServiceInstance.getUri.toString)
      .defaultHeader(HttpHeaders.AUTHORIZATION, tokenType + " " + JwtUtil.token())
      .build()

    var request = client
      .method(HttpMethod.POST)
      .uri(videoEndpoint).body(BodyInserters.fromObject(video))

    val v = request.retrieve()
      .bodyToMono(classOf[Video])
      .block()

    v.id
  }

  def addFile(file: MultipartFile): UUID ={
    val photoMeta = new Photo
    photoMeta.size = file.getSize
    photoMeta.name = Files.getNameWithoutExtension(file.getOriginalFilename)
    photoMeta.format = Files.getFileExtension(file.getOriginalFilename)
    photoMeta.content = file.getBytes
    photoMeta.mimeType = file.getContentType
    photoMeta.created = Calendar.getInstance().getTime()

    val client = WebClient.builder()
      .baseUrl(mediaServiceInstance.getUri.toString)
      .defaultHeader(HttpHeaders.AUTHORIZATION, tokenType + " " + JwtUtil.token())
      .build()

    var request = client
      .method(HttpMethod.POST)
      .uri(fileEndpoint).body(BodyInserters.fromObject(file))

    val f = request.retrieve()
      .bodyToMono(classOf[File])
      .block()

    f.id
  }
}
