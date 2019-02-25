package com.carhub.api.auto.controllers.query

import java.util

import com.carhub.api.auto.domain.{Car, Model, Serie}
import com.carhub.api.auto.services.query.{CarQueryService, ModelQueryService, SerieQueryService}
import com.carhub.api.auto.utils.exception.ElementNotFoundException
import io.swagger.annotations.{Api, ApiOperation, ApiParam}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._

@Api(value = "Model", tags = Array("Model"), description = "This API queries the Model concept.")
@RestController
@RequestMapping(value = Array("/api/models"))
class ModelQueryRestController(@Autowired
                               val serieQueryService : SerieQueryService,
                               val modelQueryService : ModelQueryService,
                               val carQueryService : CarQueryService) {

  @ApiOperation(value = "List the Models : Retrieve the Models list paged and sorted by field.", notes = "It takes the page number, a size for each page, a sorting order and the field on which the list is sorted.", response = classOf[util.List[Model]], responseContainer = "List")
  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(value = Array("/"))
  @ResponseBody
  def getModelsList(@ApiParam(name = "page", example="0", value = "The page number.", required = true) @RequestParam page : Int,
                    @ApiParam(name = "size", example="10", value = "The size of the page.", required = true) @RequestParam size : Int,
                    @ApiParam(name = "order", example="DESC", value = "The sorting order (DESC or ASC).", required = true) @RequestParam(name = "order") sortDirection : String,
                    @ApiParam(name = "field", example="name", value = "The sort field name.", required = true) @RequestParam(name = "field") sort : String ) : ResponseEntity[_] =
  {
    var result : util.List[Model] = new util.ArrayList[Model]
      sortDirection.toLowerCase match {
        case "asc" =>  result = modelQueryService.getModelsListAsc(page, size, sort)
        case _ =>  result = modelQueryService.getModelsListDesc(page, size, sort)
      }
    if (result.isEmpty) throw new ElementNotFoundException[Model](classOf[Model])
    ResponseEntity.ok(result)
  }

  @ApiOperation(value = "Count the Models.", response = classOf[Long], responseContainer = "Long")
  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(value = Array("/count"))
  @ResponseBody
  def countAllModels() : Long = modelQueryService.countAllModels

  @ApiOperation(value = "Filter Models by name", response = classOf[util.List[Model]], responseContainer = "List")
  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(value = Array("/find"), params = Array("name"))
  @ResponseBody
  def findModelByName(@ApiParam(name = "name", value = "The filtering expression.", required = true) @RequestParam name : String) : ResponseEntity[_]  = {
    val result = modelQueryService.findModelsByName(name)
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Model](classOf[Model])
    ResponseEntity.ok(result)
  }

  @ApiOperation(value = "List the Series of the Model : Retrieve all Series issued within a Model: A paged and sorted list by field.", notes = "It takes the page number, a size for each page, a sorting order and the field on which the list is sorted.", response = classOf[util.List[Serie]], responseContainer = "List")
  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(value = Array("/{id}/series"))
  @ResponseBody
  def getModelsSeries(@ApiParam(name = "id", example = "1", value = "The Model ID.", required = true) @PathVariable(value = "id") modelId : Long) : ResponseEntity[_] = {
    val result = serieQueryService.getModelSeries(modelId)
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Model](classOf[Model])
    ResponseEntity.ok(result)
  }

  @ApiOperation(value = "List the Cars of the Model : Retrieve all Cars manufactured within a Model: A paged and sorted list by field.", notes = "It takes the page number, a size for each page, a sorting order and the field on which the list is sorted.", response = classOf[util.List[Car]], responseContainer = "List")
  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(value = Array("/{id}/cars"))
  @ResponseBody
  def getModelsCars(@ApiParam(name = "id", example = "1", value = "The Model ID.", required = true) @PathVariable(value = "id") modelId : Long) : ResponseEntity[_] = {
    val result : util.List[Car] = carQueryService.getModelCars(modelId)
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Model](classOf[Model])
    ResponseEntity.ok(result)
  }
}
