package com.carhub.api.auto.controllers.command

import java.text.SimpleDateFormat
import java.util.Locale

import com.carhub.api.auto.domain.File
import com.carhub.api.auto.services.command.FileCommandService
import com.carhub.api.auto.utils.exception.{ElementNotCreatedException, ElementNotUpdatedException}
import io.swagger.annotations.{Api, ApiOperation, ApiParam}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._
import org.springframework.web.multipart.MultipartFile

@Api(value = "File", tags = Array("File Commands"), description = "This API commands the File concept.")
@RestController
@RequestMapping(value = Array("/api/files"))
class FileCommandRestController(@Autowired val fileCommandService: FileCommandService) {

  @ApiOperation(value = "Create a file.", response = classOf[File])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @PostMapping(value = Array("/"))
  def createFile(@ApiParam(name = "file", value = "A File object.", required = true) @RequestBody file: File): ResponseEntity[_] = {
    val created = fileCommandService.addFile(file)
    if(created == null) throw new ElementNotCreatedException[File](classOf[File])
    ResponseEntity.status(HttpStatus.CREATED).body(created)
  }

  @ApiOperation(value = "Upload a file.", response = classOf[File])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @PostMapping(value = Array("/upload"))
  def uploadFile(@ApiParam(name = "file", value = "A Multipart file.", required = true) @RequestParam(name = "file") file : MultipartFile): ResponseEntity[_] = {
    val created = fileCommandService.addFile(file)
    if(created == null) throw new ElementNotCreatedException[File](classOf[File])
    ResponseEntity.status(HttpStatus.CREATED).body(created)
  }

  @ApiOperation(value = "Update a file.", response = classOf[File])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PutMapping(value = Array("/{id}"))
  def updateFile(@ApiParam(name = "id", value = "The File's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @RequestBody file: File): ResponseEntity[_] = {
    val updated = fileCommandService.updateFile(id, file)
    if(updated == null) throw new ElementNotUpdatedException[File](classOf[File])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update a File's caption.", response = classOf[File])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("caption"))
  def updateFileCaption(@ApiParam(name = "id", value = "The File's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "caption", value = "The file's description.", required = true) @RequestParam(name = "caption") caption : String) = {
    val updated = fileCommandService.updateFileCaption(id, caption)
    if(updated == null) throw new ElementNotUpdatedException[File](classOf[File])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update a File's content.", response = classOf[File])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}/content"))
  def updateFileContent(@ApiParam(name = "id", value = "The File's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "file", value = "The file's content.", required = true) @RequestParam(name = "file") file : MultipartFile) = {
    val updated = fileCommandService.updateFileContent(id, file)
    if(updated == null) throw new ElementNotUpdatedException[File](classOf[File])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update a File's creation date.", response = classOf[File])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("date"))
  def updateFileCreationDate(@ApiParam(name = "id", value = "The File's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "date", value = "The file's creation date in the (dd/MM/yyyy) format.", required = true) @RequestParam(name = "date") date : String) = {
    val updated = fileCommandService.updateFileCreationDate(id, new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).parse(date))
    if(updated == null) throw new ElementNotUpdatedException[File](classOf[File])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update a File's format.", response = classOf[File])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("format"))
  def updateFileExtension(@ApiParam(name = "id", value = "The File's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "format", value = "The file's format.", required = true) @RequestParam(name = "format") format : String) = {
    val updated = fileCommandService.updateFileExtension(id, format)
    if(updated == null) throw new ElementNotUpdatedException[File](classOf[File])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update a File's URL.", response = classOf[File])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("url"))
  def updateFileLink(@ApiParam(name = "id", value = "The File's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "url", value = "The file's URL.", required = true) @RequestParam(name = "url") url : String) = {
    val updated = fileCommandService.updateFileLink(id, url)
    if(updated == null) throw new ElementNotUpdatedException[File](classOf[File])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update a File's format.", response = classOf[File])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("size"))
  def updateFileSize(@ApiParam(name = "id", value = "The File's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "size", value = "The file's URL.", required = true, example = "0") @RequestParam(name = "size") size : Long) = {
    val updated = fileCommandService.updateFileSize(id, size)
    if(updated == null) throw new ElementNotUpdatedException[File](classOf[File])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Delete the File.", response = classOf[File])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @DeleteMapping(Array("/{id}"))
  def deleteFile(@ApiParam(name = "id", value = "The File ID.", required = true, example = "1") @PathVariable(value = "id") fileId : Long) = {
    fileCommandService.deleteFile(fileId)
    ResponseEntity.status(HttpStatus.NO_CONTENT).build()
  }
}
