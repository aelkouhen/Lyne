package com.carhub.api.auto.controllers.command

import com.carhub.api.auto.domain.{Car, Serie}
import com.carhub.api.auto.services.command.SerieCommandService
import com.carhub.api.auto.utils.exception.{ElementNotCreatedException, ElementNotUpdatedException}
import io.swagger.annotations.{Api, ApiOperation, ApiParam}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._

@Api(value = "Serie", tags = Array("Serie Commands"), description = "This API commands the Serie concept.")
@RestController
@RequestMapping(value = Array("/api/series"))
class SerieCommandRestController(@Autowired val serieCommandService: SerieCommandService) {

  @ApiOperation(value = "Create a serie.", response = classOf[Serie])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @PostMapping(value = Array("/"))
  def createSerie(@ApiParam(name = "serie", value = "A Serie object.", required = true) @RequestBody serie: Serie): ResponseEntity[_] = {
    val created = serieCommandService.addSerie(serie)
    if (created == null) throw new ElementNotCreatedException[Serie](classOf[Serie])
    ResponseEntity.status(HttpStatus.CREATED).body(created)
  }

  @ApiOperation(value = "Update a serie.", response = classOf[Serie])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PutMapping(value = Array("/{id}"))
  def updateSerie(@ApiParam(name = "id", value = "The Serie's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @RequestBody serie  :Serie) {
    val updated = serieCommandService.updateSerie(id, serie)
    if(updated == null) throw new ElementNotUpdatedException[Serie](classOf[Serie])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the serie's model.", response = classOf[Serie])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("model"))
  def updateSerieModel(@ApiParam(name = "id", value = "The Serie's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "model", value = "The model of the serie.", required = true, example = "1") @RequestParam(name = "model") modelId : Long) = {
    val updated = serieCommandService.updateSerieModel(id, modelId)
    if(updated == null) throw new ElementNotUpdatedException[Serie](classOf[Serie])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the serie's name.", response = classOf[Serie])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("name"))
  def updateSerieName(@ApiParam(name = "id", value = "The Serie's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "name", value = "The name of the serie.", required = true) @RequestParam(name = "name") name : String) = {
    val updated = serieCommandService.updateSerieName(id, name)
    if(updated == null) throw new ElementNotUpdatedException[Serie](classOf[Serie])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the serie's production start year.", response = classOf[Serie])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("start"))
  def updateSerieStartYear(@ApiParam(name = "id", value = "The Serie's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "start", value = "The production start year of the serie.", required = true, example = "1999") @RequestParam(name = "start") start : Int) = {
    val updated = serieCommandService.updateSerieStartYear(id, start)
    if(updated == null) throw new ElementNotUpdatedException[Serie](classOf[Serie])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the serie's production end year.", response = classOf[Serie])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("end"))
  def updateSerieEndYear(@ApiParam(name = "id", value = "The Serie's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "end", value = "The production end year of the serie.", required = true, example = "1999") @RequestParam(name = "end") end : Int) = {
    val updated = serieCommandService.updateSerieEndYear(id, end)
    if(updated == null) throw new ElementNotUpdatedException[Serie](classOf[Serie])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Add a car to the serie.", response = classOf[Serie])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"))
  def updateSerieAddCar(@ApiParam(name = "id", value = "The Serie's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "car", value = "The car attached to the Serie.", required = true) @RequestBody car : Car) = {
    val updated = serieCommandService.updateSerieAddCar(id, car)
    if(updated == null) throw new ElementNotUpdatedException[Serie](classOf[Serie])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Delete the Serie.", response = classOf[Serie])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @DeleteMapping(Array("/{id}"))
  def deleteSerie(@ApiParam(name = "id", value = "The Serie ID.", required = true, example = "1") @PathVariable(value = "id") id : Long) = {
    serieCommandService.deleteSerie(id)
    ResponseEntity.status(HttpStatus.NO_CONTENT).build()
  }
}
