package com.carhub.api.gearheads.controllers

import com.carhub.api.gearheads.services.AccountManagementService
import com.carhub.api.utils.jsonapi.domain.JsonApiList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation._

import scala.collection.JavaConverters.iterableAsScalaIterableConverter

@Autowired
@RestController
@RequestMapping(Array("/account"))
class GearheadAccountController(accountService: AccountManagementService) {

  @ResponseBody
  @GetMapping(Array("/add"))
  def addGearhead(@RequestParam name:String, @RequestParam email:String):String = accountService.addAccount(name, email)


  @ResponseBody
  @GetMapping(Array("/all"))
  def getGearheads():ResponseEntity[_] = {
    val result = accountService.getAccounts().asScala.toList
    ResponseEntity.ok(JsonApiList(result))
  }

}