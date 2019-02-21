package com.carhub.api.auto.controllers.query

import java.util

import com.carhub.api.auto.domain.{Car, Model}
import com.carhub.api.auto.services.query.{CarQueryService, ModelQueryService, SerieQueryService}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/api/models"))
class ModelQueryRestController(@Autowired
                               val serieQueryService : SerieQueryService,
                               val modelQueryService : ModelQueryService,
                               val carQueryService : CarQueryService) {

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array(""))
  @ResponseBody
  def getModelsList(@RequestParam page : Int,
                   @RequestParam size : Int,
                   @RequestParam(name = "order") sortDirection : String,
                   @RequestParam(name = "field") sort : String ) : ResponseEntity[_] =
  {
    var result : util.List[Model] = new util.ArrayList[Model]
      sortDirection.toLowerCase match {
        case "asc" =>  result = modelQueryService.getModelsListAsc(page, size, sort)
        case _ =>  result = modelQueryService.getModelsListDesc(page, size, sort)
      }
    ResponseEntity.ok(result)
  }

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/count"))
  @ResponseBody
  def countAllModels() : Long = modelQueryService.countAllModels

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/name={name}"))
  @ResponseBody
  def findModelByName(@PathVariable(value = "name") name : String) : ResponseEntity[_]  = {
      val result = modelQueryService.findModelsByName(name)
      ResponseEntity.ok(result)
  }

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/{id}/series"))
  @ResponseBody
  def getModelsSeries(@PathVariable(value = "id") modelId : Long) : ResponseEntity[_] = {
    val result = serieQueryService.getModelSeries(modelId)
    ResponseEntity.ok(result)
  }

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/{id}/cars"))
  @ResponseBody
  def getModelsCars(@PathVariable(value = "id") modelId : Long) : ResponseEntity[_] = {
    val result : util.List[Car] = carQueryService.getModelCars(modelId)
    ResponseEntity.ok(result)
  }
}
