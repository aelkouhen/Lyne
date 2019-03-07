package com.carhub.api.auto.controllers.query

import java.util
import java.util.UUID

import com.carhub.api.auto.domain.{Car, Make, Model, Serie}
import com.carhub.api.auto.services.query._
import com.carhub.api.auto.utils.exception.ElementNotFoundException
import io.swagger.annotations.{Api, ApiOperation, ApiParam}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpHeaders, ResponseEntity}
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._

@Api(value = "Make", tags = Array("Make Queries"), description = "This API queries the Make concept.")
@RestController
@RequestMapping(value = Array("/api/makes"))
class MakeQueryRestController(@Autowired
                              val makeQueryService : MakeQueryService,
                              val serieQueryService : SerieQueryService,
                              val modelQueryService : ModelQueryService,
                              val photoQueryService : PhotoQueryService,
                              val carQueryService : CarQueryService) {

  @ApiOperation(value = "List the Makes : Retrieve the Makes list paged and sorted by field.", notes = "It takes the page number, a size for each page, a sorting order and the field on which the list is sorted.", response = classOf[util.List[Make]], responseContainer = "List")
  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(value = Array("/"))
  @ResponseBody
  def getMakesList(@ApiParam(name = "page", example="0", value = "The page number.", required = true) @RequestParam page : Int,
                   @ApiParam(name = "size", example="10", value = "The size of the page.", required = true) @RequestParam size : Int,
                   @ApiParam(name = "order", example="DESC", value = "The sorting order (DESC or ASC).", required = true) @RequestParam(name = "order") sortDirection : String,
                   @ApiParam(name = "field", example="name", value = "The sort field name.", required = true) @RequestParam(name = "field") sort : String ) : ResponseEntity[_] =
  {
    var result : util.List[Make] = new util.ArrayList[Make]
    sortDirection.toLowerCase match {
      case "asc" => result = makeQueryService.getMakesListAsc(page, size, sort)
      case _ => result = makeQueryService.getMakesListDesc(page, size, sort)
    }
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Make](classOf[Make])
    ResponseEntity.ok(result)
  }

  @ApiOperation(value = "Count the Makes.", response = classOf[Long], responseContainer = "Long")
  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(value = Array("/count"))
  @ResponseBody
  def countAllMakes() : Long = makeQueryService.countAllMakes

  @ApiOperation(value = "Filter Makes by name", response = classOf[util.List[Make]], responseContainer = "List")
  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(value = Array("/find"), params = Array("name"))
  @ResponseBody
  def findMakeByName(@ApiParam(name = "name", value = "The filtering expression.", required = true) @RequestParam name : String) : ResponseEntity[_]  = {
    val result = makeQueryService.findMakesByName(name)
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Make](classOf[Make])
    ResponseEntity.ok(result)
  }

  @ApiOperation(value = "Filter Makes by ID", response = classOf[Car])
  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(value = Array("/find"), params = Array("id"))
  @ResponseBody
  def findMakeById(@ApiParam(name = "id", value = "The Make ID.", required = true) @RequestParam(name = "id") makeId : String) : ResponseEntity[_]  = {
    val result = makeQueryService.findMakeById(UUID.fromString(makeId))
    if (result == null) throw new ElementNotFoundException[Make](classOf[Make])
    ResponseEntity.ok(result)
  }

  @ApiOperation(value = "List the Models of the Make : Retrieve all models manufactured by a Make: A paged and sorted list by field.", notes = "It takes the page number, a size for each page, a sorting order and the field on which the list is sorted.", response = classOf[util.List[Model]], responseContainer = "List")
  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(value = Array("/{id}/models"))
  @ResponseBody
  def getMakeModels(@ApiParam(name = "id", value = "The Make ID.", required = true) @PathVariable(value = "id") makeId : String) : ResponseEntity[_] = {
    val result = modelQueryService.getMakeModels(UUID.fromString(makeId))
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Make](classOf[Make])
    ResponseEntity.ok(result)
  }

  @ApiOperation(value = "List the Series of the Make : Retrieve all Series issued by a Make: A paged and sorted list by field.", notes = "It takes the page number, a size for each page, a sorting order and the field on which the list is sorted.", response = classOf[util.List[Serie]], responseContainer = "List")
  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(value = Array("/{id}/series"))
  @ResponseBody
  def getMakeSeries(@ApiParam(name = "id", value = "The Make ID.", required = true) @PathVariable(value = "id") makeId : String) : ResponseEntity[_] = {
    val result = serieQueryService.getMakeSeries(UUID.fromString(makeId))
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Make](classOf[Make])
    ResponseEntity.ok(result)
  }

  @ApiOperation(value = "List the Cars of the Make : Retrieve all Cars manufactured by a Make: A paged and sorted list by field.", notes = "It takes the page number, a size for each page, a sorting order and the field on which the list is sorted.", response = classOf[util.List[Car]], responseContainer = "List")
  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(value = Array("/{id}/cars"))
  @ResponseBody
  def getMakeCars(@ApiParam(name = "id", value = "The Make ID.", required = true) @PathVariable(value = "id") makeId : String) : ResponseEntity[_] = {
    val result = carQueryService.getMakeCars(UUID.fromString(makeId))
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Make](classOf[Make])
    ResponseEntity.ok(result)
  }

  @ApiOperation(value = "Returns the icon of the Make.", response = classOf[Array[Byte]], responseContainer = "Array")
  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(value = Array("/{id}/icon"))
  @ResponseBody
  def getMakeIcon(@ApiParam(name = "id", value = "The Make ID.", required = true) @PathVariable(value = "id") makeId : String) : ResponseEntity[_] = {
    val result = photoQueryService.getMakeIcon(UUID.fromString(makeId))
    if (result == null || result.content.isEmpty || result.content == null) throw new ElementNotFoundException[Make](classOf[Make])
    ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
      "attachment; filename=\"" + result.name + "." + result.format + "\"").body(result.content)
  }
}
