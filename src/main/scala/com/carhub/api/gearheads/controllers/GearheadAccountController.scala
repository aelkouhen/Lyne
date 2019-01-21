package com.carhub.api.gearheads.controllers

import java.lang

import com.carhub.api.gearheads.entities.Gearhead
import com.carhub.api.gearheads.services.AccountManagementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._

@Autowired
@RestController
@RequestMapping(Array("/account"))
class GearheadAccountController(accountService: AccountManagementService) {

  @ResponseBody
  @GetMapping(Array("/add"))
  def addGearhead(@RequestParam name:String, @RequestParam email:String):String = accountService.addAccount(name, email)


  @ResponseBody
  @GetMapping(Array("/all"))
  def getAllGearheads():lang.Iterable[Gearhead] = accountService.getAccounts()


}
