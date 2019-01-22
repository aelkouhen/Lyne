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

  /*
  @ResponseBody
  @GetMapping(Array("/add"))
  def createWorkplace(@RequestParam userName:String, @RequestParam email:String, @RequestParam password:String):String = accountService.createAccount(userName, email, password)

  @ResponseBody
  @GetMapping(Array("/all"))
  def getWorkplaces():ResponseEntity[_] = {
    val result = accountService.getAccounts().asScala.toList
    ResponseEntity.ok(JsonApiList(result))
  }
*/

}