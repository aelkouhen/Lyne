package com.carhub.api.auto.controllers.command

import java.text.SimpleDateFormat
import java.util.{Locale, UUID}

import com.carhub.api.auto.domain.{Make, Model}
import com.carhub.api.auto.services.command.MakeCommandService
import com.carhub.api.auto.utils.exception.{ElementNotCreatedException, ElementNotUpdatedException}
import io.swagger.annotations.{Api, ApiOperation, ApiParam}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._
import org.springframework.web.multipart.MultipartFile

@Api(value = "Make", tags = Array("Make Commands"), description = "This API commands the Make concept.")
@RestController
@RequestMapping(value = Array("/api/makes"))
class MakeCommandRestController(@Autowired val makeCommandService: MakeCommandService) {

  @ApiOperation(value = "Create a make.", response = classOf[Make])
  @PreAuthorize("#oauth2.hasScope('CREATE_PRIVILEGE')")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @PostMapping(value = Array("/"))
  def createMake(@ApiParam(name = "make", value = "A Make object.", required = true) @RequestBody make: Make): ResponseEntity[_] = {
    val created = makeCommandService.addMake(make)
    if(created == null) throw new ElementNotCreatedException[Make](classOf[Make])
    ResponseEntity.status(HttpStatus.CREATED).body(created)
  }

  @ApiOperation(value = "Update a make.", response = classOf[Make])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PutMapping(value = Array("/{id}"))
  def updateMake(@ApiParam(name = "id", value = "The Make's ID.", required = true) @PathVariable(value = "id") id : String, @RequestBody make: Make) {
    val updated = makeCommandService.updateMake(UUID.fromString(id), make)
    if(updated == null) throw new ElementNotUpdatedException[Make](classOf[Make])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Make's description text.", response = classOf[Make])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("description"))
  def updateMakeDescription(@ApiParam(name = "id", value = "The Make's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "description", value = "A plain text about the make.", required = true) @RequestParam(name = "description") description : String) = {
    val updated = makeCommandService.updateMakeDescription(UUID.fromString(id), description)
    if(updated == null) throw new ElementNotUpdatedException[Make](classOf[Make])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Make's foundation date.", response = classOf[Make])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("foundation"))
  def updateMakeFoundationDate(@ApiParam(name = "id", value = "The Make's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "foundation", value = "The foundation date.", required = true) @RequestParam(name = "foundation") foundationDate : String) = {
    val updated = makeCommandService.updateMakeFoundationDate(UUID.fromString(id), new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).parse(foundationDate))
    if(updated == null) throw new ElementNotUpdatedException[Make](classOf[Make])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Make's founder.", response = classOf[Make])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("founder"))
  def updateMakeFounder(@ApiParam(name = "id", value = "The Make's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "founder", value = "The founder name.", required = true) @RequestParam(name = "founder") founder : String) = {
    val updated = makeCommandService.updateMakeFounder(UUID.fromString(id), founder)
    if(updated == null) throw new ElementNotUpdatedException[Make](classOf[Make])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Make's headquarter location.", response = classOf[Make])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("headquarter"))
  def updateMakeHQLocation(@ApiParam(name = "id", value = "The Make's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "headquarter", value = "The headquarter location.", required = true) @RequestParam(name = "headquarter") headquarter : String) = {
    val updated = makeCommandService.updateMakeHQLocation(UUID.fromString(id), headquarter)
    if(updated == null) throw new ElementNotUpdatedException[Make](classOf[Make])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Make's IsClosed value.", response = classOf[Make])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("closed"))
  def updateMakeIsClosed(@ApiParam(name = "id", value = "The Make's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "closed", value = "Is closed.", required = true) @RequestParam(name = "closed") closed : Boolean) = {
    val updated = makeCommandService.updateMakeClosed(UUID.fromString(id), closed)
    if(updated == null) throw new ElementNotUpdatedException[Make](classOf[Make])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Upload the Make's logo.", response = classOf[Make])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}/logo"))
  def uploadMakeLogo(@ApiParam(name = "id", value = "The Make's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "logo", value = "The make's logo.", required = true) @RequestParam(name = "logo") logo : MultipartFile) = {
    val updated = makeCommandService.updateMakeLogo(UUID.fromString(id), logo)
    if(updated == null) throw new ElementNotUpdatedException[Make](classOf[Make])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Make's name.", response = classOf[Make])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("name"))
  def updateMakeName(@ApiParam(name = "id", value = "The Make's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "name", value = "The Make's name.", required = true) @RequestParam(name = "name") name : String) = {
    val updated = makeCommandService.updateMakeName(UUID.fromString(id), name)
    if(updated == null) throw new ElementNotUpdatedException[Make](classOf[Make])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Make's old name.", response = classOf[Make])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("oldName"))
  def updateMakeOldName(@ApiParam(name = "id", value = "The Make's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "oldName", value = "The Make's former name.", required = true) @RequestParam(name = "oldName") oldName : String) = {
    val updated = makeCommandService.updateMakeOldName(UUID.fromString(id), oldName)
    if(updated == null) throw new ElementNotUpdatedException[Make](classOf[Make])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Add a Make model.", response = classOf[Make])
  @PreAuthorize("#oauth2.hasScope('UPDATE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"))
  def updateMakeAddModel(@ApiParam(name = "id", value = "The Make's ID.", required = true) @PathVariable(value = "id") id : String, @ApiParam(name = "model", value = "The model associated to the make.", required = true) @RequestBody model : Model) = {
    val updated = makeCommandService.updateMakeAddModel(UUID.fromString(id), model)
    if(updated == null) throw new ElementNotUpdatedException[Make](classOf[Make])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Delete the Make.", response = classOf[Make])
  @PreAuthorize("#oauth2.hasScope('DELETE_PRIVILEGE')")
  @ResponseBody
  @DeleteMapping(Array("/{id}"))
  def deleteMake(@ApiParam(name = "id", value = "The Make ID.", required = true) @PathVariable(value = "id") id : String) = {
    makeCommandService.deleteMake(UUID.fromString(id))
    ResponseEntity.status(HttpStatus.NO_CONTENT).build()
  }
}
