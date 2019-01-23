package com.carhub.api.gearheads.controllers

import java.lang.Long

import com.carhub.api.gearheads.services.AccountManagementService
import com.carhub.api.utils.jsonapi.domain.JsonApiList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation._

import scala.collection.JavaConverters.iterableAsScalaIterableConverter

@Autowired
@RestController
@RequestMapping(Array("/workplace"))
class WorkplaceAccountController(accountService: AccountManagementService) {

  @ResponseBody
  @GetMapping(Array("/add"))
  def createWorkplace(@RequestParam name:String, @RequestParam position:String, @RequestParam(value = "start") startDate:String):String = accountService.createWorkplace(name, position, startDate)

  @ResponseBody
  @GetMapping(Array("/update{id}"))
  def updateWorkplace(@PathVariable(value = "id") id:Long, @RequestParam name:String, @RequestParam position:String, @RequestParam(value = "start") startDate:String, @RequestParam(value = "about") description:String):String = accountService.updateWorkplace(id, name, position, startDate, description)

  @ResponseBody
  @GetMapping(Array("/update{id}/location"))
  def updateWorkplaceLocation(@PathVariable(value = "id") id:Long, @RequestParam locationID:Long):String = accountService.updateWorkplaceLocation(id, locationID)

  @ResponseBody
  @GetMapping(Array("/all"))
  def getWorkplaces():ResponseEntity[_] = {
    val result = accountService.getWorkplaces().asScala.toList
    ResponseEntity.ok(JsonApiList(result))
  }


}