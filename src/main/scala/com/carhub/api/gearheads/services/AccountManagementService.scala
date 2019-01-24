package com.carhub.api.gearheads.services

import java.lang.Long
import java.text.SimpleDateFormat
import java.util.{Calendar, Date, Locale}

import com.carhub.api.gearheads.model.locations.{Location, Workplace}
import com.carhub.api.gearheads.model.{Gearhead, Gender}
import com.carhub.api.gearheads.repositories.{GearheadRepository, LocationRepository, WorkplaceRepository}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Autowired
@Service
class AccountManagementService (@Autowired
                                val gearheadRepository: GearheadRepository,
                                val locationRepository: LocationRepository,
                                val workplaceRepository: WorkplaceRepository,
                                val passwordEncoder: PasswordEncoder){


  def createAccount(userName:String, email:String, password:String):String = {
    val gearhead = new Gearhead
    gearhead.username = userName
    gearhead.password = passwordEncoder.encode(password)
    gearhead.email = email
    Calendar.getInstance().getTime()
    val now = Calendar.getInstance().getTime()
    gearhead.creationTime = now
    gearhead.updateTime = now
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

  def createWorkplace(name:String, position:String, startDate:String):String = {
    val workplace = new Workplace
    workplace.companyName = name
    workplace.position = position
    val format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)
    val date = format.parse(startDate)
    workplace.startDate = date

    workplaceRepository.save(workplace)

    s"Workplace ${workplace.id} Created"
  }

  def updateWorkplace(id:Long, name:String, position:String, startDate:String, description:String):String = {
    val workplace = workplaceRepository.findOne(id)
    if(workplace == null)
      "Workplace not found"

    workplace.companyName = name
    workplace.position = position
    val format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)
    val date = format.parse(startDate)
    workplace.startDate = date
    workplace.description = description

    workplaceRepository.save(workplace)

    s"Workplace ${workplace.id} Updated"
  }

  def updateWorkplaceLocation(workplaceID:Long, locationID:Long):String = {
    val workplace = workplaceRepository.findOne(workplaceID)
    if (workplace == null )
      "Workplace not found"

    val location = locationRepository.findOne(locationID)
    if (location==null)
      "Location not found"

    updateWorkplaceLocation(workplace,location)

  }

  private def updateWorkplaceLocation(workplace:Workplace, location: Location):String = {
    workplace.location = location
    workplaceRepository.save(workplace)

    s"Workplace ${workplace.id} Updated"
  }

  def updateAccount(id:Long, firstName:String, lastName:String, birthDay:Date, gender: Gender.Value):String = {
    val gearhead = gearheadRepository.findOne(id)
    if (gearhead == null )
      "Account not found"

    gearhead.firstName = firstName
    gearhead.lastName = lastName
    gearhead.birthDay = birthDay
    gearhead.gender = gender

    updateConnexionTime(gearhead)
    gearheadRepository.save(gearhead)

    s"Account $id Updated"
  }

  def updateAccount(id:Long, about:String):String = {
    val gearhead = gearheadRepository.findOne(id)
    if (gearhead == null )
      "Account not found"

    gearhead.aboutMe = about

    updateConnexionTime(gearhead)
    gearheadRepository.save(gearhead)

    s"Account $id Updated"
  }

  def updateAccountLocation(gearheadID:Long, locationID:Long):String = {
    val gearhead = gearheadRepository.findOne(gearheadID)
    if (gearhead == null )
      "Account not found"

    val location = locationRepository.findOne(locationID)
    if (location == null )
      "Location not found"

    updateAccountLocation(gearhead, location)
  }

  private def updateAccountLocation(gearhead: Gearhead, location:Location):String = {
    gearhead.currentLocation = location
    updateConnexionTime(gearhead)
    gearheadRepository.save(gearhead)

    s"Account ${gearhead.id} Updated"
  }


  def updateAccountWorkplace(gearheadID:Long, workplaceID:Long):String = {
    val gearhead = gearheadRepository.findOne(gearheadID)
    if (gearhead == null )
      "Account not found"

    val workplace = workplaceRepository.findOne(workplaceID)
    if (workplace == null )
      "Workplace not found"

    updateAccountWorkplace(gearhead, workplace)
  }

  private def updateAccountWorkplace(gearhead: Gearhead, workplace:Workplace):String = {
    gearhead.workplace = workplace
    updateConnexionTime(gearhead)
    gearheadRepository.save(gearhead)

    s"Account ${gearhead.id} Updated"
  }

  private def updateConnexionTime(gearhead:Gearhead):String = {
    val now = Calendar.getInstance().getTime()
    gearhead.updateTime = now
    gearheadRepository.save(gearhead)

    s"Account ${gearhead.id} Updated"
  }

  def updateLocation(id:Long, street:String, city:String, state:String, zipCode:String, country:String):String = {
    val location = locationRepository.findOne(id)
    if (location == null )
      "Location not found"

    location.street = street
    location.city = city
    location.state = state
    location.zipCode = zipCode
    location.country = country

    locationRepository.save(location)

    s"Location ${location.id} Updated"
  }


  def getAccounts():java.lang.Iterable[Gearhead] =  gearheadRepository.findAll()
  def getLocations():java.lang.Iterable[Location] = locationRepository.findAll()
  def getWorkplaces():java.lang.Iterable[Workplace] = workplaceRepository.findAll()

}