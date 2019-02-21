package com.carhub.api.auto.controllers.query

import java.util

import com.carhub.api.auto.domain.Video
import com.carhub.api.auto.services.query.VideoQueryService
import com.carhub.api.auto.utils.exception.ElementNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/api/videos"))
class VideoQueryRestController(@Autowired val videoQueryService : VideoQueryService) {

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array(""))
  @ResponseBody
  def getVideosList(@RequestParam page : Int,
                    @RequestParam size : Int,
                    @RequestParam(name = "order") sortDirection : String,
                    @RequestParam(name = "field") sort : String ) : ResponseEntity[_] =
  {
    var result : util.List[Video] = new util.ArrayList[Video]
    sortDirection.toLowerCase match {
      case "asc" => result = videoQueryService.getVideosListAsc(page, size, sort)
      case _ => result = videoQueryService.getVideosListDesc(page, size, sort)
    }
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Video]()
    ResponseEntity.ok(result)
  }

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/count"))
  @ResponseBody
  def countAllVideos() : Long = videoQueryService.countAllVideos

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/name={name}"))
  @ResponseBody
  def findVideosByName(@PathVariable(value = "name") name : String) : ResponseEntity[_]  = {
    val result = videoQueryService.findVideosByName(name)
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Video]()
    ResponseEntity.ok(result)
  }

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/format={ext}"))
  @ResponseBody
  def findVideosByExtension(@PathVariable(value = "ext") extension : String) : ResponseEntity[_]  = {
    val result = videoQueryService.findVideosByExtension(extension)
    if (result.isEmpty || result == null) throw new ElementNotFoundException[Video]()
    ResponseEntity.ok(result)
  }
}
