package com.carhub.api.gearheads.services

import java.lang

import com.carhub.api.gearheads.entities.{Gearhead, GearheadRepository}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Autowired
@Service
class AccountManagementService (@Autowired private val gearheadRepository: GearheadRepository){


  def addAccount(name:String, email:String):String = {
    val g = new Gearhead
    g.name = name
    g.email = email
    gearheadRepository.save(g)

    "Saved"
  }

  def getAccounts():lang.Iterable[Gearhead] = {
    gearheadRepository.findAll()
  }

}