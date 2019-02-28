package com.carhub.api.auto.controllers.command

import java.text.SimpleDateFormat
import java.util.Locale

import com.carhub.api.auto.domain.Photo
import com.carhub.api.auto.services.command.PhotoCommandService
import com.carhub.api.auto.utils.exception.{ElementNotCreatedException, ElementNotUpdatedException}
import io.swagger.annotations.{Api, ApiOperation, ApiParam}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._
import org.springframework.web.multipart.MultipartFile

@Api(value = "Photo", tags = Array("Photo Commands"), description = "This API commands the Photo concept.")
@RestController
@RequestMapping(value = Array("/api/photos"))
class PhotoCommandRestController(@Autowired val photoCommandService: PhotoCommandService) {

  @ApiOperation(value = "Create a photo.", response = classOf[Photo])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @PostMapping(value = Array("/"))
  def createPhoto(@ApiParam(name = "photo", value = "A Photo object.", required = true) @RequestBody photo: Photo): ResponseEntity[_] = {
    val created = photoCommandService.addPhoto(photo)
    if(created == null) throw new ElementNotCreatedException[Photo](classOf[Photo])
    ResponseEntity.status(HttpStatus.CREATED).body(created)
  }

  @ApiOperation(value = "Upload a photo.", response = classOf[Photo])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @PostMapping(value = Array("/upload"))
  def uploadPhoto(@ApiParam(name = "photo", value = "A Multipart photo.", required = true) @RequestParam(name = "photo") photo : MultipartFile): ResponseEntity[_] = {
    val created = photoCommandService.addPhoto(photo)
    if(created == null) throw new ElementNotCreatedException[Photo](classOf[Photo])
    ResponseEntity.status(HttpStatus.CREATED).body(created)
  }

  @ApiOperation(value = "Update a photo.", response = classOf[Photo])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PutMapping(value = Array("/{id}"))
  def updatePhoto(@ApiParam(name = "id", value = "The Photo's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @RequestBody photo: Photo): ResponseEntity[_] = {
    val updated = photoCommandService.updatePhoto(id, photo)
    if(updated == null) throw new ElementNotUpdatedException[Photo](classOf[Photo])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update a Photo's caption.", response = classOf[Photo])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("caption"))
  def updatePhotoCaption(@ApiParam(name = "id", value = "The Photo's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "caption", value = "The photo's description.", required = true) @RequestParam(name = "caption") caption : String) = {
    val updated = photoCommandService.updatePhotoCaption(id, caption)
    if(updated == null) throw new ElementNotUpdatedException[Photo](classOf[Photo])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update a Photo's content.", response = classOf[Photo])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}/content"))
  def updatePhotoContent(@ApiParam(name = "id", value = "The Photo's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "photo", value = "The photo's content.", required = true) @RequestParam(name = "photo") photo : MultipartFile) = {
    val updated = photoCommandService.updatePhotoContent(id, photo)
    if(updated == null) throw new ElementNotUpdatedException[Photo](classOf[Photo])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update a Photo's creation date.", response = classOf[Photo])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("date"))
  def updatePhotoCreationDate(@ApiParam(name = "id", value = "The Photo's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "date", value = "The photo's creation date in the (dd/MM/yyyy) format.", required = true) @RequestParam(name = "date") date : String) = {
    val updated = photoCommandService.updatePhotoCreationDate(id, new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).parse(date))
    if(updated == null) throw new ElementNotUpdatedException[Photo](classOf[Photo])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update a Photo's format.", response = classOf[Photo])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("format"))
  def updatePhotoFormat(@ApiParam(name = "id", value = "The Photo's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "format", value = "The photo's format.", required = true) @RequestParam(name = "format") format : String) = {
    val updated = photoCommandService.updatePhotoExtension(id, format)
    if(updated == null) throw new ElementNotUpdatedException[Photo](classOf[Photo])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update a Photo's URL.", response = classOf[Photo])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("url"))
  def updatePhotoLink(@ApiParam(name = "id", value = "The Photo's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "url", value = "The photo's URL.", required = true) @RequestParam(name = "url") url : String) = {
    val updated = photoCommandService.updatePhotoLink(id, url)
    if(updated == null) throw new ElementNotUpdatedException[Photo](classOf[Photo])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Delete the Photo.", response = classOf[Photo])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @DeleteMapping(Array("/{id}"))
  def deletePhoto(@ApiParam(name = "id", value = "The Photo ID.", required = true, example = "1") @PathVariable(value = "id") photoId : Long) = {
    photoCommandService.deletePhoto(photoId)
    ResponseEntity.status(HttpStatus.NO_CONTENT).build()
  }
}
