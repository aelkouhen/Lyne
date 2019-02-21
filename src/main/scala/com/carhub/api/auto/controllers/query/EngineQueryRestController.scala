package com.carhub.api.auto.controllers.query

import java.util

import com.carhub.api.auto.domain.Engine
import com.carhub.api.auto.services.query.EngineQueryService
import com.carhub.api.auto.utils.exception.ElementNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/api/engines"))
class EngineQueryRestController(@Autowired val engineQueryService : EngineQueryService) {

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array(""))
  @ResponseBody
  def getEnginesList(@RequestParam page : Int,
                 @RequestParam size : Int,
                 @RequestParam(name = "order") sortDirection : String,
                 @RequestParam(name = "field") sort : String ) : ResponseEntity[_] =
  {
    var result : util.List[Engine] = new util.ArrayList[Engine]
    sortDirection.toLowerCase match {
      case "asc" => result = engineQueryService.getEnginesListAsc(page, size, sort)
      case _ => result = engineQueryService.getEnginesListDesc(page, size, sort)
    }
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Engine]()
    ResponseEntity.ok(result)
  }

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/count"))
  @ResponseBody
  def countAllEngines() : Long = engineQueryService.countAllEngines

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/name={name}"))
  @ResponseBody
  def findEngineByName(@PathVariable(value = "name") name : String) : ResponseEntity[_]  = {
    val result = engineQueryService.findEnginesByName(name)
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Engine]()
    ResponseEntity.ok(result)
  }

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/fuel={fuel}"))
  @ResponseBody
  def findEngineByFuelType(@PathVariable(value = "fuel") fuel : String) : ResponseEntity[_]  = {
    val result = engineQueryService.findEnginesByFuelType(fuel)
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Engine]()
    ResponseEntity.ok(result)
  }
}
