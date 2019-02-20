package com.carhub.api.auto.controllers.query

import java.util

import com.carhub.api.auto.domain.Resource
import com.carhub.api.auto.services.query.{ResourceQueryService, FileQueryService}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/api/resources"))
class ResourceQueryRestController(@Autowired val abstractFileQueryService : ResourceQueryService) {

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array(""))
  @ResponseBody
  def getFilesList(@RequestParam page : Int,
                 @RequestParam size : Int,
                 @RequestParam(name = "order") sortDirection : String,
                 @RequestParam(name = "field") sort : String ) : ResponseEntity[_] =
  {
    var result : util.List[Resource] = new util.ArrayList[Resource]
    sortDirection.toLowerCase match {
      case "asc" => result = abstractFileQueryService.getAbstractFilesListAsc(page, size, sort)
      case _ => result = abstractFileQueryService.getAbstractFilesListDesc(page, size, sort)
    }

    ResponseEntity.ok(result)
  }

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/count"))
  @ResponseBody
  def countAllFiles() : Long = abstractFileQueryService.countAllAbstractFiles()

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/name={name}"))
  @ResponseBody
  def findFileByName(@PathVariable(value = "name") name : String) : ResponseEntity[_]  = {
    val result = abstractFileQueryService.findAbstractFilesByName(name)
    ResponseEntity.ok(result)
  }

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/format={ext}"))
  @ResponseBody
  def findFileByExtension(@PathVariable(value = "ext") extension : String) : ResponseEntity[_]  = {
    val result = abstractFileQueryService.findAbstractFilesByExtension(extension)
    ResponseEntity.ok(result)
  }
}
