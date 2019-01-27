package com.carhub.api

import java.text.SimpleDateFormat
import java.util.{Calendar, Locale}

import com.carhub.api.gearheads.model.{Gearhead, Gender}
import com.carhub.api.gearheads.model.files.Photo
import com.carhub.api.gearheads.model.locations.Location
import com.carhub.api.gearheads.repositories.{GearheadRepository, LocationRepository, PhotoRepository, WorkplaceRepository}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.{ApplicationArguments, ApplicationRunner}
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component


@Component
class InitialDataLoader(@Autowired
                        val gearheadRepository: GearheadRepository,
                        val photoRepository: PhotoRepository,
                        val locationRepository: LocationRepository)
                        extends ApplicationRunner {

  def run(args: ApplicationArguments): Unit = {

    val gearhead = new Gearhead
    gearhead.aboutMe = "lorem ipsum"
    val format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)
    val date = format.parse("15/11/1986")
    gearhead.birthDay = date

    gearheadRepository.save(gearhead)

    val photo = new Photo
    val picture = new ClassPathResource("image/myPic.jpg")
    val inputStream = picture.getInputStream
    val arrayPic = Stream.continually(inputStream.read).takeWhile(-1 !=).map(_.toByte).toArray
    photo.caption = "Amine's Pic"
    photo.created = Calendar.getInstance().getTime()
    photo.owner = gearhead
    photo.fullContent = arrayPic

    photoRepository.saveAndFlush(photo)
    inputStream.close()

    gearhead.profilePhoto = photo
    gearhead.coverPhoto = photo
    gearhead.email = "amine.elkouhen@gmail.com"
    gearhead.firstName = "Amine"
    gearhead.firstName = "El Kouhen"
    gearhead.enabled = true
    gearhead.gender = Gender.MALE
    gearhead.username = "aelkouhen"

    val currentLocation = new Location
    currentLocation.street = "36 Rue Saint Henri"
    currentLocation.city = "La Madeleine"
    currentLocation.state = "Nord"
    currentLocation.zipCode = "59110"
    currentLocation.country = "France"

    locationRepository.save(currentLocation)

    gearhead.hometownLocation = currentLocation
    gearhead.currentLocation = currentLocation
    gearhead.creationTime = Calendar.getInstance().getTime()
    gearhead.updateTime = Calendar.getInstance().getTime()

    gearheadRepository.save(gearhead)

  }

}
