package com.carhub.api.gearheads.controllers

import java.lang.Long
import java.util.Date

import com.carhub.api.gearheads.services.AccountManagementService
import com.carhub.api.utils.jsonapi.domain.JsonApiList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation._
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale

import scala.collection.JavaConverters.iterableAsScalaIterableConverter

@Autowired
@RestController
@RequestMapping(Array("/account"))
class GearheadAccountController(accountService: AccountManagementService) {

  @ResponseBody
  @GetMapping(Array("/add"))
  def addGearhead(@RequestParam userName:String, @RequestParam email:String, @RequestParam password:String):String = accountService.createAccount(userName, email, password)

  @ResponseBody
  @GetMapping(Array("/update{id}"))
  def updateGearhead(@RequestParam id:Long, @RequestParam firstName:String, @RequestParam lastName:String, @RequestParam birthday:String):String = {
    val format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH)
    val date = format.parse(birthday)
    accountService.updateAccount(id, firstName, lastName, date)
  }

  @ResponseBody
  @GetMapping(Array("/update{id}"))
  def updateGearhead(@RequestParam id:Long, @RequestParam locationID:Long):String = {
    accountService.updateAccountLocation(id, locationID)
  }

  @ResponseBody
  @GetMapping(Array("/all"))
  def getGearheads():ResponseEntity[_] = {
    val result = accountService.getAccounts().asScala.toList
    ResponseEntity.ok(JsonApiList(result))
  }

}