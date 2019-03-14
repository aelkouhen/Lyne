package com.carhub.api.auto.controllers.query

import java.util
import java.util.UUID

import com.carhub.api.auto.domain.{Car, File, Photo, Video}
import com.carhub.api.auto.services.query.CarQueryService
import com.carhub.api.auto.utils.exception.ElementNotFoundException
import io.swagger.annotations.{Api, ApiOperation, ApiParam}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._

@Api(value = "Car", tags = Array("Car Queries"), description = "This API queries the Car concept.")
@RestController
@RequestMapping(value = Array("/api"))
class CarQueryRestController(@Autowired val carQueryService : CarQueryService) {

  @ApiOperation(value = "List the Cars : Retrieve the Cars list paged and sorted by field.", notes = "It takes the page number, a size for each page, a sorting order and the field on which the list is sorted.", response = classOf[util.List[Car]], responseContainer = "List")
  @PreAuthorize("#oauth2.hasScope('READ_PRIVILEGE')")
  @GetMapping(value = Array("/cars"))
  @ResponseBody
  def getCarsList(@ApiParam(name = "page", example="0", value = "The page number.", required = true) @RequestParam page : Int,
                  @ApiParam(name = "size", example="10", value = "The size of the page.", required = true) @RequestParam size : Int,
                  @ApiParam(name = "order", example="DESC", value = "The sorting order (DESC or ASC).", required = true) @RequestParam(name = "order") sortDirection : String,
                  @ApiParam(name = "field", example="name", value = "The sort field name.", required = true) @RequestParam(name = "field") sort : String ) : ResponseEntity[_] =
  {
    var result : util.List[Car] = new util.ArrayList[Car]
    sortDirection.toLowerCase match {
        case "asc" => result = carQueryService.getCarsListAsc(page, size, sort)
        case _ => result = carQueryService.getCarsListDesc(page, size, sort)
    }
    if (result == null || result.isEmpty) throw new ElementNotFoundException[Car](classOf[Car])
    ResponseEntity.ok(result)
  }

  @ApiOperation(value = "Count the Cars.", response = classOf[Long], responseContainer = "Long")
  @PreAuthorize("#oauth2.hasScope('READ_PRIVILEGE')")
  @GetMapping(value = Array("/cars/count"))
  @ResponseBody
  def countAllCars() : Long = carQueryService.countAllCars

  @ApiOperation(value = "Filter Cars by name", response = classOf[util.List[Car]], responseContainer = "List")
  @PreAuthorize("#oauth2.hasScope('READ_PRIVILEGE')")
  @GetMapping(value = Array("/cars/find"), params = Array("name"))
  @ResponseBody
  def findCarByName(@ApiParam(name = "name", value = "The filtering expression.", required = true) @RequestParam name : String) : ResponseEntity[_]  = {
    val result = carQueryService.findCarsByName(name)
    if (result == null || result.isEmpty) throw new ElementNotFoundException[Car](classOf[Car])
    ResponseEntity.ok(result)
  }

  @ApiOperation(value = "Filter Cars by ID", response = classOf[Car])
  @PreAuthorize("#oauth2.hasScope('READ_PRIVILEGE')")
  @GetMapping(value = Array("/cars/find"), params = Array("id"))
  @ResponseBody
  def findCarById(@ApiParam(name = "id", value = "The Car ID.", required = true) @RequestParam(name = "id") carId : String) : ResponseEntity[_]  = {
    val result = carQueryService.findCarById(UUID.fromString(carId))
    if (result == null) throw new ElementNotFoundException[Car](classOf[Car])
    ResponseEntity.ok(result)
  }

  @ApiOperation(value = "Get Car's related files.", responseContainer = "List")
  @PreAuthorize("#oauth2.hasScope('READ_PRIVILEGE')")
  @ResponseBody
  @GetMapping(value = Array("/cars/{id}/files"))
  def getCarsFiles(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") carId : String) = {
    val result = carQueryService.findCarFiles(UUID.fromString(carId))
    if (result == null || result.isEmpty) throw new ElementNotFoundException[File](classOf[File])
    ResponseEntity.ok(result)
  }

  @ApiOperation(value = "Get Car's related photos.", responseContainer = "List")
  @PreAuthorize("#oauth2.hasScope('READ_PRIVILEGE')")
  @ResponseBody
  @GetMapping(value = Array("/cars/{id}/photos"))
  def getCarsPhotos(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") carId : String) = {
    val result = carQueryService.findCarPhotos(UUID.fromString(carId))
    if (result == null || result.isEmpty) throw new ElementNotFoundException[Photo](classOf[Photo])
    ResponseEntity.ok(result)
  }

  @ApiOperation(value = "Get Car's related videos.", responseContainer = "List")
  @PreAuthorize("#oauth2.hasScope('READ_PRIVILEGE')")
  @ResponseBody
  @GetMapping(value = Array("/cars/{id}/videos"))
  def getCarsVideos(@ApiParam(name = "id", value = "The Car's ID.", required = true) @PathVariable(value = "id") carId : String) = {
    val result = carQueryService.findCarVideos(UUID.fromString(carId))
    if (result == null || result.isEmpty) throw new ElementNotFoundException[Video](classOf[Video])
    ResponseEntity.ok(result)
  }
}
