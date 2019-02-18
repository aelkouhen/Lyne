package com.carhub.api

import java.text.SimpleDateFormat
import java.util.{Calendar, Locale}

import com.carhub.api.auto.domain._
import com.carhub.api.auto.services.command._
import com.carhub.api.auto.domain.{File, Photo, Video}
import com.google.common.io.Files
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.{ApplicationArguments, ApplicationRunner}
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component

@Autowired
@Component
class AutoInitialDataLoader(carCommandService : CarCommandService,
                            engineCommandService : EngineCommandService,
                            fileCommandService : FileCommandService,
                            makeCommandService : MakeCommandService,
                            modelCommandService : ModelCommandService,
                            photoCommandService : PhotoCommandService,
                            serieCommandService : SerieCommandService,
                            videoCommandService : VideoCommandService)
                            extends ApplicationRunner {

  def run(args: ApplicationArguments): Unit = {
    val make = new Make()
    make.name = "Nissan"
    make.oldName = "Datsun"
    var format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)
    var date = format.parse("26/12/1933")
    make.foundationDate = date
    make.about = "Nissan Motor Company, Limited, Nissan est un constructeur automobile japonais né sous le nom de Datsun. Son siège social est à Yokohama depuis 2010. Il est lié au constructeur français Renault depuis 1999 à travers l'Alliance Renault-Nissan qui est au premier semestre 2017, le premier groupe automobile mondial."
    make.founder = "Yoshisuke Aikawa"
    make.logo = createPhoto()

    makeCommandService.addMake(make)


    val xtrailModel = new Model()
    xtrailModel.make = make
    xtrailModel.name = "X-Trail"
    format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)
    date = format.parse("01/01/2001")
    xtrailModel.creationDate = date
    xtrailModel.generation = "III"

    modelCommandService.addModel(xtrailModel)

    val serie = new Serie()
    serie.model = xtrailModel
    serie.name = "1.6 dCi (130 Hp) Xtronic"
    serie.productionEndYear = 2017
    serie.productionStartYear = 2014

    serieCommandService.addSerie(serie)

    val car = new Car()
    car.accelerationTime = 11.4
    car.avgFuelConsumption = 5.1
    car.numberOfDoors = 5
    car.maxSpeed = 240
    car.fuelCapacity = 60
    car.modelYear = 2017
    car.bodyType = Body.SUV
    car.seatingCapacity = 7
    car.length = 4690
    car.width = 1820
    car.height = 1740
    car.wheelBase = 2705
    car.rideHeight = 210
    car.enginePosition = EnginePosition.FRONT_TRANSVERSELY

    val engine = new Engine
    engine.name = "1.6 dCi"
    engine.engineDisplacement = 1598
    engine.torque = 320
    engine.injectionSystem = InjectionSystem.CRDI
    engine.turbineSystem = TurbineSystem.TURBO
    engine.positionOfCylinders = CylinderPosition.INLINE
    engine.numberOfCylinders = 4
    engine.valvesPerCylinder = 4
    engine.fuelType = Fuel.DIESEL

    engineCommandService.addEngine(engine)

    car.engine = engine
    car.driveWheelConfiguration = WDEnum.FRONT_2WD
    car.vehicleTransmission = Transmission.AT
    car.frontSuspension = Suspension.MCPHERSON_SPRING_WITH_STABILIZER
    car.rearSuspension = Suspension.MULTI_LINK_SPRING
    car.frontBreak = Break.VENTILATED_DISC
    car.rearBreak = Break.VENTILATED_DISC
    car.emissionsCO2 = 135
    car.kerbWeight = 1565
    car.maxWeight = 2140
    car.trailerWeight = 1500
    car.tireSize = "225/65 R17; 225/60 R18; 225/55 R19"
    car.rimsSize = "R17; R18; R19"
    car.serie = serie
    car.images.add({
      val photo = new Photo
      val picture = new ClassPathResource("images/xtrail.jpg")
      val inputStream = picture.getInputStream
      val arrayPic = Stream.continually(inputStream.read).takeWhile(-1 !=).map(_.toByte).toArray
      inputStream.close()
      photo.size = picture.contentLength()
      photo.extension = Files.getFileExtension(picture.getFilename)
      photo.caption = "Xtrail"
      photo.created = Calendar.getInstance().getTime()
      photo.content = arrayPic
      photoCommandService.addPhoto(photo)

      photo
    })

    car.images.add({
      val photo = new Photo
      val picture = new ClassPathResource("images/nissan-x-trail.jpg")
      val inputStream = picture.getInputStream
      val arrayPic = Stream.continually(inputStream.read).takeWhile(-1 !=).map(_.toByte).toArray
      inputStream.close()
      photo.size = picture.contentLength()
      photo.extension = Files.getFileExtension(picture.getFilename)
      photo.caption = "Xtrail"
      photo.created = Calendar.getInstance().getTime()
      photo.content = arrayPic
      photoCommandService.addPhoto(photo)

      photo
    })

    car.videos.add({
      val video = new Video
      video.caption = "Technical Spec. Video"
      video.created = Calendar.getInstance().getTime()
      video.link = "https://youtu.be/9u9x4kveojU"
      videoCommandService.addVideo(video)

      video
    })

    car.files.add({
      val file = new File
      val brochure = new ClassPathResource("files/Brochure_XTRAIL.pdf")
      val inputStream = brochure.getInputStream
      val arrayPic = Stream.continually(inputStream.read).takeWhile(-1 !=).map(_.toByte).toArray
      inputStream.close()
      file.size = brochure.contentLength()
      file.extension = Files.getFileExtension(brochure.getFilename)
      file.caption = "Brochure"
      file.created = Calendar.getInstance().getTime()
      file.content = arrayPic
      fileCommandService.addFile(file)

      file
    })

    carCommandService.addCar(car)
  }

  private def createPhoto() = {
    val photo = new Photo
    val picture = new ClassPathResource("images/nissan_logo.png")
    val inputStream = picture.getInputStream
    val arrayPic = Stream.continually(inputStream.read).takeWhile(-1 !=).map(_.toByte).toArray
    inputStream.close()
    photo.size = picture.contentLength()
    photo.extension = Files.getFileExtension(picture.getFilename)
    photo.caption = "Nissan's logo"
    photo.created = Calendar.getInstance().getTime()
    photo.content = arrayPic
    photoCommandService.addPhoto(photo)

    photo
  }

}
