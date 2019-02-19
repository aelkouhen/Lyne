package com.carhub.api.auto.controllers.query

import java.util

import com.carhub.api.auto.domain.Car
import com.carhub.api.auto.services.query.CarQueryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/api/cars"))
class CarQueryRestController(@Autowired val carQueryService : CarQueryService) {

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array(""))
  @ResponseBody
  def getCarsList(@RequestParam page : Int,
                 @RequestParam size : Int,
                 @RequestParam(name = "order") sortDirection : String,
                 @RequestParam(name = "field") sort : String ) : ResponseEntity[_] =
  {
    var result : util.List[Car] = new util.ArrayList[Car]
    sortDirection.toLowerCase match {
        case "asc" => result = carQueryService.getCarsListAsc(page, size, sort)
        case _ => result = carQueryService.getCarsListDesc(page, size, sort)
    }

    ResponseEntity.ok(result)
  }

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/count"))
  @ResponseBody
  def countAllCars() : Long = carQueryService.countAllCars

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/name={name}"))
  @ResponseBody
  def findCarByName(@PathVariable(value = "name") name : String) : ResponseEntity[_]  = {
    val result = carQueryService.findCarsByName(name)
    ResponseEntity.ok(result)
  }
}
