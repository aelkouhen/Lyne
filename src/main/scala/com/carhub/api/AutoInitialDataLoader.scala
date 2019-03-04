package com.carhub.api

import java.awt.image.BufferedImage
import java.net.URLConnection
import java.text.SimpleDateFormat
import java.util.{Calendar, Locale}

import com.carhub.api.auto.domain._
import com.carhub.api.auto.services.command._
import com.carhub.api.auto.domain.{File, Photo, Video}
import com.google.common.io.Files
import javax.imageio.ImageIO
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

    var car = new Car()
    car.name = "Nissan X-Trail 1.6 dCi (130 Hp) Xtronic"
    car.accelerationTime = 11.4
    car.avgFuelConsumption = 5.1
    car.numberOfDoors = 5
    car.maxSpeed = 240
    car.fuelCapacity = 60
    car.modelYear = 2017
    car.body = Body.SUV
    car.seatingCapacity = 7
    car.length = 4690
    car.width = 1820
    car.height = 1740
    car.wheelBase = 2705
    car.rideHeight = 210
    car.enginePosition = EnginePosition.FRONT_TRANSVERSELY
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
      val picture = new ClassPathResource("images/nissan-x-trail.jpg")
      var inputStream = picture.getInputStream
      val bimg : BufferedImage = ImageIO.read(inputStream)
      photo.width = bimg.getWidth
      photo.height = bimg.getHeight
      inputStream = picture.getInputStream
      val arrayPic = Stream.continually(inputStream.read).takeWhile(-1 !=).map(_.toByte).toArray
      inputStream.close()
      val connection = picture.getURL.openConnection
      photo.mimeType = connection.getContentType
      photo.size = picture.contentLength
      photo.name = Files.getNameWithoutExtension(picture.getFilename)
      photo.format = Files.getFileExtension(picture.getFilename)
      photo.caption = "Xtrail"
      photo.created = Calendar.getInstance().getTime()
      photo.content = arrayPic
      photoCommandService.addPhoto(photo)

      photo
    })

    carCommandService.addCar(car)

    val engine = new Engine
    engine.name = "1.6 dCi"
    engine.engineDisplacement = 1598
    engine.torque = 320
    engine.injection = Injection.CRDI
    engine.turbine = Turbine.TURBO
    engine.cylinders = Cylinder.INLINE
    engine.numberOfCylinders = 4
    engine.valvesPerCylinder = 4
    engine.fuel = Fuel.DIESEL

    engineCommandService.addEngine(engine)
    carCommandService.updateCarEngine(car.id, engine)


    val photo = new Photo
    val picture = new ClassPathResource("images/xtrail.jpg")
    var inputStream = picture.getInputStream
    val bimg : BufferedImage = ImageIO.read(inputStream)
    photo.width = bimg.getWidth
    photo.height = bimg.getHeight
    inputStream = picture.getInputStream
    var arrayPic = Stream.continually(inputStream.read).takeWhile(-1 !=).map(_.toByte).toArray
    inputStream.close()
    photo.size = picture.contentLength
    var connection = picture.getURL.openConnection
    photo.mimeType = connection.getContentType
    photo.name = Files.getNameWithoutExtension(picture.getFilename)
    photo.format = Files.getFileExtension(picture.getFilename)
    photo.caption = "Xtrail"
    photo.created = Calendar.getInstance().getTime()
    photo.content = arrayPic
    photoCommandService.addPhoto(photo)

    carCommandService.updateCarUploadPhoto(car.id, photo)


    val video = new Video
    video.name = "TS"
    video.caption = "Technical Spec. Video"
    video.created = Calendar.getInstance().getTime()
    video.url = "https://youtu.be/9u9x4kveojU"
    videoCommandService.addVideo(video)

    carCommandService.updateCarUploadVideo(car.id, video)

    val file = new File
    val brochure = new ClassPathResource("files/Brochure_XTRAIL.pdf")
    inputStream = brochure.getInputStream
    arrayPic = Stream.continually(inputStream.read).takeWhile(-1 !=).map(_.toByte).toArray
    inputStream.close()
    file.name = Files.getNameWithoutExtension(brochure.getFilename)
    file.size = brochure.contentLength
    file.format = Files.getFileExtension(brochure.getFilename)
    connection = brochure.getURL.openConnection
    file.mimeType = connection.getContentType
    file.caption = "Brochure"
    file.created = Calendar.getInstance().getTime()
    file.content = arrayPic
    fileCommandService.addFile(file)


    carCommandService.updateCarUploadFile(car.id, file)
  }

  private def createPhoto() = {
    val photo = new Photo
    val picture = new ClassPathResource("images/nissan_logo.png")
    var inputStream = picture.getInputStream
    val bimg : BufferedImage = ImageIO.read(inputStream)
    inputStream = picture.getInputStream
    photo.width = bimg.getWidth
    photo.height = bimg.getHeight
    val arrayPic = Stream.continually(inputStream.read).takeWhile(-1 !=).map(_.toByte).toArray
    inputStream.close()
    val connection = picture.getURL.openConnection
    photo.mimeType = connection.getContentType
    photo.size = picture.contentLength
    photo.name = Files.getNameWithoutExtension(picture.getFilename)
    photo.format = Files.getFileExtension(picture.getFilename)
    photo.caption = "Nissan's logo"
    photo.created = Calendar.getInstance().getTime()
    photo.content = arrayPic
    photoCommandService.addPhoto(photo)

    photo
  }
}
