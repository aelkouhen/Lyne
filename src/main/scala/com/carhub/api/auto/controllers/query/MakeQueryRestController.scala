package com.carhub.api.auto.controllers.query

import java.util

import com.carhub.api.auto.domain.Make
import com.carhub.api.auto.services.query.MakeQueryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/api/makes"))
class MakeQueryRestController(@Autowired val makeQueryService : MakeQueryService) {

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array(""))
  @ResponseBody
  def getMakesList(@RequestParam page : Int,
                  @RequestParam size : Int,
                  @RequestParam(name = "order") sortDirection : String,
                  @RequestParam(name = "field") sort : String ) : ResponseEntity[_] =
  {
    var result : util.List[Make] = new util.ArrayList[Make]
    sortDirection.toLowerCase match {
      case "asc" => result = makeQueryService.getMakesListAsc(page, size, sort)
      case _ => result = makeQueryService.getMakesListDesc(page, size, sort)
    }

    ResponseEntity.ok(result)
  }

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/count"))
  @ResponseBody
  def countAllMakes() : Long = makeQueryService.countAllMakes

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/name/{name}"))
  @ResponseBody
  def findMakeByName(@PathVariable(value = "name") name : String) : ResponseEntity[_]  = {
    val result = makeQueryService.findMakesByName(name)
    ResponseEntity.ok(result)
  }

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/models"))
  @ResponseBody
  def getMakesModels(make : Make) : ResponseEntity[_] = {
    println("test")
    val result = makeQueryService.getMakesModels(make)
    println(result.size())
    ResponseEntity.ok(result)
  }

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/series"))
  @ResponseBody
  def getMakesSeries(make : Make) : ResponseEntity[_] = {
    val result = makeQueryService.getMakesSeries(make)
    ResponseEntity.ok(result)
  }

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/cars"))
  @ResponseBody
  def getMakesCars(make : Make) : ResponseEntity[_] = {
    val result = makeQueryService.getMakesCars(make)
    ResponseEntity.ok(result)
  }
}
