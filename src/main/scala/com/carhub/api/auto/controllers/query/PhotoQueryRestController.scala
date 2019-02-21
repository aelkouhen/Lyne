package com.carhub.api.auto.controllers.query

import java.util

import com.carhub.api.auto.domain.Photo
import com.carhub.api.auto.services.query.PhotoQueryService
import com.carhub.api.auto.utils.exception.ElementNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/api/photos"))
class PhotoQueryRestController(@Autowired val photoQueryService : PhotoQueryService) {

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array(""))
  @ResponseBody
  def getPhotosList(@RequestParam page : Int,
                   @RequestParam size : Int,
                   @RequestParam(name = "order") sortDirection : String,
                   @RequestParam(name = "field") sort : String ) : ResponseEntity[_] =
  {
    var result : util.List[Photo] = new util.ArrayList[Photo]
    sortDirection.toLowerCase match {
      case "asc" => result = photoQueryService.getPhotosListAsc(page, size, sort)
      case _ => result = photoQueryService.getPhotosListDesc(page, size, sort)
    }
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Photo]()
    ResponseEntity.ok(result)
  }

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/count"))
  @ResponseBody
  def countAllPhotos() : Long = photoQueryService.countAllPhotos

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/name={name}"))
  @ResponseBody
  def findPhotosByName(@PathVariable(value = "name") name : String) : ResponseEntity[_]  = {
    val result = photoQueryService.findPhotosByName(name)
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Photo]()
    ResponseEntity.ok(result)
  }

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/format={ext}"))
  @ResponseBody
  def findPhotosByExtension(@PathVariable(value = "ext") extension : String) : ResponseEntity[_]  = {
    val result = photoQueryService.findPhotosByExtension(extension)
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Photo]()
    ResponseEntity.ok(result)
  }
}
