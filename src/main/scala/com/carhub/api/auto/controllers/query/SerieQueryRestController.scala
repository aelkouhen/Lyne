package com.carhub.api.auto.controllers.query

import java.util

import com.carhub.api.auto.domain.Serie
import com.carhub.api.auto.services.query.{CarQueryService, SerieQueryService}
import com.carhub.api.auto.utils.exception.ElementNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/api/series"))
class SerieQueryRestController(@Autowired
                               val serieQueryService : SerieQueryService,
                               val carQueryService : CarQueryService) {

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array(""))
  @ResponseBody
  def getSeriesList(@RequestParam page : Int,
                    @RequestParam size : Int,
                    @RequestParam(name = "order") sortDirection : String,
                    @RequestParam(name = "field") sort : String ) : ResponseEntity[_] =
  {
    var result : util.List[Serie] = new util.ArrayList[Serie]
    sortDirection.toLowerCase match {
      case "asc" => result = serieQueryService.getSeriesListAsc(page, size, sort)
      case _ => result = serieQueryService.getSeriesListDesc(page, size, sort)
    }
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Serie]()
    ResponseEntity.ok(result)
  }

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/count"))
  @ResponseBody
  def countAllSeries() : Long = serieQueryService.countAllSeries

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/name={name}"))
  @ResponseBody
  def findSeriesByName(@PathVariable(value = "name") name : String) : ResponseEntity[_]  = {
    val result = serieQueryService.findSeriesByName(name)
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Serie]()
    ResponseEntity.ok(result)
  }

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/{id}/cars"))
  @ResponseBody
  def getSerieCars(@PathVariable(value = "id") serieId : Long) : ResponseEntity[_] = {
    val result  = carQueryService.getSerieCars(serieId)
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Serie]()
    ResponseEntity.ok(result)
  }
}
