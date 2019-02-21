package com.carhub.api.auto.controllers.query

import java.util

import com.carhub.api.auto.domain.Make
import com.carhub.api.auto.services.query._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/api/makes"))
class MakeQueryRestController(@Autowired
                              val makeQueryService : MakeQueryService,
                              val serieQueryService : SerieQueryService,
                              val modelQueryService : ModelQueryService,
                              val photoQueryService : PhotoQueryService,
                              val carQueryService : CarQueryService) {

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
  @GetMapping(Array("/name={name}"))
  @ResponseBody
  def findMakeByName(@PathVariable(value = "name") name : String) : ResponseEntity[_]  = {
    val result = makeQueryService.findMakesByName(name)
    ResponseEntity.ok(result)
  }

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/{id}/models"))
  @ResponseBody
  def getMakeModels(@PathVariable(value = "id") makeId : Long) : ResponseEntity[_] = {
    val result = modelQueryService.getMakeModels(makeId)
    ResponseEntity.ok(result)
  }

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/{id}/series"))
  @ResponseBody
  def getMakeSeries(@PathVariable(value = "id") makeId : Long) : ResponseEntity[_] = {
    val result = serieQueryService.getMakeSeries(makeId)
    ResponseEntity.ok(result)
  }

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/{id}/cars"))
  @ResponseBody
  def getMakeCars(@PathVariable(value = "id") makeId : Long) : ResponseEntity[_] = {
    val result = carQueryService.getMakeCars(makeId)
    ResponseEntity.ok(result)
  }

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/{id}/icon"))
  @ResponseBody
  def getMakeIcon(@PathVariable(value = "id") makeId : Long) : ResponseEntity[_] = {
    val result = photoQueryService.getMakeIcon(makeId)
    ResponseEntity.ok(result)
  }
}
