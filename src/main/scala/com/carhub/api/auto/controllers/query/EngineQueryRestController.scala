package com.carhub.api.auto.controllers.query

import java.util
import java.util.UUID

import com.carhub.api.auto.domain.Engine
import com.carhub.api.auto.services.query.EngineQueryService
import com.carhub.api.auto.utils.exception.ElementNotFoundException
import io.swagger.annotations.{Api, ApiOperation, ApiParam}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation._

@Api(value = "Engine", tags = Array("Engine Queries"), description = "This API queries the Engine concept.")
@RestController
@RequestMapping(value = Array("/v1"))
class EngineQueryRestController(@Autowired val engineQueryService : EngineQueryService) {

  @ApiOperation(value = "List the Engines : Retrieve the Engines list paged and sorted by field.", notes = "It takes the page number, a size for each page, a sorting order and the field on which the list is sorted.", response = classOf[util.List[Engine]], responseContainer = "List")
  @GetMapping(value = Array("/engines"))
  @ResponseBody
  def getEnginesList(@ApiParam(name = "page", example="0", value = "The page number.", required = true) @RequestParam page : Int,
                     @ApiParam(name = "size", example="10", value = "The size of the page.", required = true) @RequestParam size : Int,
                     @ApiParam(name = "order", example="DESC", value = "The sorting order (DESC or ASC).", required = true) @RequestParam(name = "order") sortDirection : String,
                     @ApiParam(name = "field", example="name", value = "The sort field name.", required = true) @RequestParam(name = "field") sort : String ) : ResponseEntity[_] =
  {
    var result : util.List[Engine] = new util.ArrayList[Engine]
    sortDirection.toLowerCase match {
      case "asc" => result = engineQueryService.getEnginesListAsc(page, size, sort)
      case _ => result = engineQueryService.getEnginesListDesc(page, size, sort)
    }
    if (result == null || result.isEmpty) throw new ElementNotFoundException[Engine](classOf[Engine])
    ResponseEntity.ok(result)
  }

  @ApiOperation(value = "Count the Engines.", response = classOf[Long], responseContainer = "Long")
  @GetMapping(value = Array("/engines/count"))
  @ResponseBody
  def countAllEngines() : Long = engineQueryService.countAllEngines

  @ApiOperation(value = "Filter Engines by name", response = classOf[util.List[Engine]], responseContainer = "List")
  @GetMapping(value = Array("/engines/find"), params = Array("name"))
  @ResponseBody
  def findEngineByName(@ApiParam(name = "name", value = "The filtering expression.", required = true) @RequestParam name : String) : ResponseEntity[_]  = {
    val result = engineQueryService.findEnginesByName(name)
    if (result == null || result.isEmpty) throw new ElementNotFoundException[Engine](classOf[Engine])
    ResponseEntity.ok(result)
  }

  @ApiOperation(value = "Filter Engines by fuel type", response = classOf[util.List[Engine]], responseContainer = "List")
  @GetMapping(value = Array("/engines/find"), params = Array("fuel"))
  @ResponseBody
  def findEngineByFuelType(@ApiParam(name = "fuel", value = "The filtering expression.", required = true) @RequestParam fuel : String) : ResponseEntity[_]  = {
    val result = engineQueryService.findEnginesByFuelType(fuel)
    if (result == null || result.isEmpty) throw new ElementNotFoundException[Engine](classOf[Engine])
    ResponseEntity.ok(result)
  }

  @ApiOperation(value = "Filter Engines by ID", response = classOf[Engine])
  @GetMapping(value = Array("/engines/find"), params = Array("id"))
  @ResponseBody
  def findEngineById(@ApiParam(name = "id", value = "The Engine ID.", required = true) @RequestParam(name = "id") engineId : String) : ResponseEntity[_]  = {
    val result = engineQueryService.findEngineById(UUID.fromString(engineId))
    if (result == null) throw new ElementNotFoundException[Engine](classOf[Engine])
    ResponseEntity.ok(result)
  }
}
