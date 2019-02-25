package com.carhub.api.auto.controllers.query

import java.util

import com.carhub.api.auto.domain.Photo
import com.carhub.api.auto.services.query.PhotoQueryService
import com.carhub.api.auto.utils.exception.ElementNotFoundException
import io.swagger.annotations.{Api, ApiOperation, ApiParam}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._

@Api(value = "Photo", tags = Array("Photo"), description = "This API queries the Photo concept.")
@RestController
@RequestMapping(value = Array("/api/photos"))
class PhotoQueryRestController(@Autowired val photoQueryService : PhotoQueryService) {

  @ApiOperation(value = "List the Photos : Retrieve the Photos list paged and sorted by field.", notes = "It takes the page number, a size for each page, a sorting order and the field on which the list is sorted.", response = classOf[util.List[Photo]], responseContainer = "List")
  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(value = Array("/"))
  @ResponseBody
  def getPhotosList(@ApiParam(name = "page", example="0", value = "The page number.", required = true) @RequestParam page : Int,
                    @ApiParam(name = "size", example="10", value = "The size of the page.", required = true) @RequestParam size : Int,
                    @ApiParam(name = "order", example="DESC", value = "The sorting order (DESC or ASC).", required = true) @RequestParam(name = "order") sortDirection : String,
                    @ApiParam(name = "field", example="name", value = "The sort field name.", required = true) @RequestParam(name = "field") sort : String ) : ResponseEntity[_] =
  {
    var result : util.List[Photo] = new util.ArrayList[Photo]
    sortDirection.toLowerCase match {
      case "asc" => result = photoQueryService.getPhotosListAsc(page, size, sort)
      case _ => result = photoQueryService.getPhotosListDesc(page, size, sort)
    }
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Photo](classOf[Photo])
    ResponseEntity.ok(result)
  }

  @ApiOperation(value = "Count the Photos.", response = classOf[Long], responseContainer = "Long")
  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(value = Array("/count"))
  @ResponseBody
  def countAllPhotos() : Long = photoQueryService.countAllPhotos

  @ApiOperation(value = "Filter Photos by name", response = classOf[util.List[Photo]], responseContainer = "List")
  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(value = Array("/find"), params = Array("name"))
  @ResponseBody
  def findPhotosByName(@ApiParam(name = "name", value = "The filtering expression.", required = true) @RequestParam name : String) : ResponseEntity[_]  = {
    val result = photoQueryService.findPhotosByName(name)
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Photo](classOf[Photo])
    ResponseEntity.ok(result)
  }

  @ApiOperation(value = "Filter Photos by format", response = classOf[util.List[Photo]], responseContainer = "List")
  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(value = Array("/find"), params = Array("format"))
  @ResponseBody
  def findPhotosByExtension(@ApiParam(name = "format", value = "The filtering expression.", required = true) @RequestParam format : String) : ResponseEntity[_]  = {
    val result = photoQueryService.findPhotosByExtension(format)
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Photo](classOf[Photo])
    ResponseEntity.ok(result)
  }
}
