package com.carhub.api.auto.controllers.query

import java.util

import com.carhub.api.auto.domain.File
import com.carhub.api.auto.services.query.FileQueryService
import com.carhub.api.auto.utils.exception.ElementNotFoundException
import io.swagger.annotations.{Api, ApiOperation, ApiParam}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._

@Api(value = "File", tags = Array("File"), description = "This API queries the File concept.")
@RestController
@RequestMapping(Array("/api/files"))
class FileQueryRestController(@Autowired val fileQueryService : FileQueryService) {

  @ApiOperation(value = "List the Files : Retrieve the Files list paged and sorted by field.", notes = "It takes the page number, a size for each page, a sorting order and the field on which the list is sorted.", response = classOf[util.List[File]], responseContainer = "List")
  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping
  @ResponseBody
  def getFilesList(@ApiParam(name = "page", example="0", value = "The page number.", required = true) @RequestParam page : Int,
                   @ApiParam(name = "size", example="10", value = "The size of the page.", required = true) @RequestParam size : Int,
                   @ApiParam(name = "order", example="DESC", value = "The sorting order (DESC or ASC).", required = true) @RequestParam(name = "order") sortDirection : String,
                   @ApiParam(name = "field", example="name", value = "The sort field name.", required = true) @RequestParam(name = "field") sort : String ) : ResponseEntity[_] =
  {
    var result : util.List[File] = new util.ArrayList[File]
    sortDirection.toLowerCase match {
      case "asc" => result = fileQueryService.getFilesListAsc(page, size, sort)
      case _ => result = fileQueryService.getFilesListDesc(page, size, sort)
    }
    if (result.isEmpty || result == null) throw new ElementNotFoundException[File](classOf[File])
    ResponseEntity.ok(result)
  }

  @ApiOperation(value = "Count the Files.", response = classOf[Long], responseContainer = "Long")
  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(value = Array("/count"))
  @ResponseBody
  def countAllFiles() : Long = fileQueryService.countAllFiles

  @ApiOperation(value = "Filter Files by name", response = classOf[util.List[File]], responseContainer = "List")
  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(value = Array("/find"), params = Array("name"))
  @ResponseBody
  def findFilesByName(@ApiParam(name = "name", value = "The filtering expression.", required = true) @RequestParam(value = "name") name : String) : ResponseEntity[_]  = {
    val result = fileQueryService.findFilesByName(name)
    if (result.isEmpty || result == null) throw new ElementNotFoundException[File](classOf[File])
    ResponseEntity.ok(result)
  }

  @ApiOperation(value = "Filter Files by format", response = classOf[util.List[File]], responseContainer = "List")
  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(value = Array("/find"), params = Array("format"))
  @ResponseBody
  def findFilesByExtension(@ApiParam(name = "format", value = "The filtering expression.", required = true) @RequestParam format : String) : ResponseEntity[_]  = {
    val result = fileQueryService.findFilesByExtension(format)
    if (result.isEmpty || result == null) throw new ElementNotFoundException[File](classOf[File])
    ResponseEntity.ok(result)
  }
}
