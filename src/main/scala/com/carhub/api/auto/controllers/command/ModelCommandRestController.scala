package com.carhub.api.auto.controllers.command

import java.text.SimpleDateFormat
import java.util.{Locale, UUID}

import com.carhub.api.auto.domain.{Model, Serie}
import com.carhub.api.auto.services.command.ModelCommandService
import com.carhub.api.auto.utils.exception.{ElementNotCreatedException, ElementNotUpdatedException}
import io.swagger.annotations.{Api, ApiOperation, ApiParam}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._

@Api(value = "Model", tags = Array("Model Commands"), description = "This API commands the Model concept.")
@RestController
@RequestMapping(value = Array("/v1"))
class ModelCommandRestController(@Autowired val modelCommandService: ModelCommandService) {

  @ApiOperation(value = "Create a model.", response = classOf[Model])
  @PreAuthorize("#oauth2.hasScope('CREATE_PRIVILEGE')")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @PostMapping(value = Array("/models"))
  def createModel(@ApiParam(name = "model", value = "A Model object.", required = true) @RequestBody model: Model): ResponseEntity[_] = {
    val created = modelCommandService.addModel(model)
    if (created == null) throw new ElementNotCreatedException[Model](classOf[Model])
    ResponseEntity.status(HttpStatus.CREATED).body(created)
  }

  @ApiOperation(value = "Update a model.", response = classOf[Model])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PutMapping(value = Array("/models/{id}"))
  def updateModel(@ApiParam(name = "id", value = "The Model's ID.", required = true) @PathVariable(value = "id") id : String, @RequestBody model: Model) {
    val updated = modelCommandService.updateModel(UUID.fromString(id), model)
    if(updated == null) throw new ElementNotUpdatedException[Model](classOf[Model])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the model's make.", response = classOf[Model])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/models/{id}"), params = Array("make"))
  def updateModelMake(@ApiParam(name = "id", value = "The Model's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "make", value = "The make of the model.", required = true) @RequestParam(name = "make") makeId : String) = {
    val updated = modelCommandService.updateModelMake(UUID.fromString(id), UUID.fromString(makeId))
    if(updated == null) throw new ElementNotUpdatedException[Model](classOf[Model])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Model's name.", response = classOf[Model])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/models/{id}"), params = Array("name"))
  def updateModelName(@ApiParam(name = "id", value = "The Model's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "name", value = "The Model's name.", required = true) @RequestParam(name = "name") name : String) = {
    val updated = modelCommandService.updateModelName(UUID.fromString(id), name)
    if(updated == null) throw new ElementNotUpdatedException[Model](classOf[Model])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Model's generation.", response = classOf[Model])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/models/{id}"), params = Array("generation"))
  def updateModelGeneration(@ApiParam(name = "id", value = "The Model's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "generation", value = "The Model's generation.", required = true) @RequestParam(name = "generation") generation : String) = {
    val updated = modelCommandService.updateModelGeneration(UUID.fromString(id), generation)
    if(updated == null) throw new ElementNotUpdatedException[Model](classOf[Model])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Model's creation date.", response = classOf[Model])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/models/{id}"), params = Array("creation"))
  def updateModelCreationDate(@ApiParam(name = "id", value = "The Model's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "creation", value = "The creation date.", required = true) @RequestParam(name = "creation") creationDate : String) = {
    val updated = modelCommandService.updateModelCreationDate(UUID.fromString(id), new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).parse(creationDate))
    if(updated == null) throw new ElementNotUpdatedException[Model](classOf[Model])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Add a Model serie.", response = classOf[Model])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/models/{id}"))
  def updateModelAddSerie(@ApiParam(name = "id", value = "The Model's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "serie", value = "The serie associated to the model.", required = true) @RequestBody serie : Serie) = {
    val updated = modelCommandService.updateModelAddSerie(UUID.fromString(id), serie)
    if(updated == null) throw new ElementNotUpdatedException[Model](classOf[Model])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Delete the Model.", response = classOf[Model])
  @PreAuthorize("#oauth2.hasScope('DELETE_PRIVILEGE')")
  @ResponseBody
  @DeleteMapping(Array("/models/{id}"))
  def deleteModel(@ApiParam(name = "id", value = "The Model ID.", required = true) @PathVariable(value = "id") id : String) = {
    modelCommandService.deleteModel(UUID.fromString(id))
    ResponseEntity.status(HttpStatus.NO_CONTENT).build()
  }
}