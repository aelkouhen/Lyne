package com.carhub.api.gearheads.services

import java.lang.Long
import java.util.{Calendar, Date}

import com.carhub.api.gearheads.entities.{Gearhead, Gender, Location}
import com.carhub.api.gearheads.repositories.{GearheadRepository, LocationRepository}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Autowired
@Service
class AccountManagementService (@Autowired
                                val gearheadRepository: GearheadRepository,
                                val locationRepository: LocationRepository,
                                val passwordEncoder: PasswordEncoder){


  def createAccount(userName:String, email:String, password:String):String = {
    val gearhead = new Gearhead
    gearhead.username = userName
    gearhead.password = passwordEncoder.encode(password)
    gearhead.email = email
    Calendar.getInstance().getTime()
    val now = Calendar.getInstance().getTime()
    gearhead.creationDate = now
    gearhead.lastConnexion = now
    gearhead.gender = Gender.UNSPECIFIED
    gearhead.enabled = true
    gearheadRepository.save(gearhead)

    s"Account ${gearhead.id} Created"
  }

  def createLocation(street:String, city:String, state:String, zipCode:String, country:String):String = {
    val location = new Location
    location.street = street
    location.city = city
    location.state = state
    location.zipCode = zipCode
    location.country = country

    locationRepository.save(location)

    s"Location ${location.id} Created"
  }

  def updateAccount(id:Long, firstName:String, lastName:String, birthDay:Date):String = {
    val gearhead = gearheadRepository.findOne(id)
    gearhead.firstName = firstName
    gearhead.lastName = lastName
    gearhead.birthDay = birthDay

    updateConnexionTime(gearhead)
    gearhead.gender = Gender.UNSPECIFIED
    gearheadRepository.save(gearhead)

    s"Account $id Updated"
  }

  def updateAccountLocation(gearheadID:Long, locationID:Long):String = {
    val gearhead = gearheadRepository.findOne(gearheadID)
    val location = locationRepository.findOne(locationID)
    updateAccountLocation(gearhead, location)
  }

  private def updateAccountLocation(gearhead: Gearhead, location:Location):String = {
    gearhead.location = location
    updateConnexionTime(gearhead)
    gearheadRepository.save(gearhead)

    s"Account ${gearhead.id} Updated"
  }

  private def updateConnexionTime(gearhead:Gearhead):String = {
    val now = Calendar.getInstance().getTime()
    gearhead.lastConnexion = now
    gearheadRepository.save(gearhead)

    s"Account ${gearhead.id} Updated"
  }

  def updateLocation(id:Long, street:String, city:String, state:String, zipCode:String, country:String):String = {
    val location = locationRepository.findOne(id)
    location.street = street
    location.city = city
    location.state = state
    location.zipCode = zipCode
    location.country = country

    locationRepository.save(location)

    s"Location ${location.id} Updated"
  }


  def getAccounts():java.lang.Iterable[Gearhead] = {
    gearheadRepository.findAll()
  }

  def getLocations():java.lang.Iterable[Location] = {
    locationRepository.findAll()
  }
}