package com.carhub.api.auto.controllers.query

import java.util

import com.carhub.api.auto.domain.{Car, Serie}
import com.carhub.api.auto.services.query.{CarQueryService, SerieQueryService}
import com.carhub.api.auto.utils.exception.ElementNotFoundException
import io.swagger.annotations.{Api, ApiOperation, ApiParam}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._

@Api(value = "Serie", tags = Array("Serie"), description = "This API queries the Serie concept.")
@RestController
@RequestMapping(Array("/api/series"))
class SerieQueryRestController(@Autowired
                               val serieQueryService : SerieQueryService,
                               val carQueryService : CarQueryService) {

  @ApiOperation(value = "List the Series : Retrieve the Series list paged and sorted by field.", notes = "It takes the page number, a size for each page, a sorting order and the field on which the list is sorted.", response = classOf[util.List[Serie]], responseContainer = "List")
  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping()
  @ResponseBody
  def getSeriesList(@ApiParam(name = "page", example="0", value = "The page number.", required = true) @RequestParam page : Int,
                    @ApiParam(name = "size", example="10", value = "The size of the page.", required = true) @RequestParam size : Int,
                    @ApiParam(name = "order", example="DESC", value = "The sorting order (DESC or ASC).", required = true) @RequestParam(name = "order") sortDirection : String,
                    @ApiParam(name = "field", example="name", value = "The sort field name.", required = true) @RequestParam(name = "field") sort : String ) : ResponseEntity[_] =
  {
    var result : util.List[Serie] = new util.ArrayList[Serie]
    sortDirection.toLowerCase match {
      case "asc" => result = serieQueryService.getSeriesListAsc(page, size, sort)
      case _ => result = serieQueryService.getSeriesListDesc(page, size, sort)
    }
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Serie](classOf[Serie])
    ResponseEntity.ok(result)
  }

  @ApiOperation(value = "Count the Series.", response = classOf[Long], responseContainer = "Long")
  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(value = Array("/count"))
  @ResponseBody
  def countAllSeries() : Long = serieQueryService.countAllSeries

  @ApiOperation(value = "Filter Series by name", response = classOf[util.List[Serie]], responseContainer = "List")
  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(value = Array("/find"), params = Array("name"))
  @ResponseBody
  def findSeriesByName(@ApiParam(name = "name", value = "The filtering expression.", required = true) @RequestParam name : String) : ResponseEntity[_]  = {
    val result = serieQueryService.findSeriesByName(name)
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Serie](classOf[Serie])
    ResponseEntity.ok(result)
  }

  @ApiOperation(value = "List the Cars of the Serie : Retrieve all Cars manufactured within a Serie: A paged and sorted list by field.", notes = "It takes the page number, a size for each page, a sorting order and the field on which the list is sorted.", response = classOf[util.List[Car]], responseContainer = "List")
  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/{id}/cars"))
  @ResponseBody
  def getSerieCars(@ApiParam(name = "id", example = "1", value = "The Model ID.", required = true) @PathVariable(value = "id") serieId : Long) : ResponseEntity[_] = {
    val result  = carQueryService.getSerieCars(serieId)
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Serie](classOf[Serie])
    ResponseEntity.ok(result)
  }
}
