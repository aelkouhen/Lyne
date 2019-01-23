package com.carhub.api.gearheads.controllers

import java.lang.Long

import com.carhub.api.gearheads.services.AccountManagementService
import com.carhub.api.utils.jsonapi.domain.JsonApiList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation._
import java.text.SimpleDateFormat
import java.util.Locale

import com.carhub.api.gearheads.entities.Gender

import scala.collection.JavaConverters.iterableAsScalaIterableConverter

@Autowired
@RestController
@RequestMapping(Array("/account"))
class GearheadAccountController(accountService: AccountManagementService) {

  @ResponseBody
  @GetMapping(Array("/add"))
  def addGearhead(@RequestParam(name = "username") userName:String, @RequestParam email:String, @RequestParam password:String):String = accountService.createAccount(userName, email, password)

  @ResponseBody
  @GetMapping(Array("/update{id}"))
  def updateGearhead(@PathVariable(value = "id") id:Long, @RequestParam(name = "firstname") firstName:String, @RequestParam(name = "lastname") lastName:String, @RequestParam birthday:String, @RequestParam gender:String):String = {
    val format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)
    val date = format.parse(birthday)
    accountService.updateAccount(id, firstName, lastName, date, Gender.valueOf(gender))
  }

  @ResponseBody
  @GetMapping(Array("/update{id}/about"))
  def updateGearhead(@PathVariable(value = "id") id:Long, @RequestParam text:String):String = accountService.updateAccount(id, text)

  @ResponseBody
  @GetMapping(Array("/update{id}/location"))
  def updateGearheadLocation(@PathVariable(value = "id") id:Long, @RequestParam locationID:Long):String = accountService.updateAccountLocation(id, locationID)

  @ResponseBody
  @GetMapping(Array("/update{id}/workplace"))
  def updateGearheadWorkplace(@PathVariable(value = "id") id:Long, @RequestParam workplaceID:Long):String = accountService.updateAccountWorkplace(id, workplaceID)


  @ResponseBody
  @GetMapping(Array("/all"))
  def getGearheads():ResponseEntity[_] = {
    val result = accountService.getAccounts().asScala.toList
    ResponseEntity.ok(JsonApiList(result))
  }

}