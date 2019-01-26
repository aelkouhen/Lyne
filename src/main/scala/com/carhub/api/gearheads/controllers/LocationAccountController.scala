package com.carhub.api.gearheads.controllers

import java.lang.Long

import com.carhub.api.gearheads.services.AccountManagementService
import com.carhub.api.gearheads.utils.jsonapi.domain.JsonApiList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation._

import scala.collection.JavaConverters.iterableAsScalaIterableConverter

@Autowired
@RestController
@RequestMapping(Array("/location"))
class LocationAccountController(accountService: AccountManagementService) {

  @ResponseBody
  @GetMapping(Array("/add"))
  def addLocation(@RequestParam street:String, @RequestParam city:String, @RequestParam state:String, @RequestParam(name = "zipcode") zipCode:String, @RequestParam country:String):String = accountService.createLocation(street, city, state, zipCode, country)

  @ResponseBody
  @GetMapping(Array("/update{id}"))
  def updateLocation(@PathVariable(value = "id") id:Long, @RequestParam street:String, @RequestParam city:String, @RequestParam state:String, @RequestParam(name = "zipcode") zipCode:String, @RequestParam country:String):String = accountService.createLocation(street, city, state, zipCode, country)


  @ResponseBody
  @GetMapping(Array("/all"))
  def getLocations():ResponseEntity[_] = {
    val result = accountService.getLocations().asScala.toList
    ResponseEntity.ok(JsonApiList(result))
  }

}