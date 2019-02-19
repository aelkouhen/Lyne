package com.carhub.api.auto.controllers.query

import java.util

import com.carhub.api.auto.domain.File
import com.carhub.api.auto.services.query.FileQueryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/api/files"))
class FileQueryRestController(@Autowired val fileQueryService : FileQueryService) {

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array(""))
  @ResponseBody
  def getFilesList(@RequestParam page : Int,
                 @RequestParam size : Int,
                 @RequestParam(name = "order") sortDirection : String,
                 @RequestParam(name = "field") sort : String ) : ResponseEntity[_] =
  {
    var result : util.List[File] = new util.ArrayList[File]
    sortDirection.toLowerCase match {
      case "asc" => result = fileQueryService.getFilesListAsc(page, size, sort)
      case _ => result = fileQueryService.getFilesListDesc(page, size, sort)
    }

    ResponseEntity.ok(result)
  }

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/count"))
  @ResponseBody
  def countAllFiles() : Long = fileQueryService.countAllFiles()

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("/name/{name}"))
  @ResponseBody
  def findFileByName(@PathVariable(value = "name") name : String) : ResponseEntity[_]  = {
    val result = fileQueryService.findFileByName(name)
    ResponseEntity.ok(result)
  }

  @PreAuthorize("hasRole('READ_PRIVILEGE')")
  @GetMapping(Array("extension/{ext}"))
  @ResponseBody
  def findFileByExtension(@PathVariable(value = "ext") extension : String) : ResponseEntity[_]  = {
    val result = fileQueryService.findFileByExtension(extension)
    ResponseEntity.ok(result)
  }
}
