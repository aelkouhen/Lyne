package com.carhub.api.auto.controllers.command

import com.carhub.api.auto.domain.Car
import com.carhub.api.auto.services.command.CarCommandService
import com.carhub.api.auto.utils.exception.ElementNotCreatedException
import io.swagger.annotations.{Api, ApiParam}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._

@Api(value = "Car", tags = Array("Car"), description = "This API queries the Car concept.")
@RestController
@RequestMapping(Array("/api/cars"))
class CarCommandRestController(@Autowired val carCommandService : CarCommandService)  {

  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @PostMapping
  def create(@ApiParam(name = "car", value = "Car details", required = true) @RequestBody car: Car): ResponseEntity[_] = {
    val created = carCommandService.addCar(car)

    if(created == null) throw new ElementNotCreatedException[Car]()
    ResponseEntity.status(HttpStatus.CREATED).body(created)
  }
}
