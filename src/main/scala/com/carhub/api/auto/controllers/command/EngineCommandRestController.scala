package com.carhub.api.auto.controllers.command

import com.carhub.api.auto.domain.Engine
import com.carhub.api.auto.services.command.EngineCommandService
import com.carhub.api.auto.utils.exception.{ElementNotCreatedException, ElementNotUpdatedException}
import io.swagger.annotations.{Api, ApiOperation, ApiParam}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._

@Api(value = "Engine", tags = Array("Engine Commands"), description = "This API commands the Engine concept.")
@RestController
@RequestMapping(value = Array("/api/engines"))
class EngineCommandRestController(@Autowired val engineCommandService : EngineCommandService) {

  @ApiOperation(value = "Create an Engine.", response = classOf[Engine])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @PostMapping(value = Array("/"))
  def createEngine(@ApiParam(name = "engine", value = "A Engine object.", required = true) @RequestBody engine: Engine): ResponseEntity[_] = {
    val created = engineCommandService.addEngine(engine)
    if(created == null) throw new ElementNotCreatedException[Engine](classOf[Engine])
    ResponseEntity.status(HttpStatus.CREATED).body(created)
  }

  @ApiOperation(value = "Update an Engine.", response = classOf[Engine])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PutMapping(value = Array("/{id}"))
  def updateEngine(@ApiParam(name = "id", value = "The Engine's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @RequestBody engine: Engine): ResponseEntity[_] = {
    val updated = engineCommandService.updateEngine(id, engine)
    if(updated == null) throw new ElementNotUpdatedException[Engine](classOf[Engine])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Engine's compression ratio.", response = classOf[Engine])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("compression"))
  def updateEngineCompressionRatio(@ApiParam(name = "id", value = "The Engine's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "compression", value = "The Compression ratio of the piston.", required = true, example = "0.0") @RequestParam(name = "compression") compressionRatio : Double) = {
    val updated = engineCommandService.updateEngineCompressionRatio(id, compressionRatio)
    if(updated == null) throw new ElementNotUpdatedException[Engine](classOf[Engine])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Engine's coolant capacity.", response = classOf[Engine])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("coolant"))
  def updateEngineCoolantCapacity(@ApiParam(name = "id", value = "The Engine's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "coolant", value = "The Engine Coolant capacity in (l).", required = true, example = "0.0") @RequestParam(name = "coolant") coolantCapacity : Double) = {
    val updated = engineCommandService.updateEngineCoolantCapacity(id, coolantCapacity)
    if(updated == null) throw new ElementNotUpdatedException[Engine](classOf[Engine])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Engine's cylinder bore.", response = classOf[Engine])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("bore"))
  def updateEngineCylinderBore(@ApiParam(name = "id", value = "The Engine's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "bore", value = "The bore is the diameter the cylinder in which a piston travels in (mm).", required = true, example = "0.0") @RequestParam(name = "bore") cylinderBore : Double) = {
    val updated = engineCommandService.updateEngineCylinderBore(id, cylinderBore)
    if(updated == null) throw new ElementNotUpdatedException[Engine](classOf[Engine])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Engine displacement.", response = classOf[Engine])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("displacement"))
  def updateEngineDisplacement(@ApiParam(name = "id", value = "The Engine's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "displacement", value = "The volume swept by all of the pistons inside the cylinders of an internal combustion engine in a single movement. Typical unit code(s): CMQ for cubic centimeter.", required = true, example = "0.0") @RequestParam(name = "displacement") engineDisplacement : Double) = {
    val updated = engineCommandService.updateEngineDisplacement(id, engineDisplacement)
    if(updated == null) throw new ElementNotUpdatedException[Engine](classOf[Engine])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Engine power.", response = classOf[Engine])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("power"))
  def updateEnginePower(@ApiParam(name = "id", value = "The Engine's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "power", value = "The power of the vehicle's engine. Typical unit code(s): KWT for kilowatt.", required = true, example = "0.0") @RequestParam(name = "power") enginePower : Double) = {
    val updated = engineCommandService.updateEnginePower(id, enginePower)
    if(updated == null) throw new ElementNotUpdatedException[Engine](classOf[Engine])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Engine's Fuel Type.", response = classOf[Engine])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("fuel"))
  def updateEngineFuelType(@ApiParam(name = "id", value = "The Engine's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "fuel", value = "The type of fuel suitable for the engine.", required = true) @RequestParam(name = "fuel") fuelType : String) = {
    val updated = engineCommandService.updateEngineFuelType(id, fuelType)
    if(updated == null) throw new ElementNotUpdatedException[Engine](classOf[Engine])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Engine's injection system.", response = classOf[Engine])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("injection"))
  def updateEngineInjectionSystem(@ApiParam(name = "id", value = "The Engine's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "injection", value = "The type of fuel injection system powering the engine.", required = true) @RequestParam(name = "injection") injectionSystem : String) = {
    val updated = engineCommandService.updateEngineInjectionSystem(id, injectionSystem)
    if(updated == null) throw new ElementNotUpdatedException[Engine](classOf[Engine])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Engine's name.", response = classOf[Engine])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("name"))
  def updateEngineName(@ApiParam(name = "id", value = "The Engine's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "name", value = "The name of the engine.", required = true) @RequestParam(name = "name") name : String) = {
    val updated = engineCommandService.updateEngineName(id, name)
    if(updated == null) throw new ElementNotUpdatedException[Engine](classOf[Engine])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Engine's number of cylinders.", response = classOf[Engine])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("cylinders"))
  def updateEngineNumberOfCylinder(@ApiParam(name = "id", value = "The Engine's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "cylinders", value = "The number of cylinders.", required = true, example = "0") @RequestParam(name = "cylinders") numberOfCylinders : Int) = {
    val updated = engineCommandService.updateEngineNumberOfCylinder(id, numberOfCylinders)
    if(updated == null) throw new ElementNotUpdatedException[Engine](classOf[Engine])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Engine's oil capacity.", response = classOf[Engine])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("oil"))
  def updateEngineOilCapacity(@ApiParam(name = "id", value = "The Engine's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "oil", value = "The Engine Oil capacity in (l).", required = true, example = "0.0") @RequestParam(name = "oil") oilCapacity : Double) = {
    val updated = engineCommandService.updateEngineOilCapacity(id, oilCapacity)
    if(updated == null) throw new ElementNotUpdatedException[Engine](classOf[Engine])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Engine's piston stroke.", response = classOf[Engine])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("piston"))
  def updateEnginePistonStroke(@ApiParam(name = "id", value = "The Engine's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "piston", value = "The Piston Stroke.", required = true, example = "0.0") @RequestParam(name = "piston") pistonStroke : Double) = {
    val updated = engineCommandService.updateEnginePistonStroke(id, pistonStroke)
    if(updated == null) throw new ElementNotUpdatedException[Engine](classOf[Engine])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Engine's position of cylinders.", response = classOf[Engine])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("position"))
  def updateEnginePositionOfCylinder(@ApiParam(name = "id", value = "The Engine's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "position", value = "The position of the cylinders.", required = true) @RequestParam(name = "position") positionOfCylinders : String) = {
    val updated = engineCommandService.updateEnginePositionOfCylinder(id, positionOfCylinders)
    if(updated == null) throw new ElementNotUpdatedException[Engine](classOf[Engine])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Engine's torque.", response = classOf[Engine])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("torque"))
  def updateEngineTorque(@ApiParam(name = "id", value = "The Engine's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "torque", value = "The torque (turning force) of the vehicle's engine. Typical unit code(s): NU for newton metre (N m).", required = true, example = "0") @RequestParam(name = "torque") torque : Int) = {
    val updated = engineCommandService.updateEngineTorque(id, torque)
    if(updated == null) throw new ElementNotUpdatedException[Engine](classOf[Engine])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Engine's number of valves per cylinder.", response = classOf[Engine])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("valves"))
  def updateEngineValvesPerCylynder(@ApiParam(name = "id", value = "The Engine's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "valves", value = "The Number of valves per cylinder.", required = true, example = "0") @RequestParam(name = "valves") valvesPerCylinder : Int) = {
    val updated = engineCommandService.updateEngineValvesPerCylynder(id, valvesPerCylinder)
    if(updated == null) throw new ElementNotUpdatedException[Engine](classOf[Engine])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Update the Engine's turbine system.", response = classOf[Engine])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @PatchMapping(value = Array("/{id}"), params = Array("turbine"))
  def updateEngineTurbineSystem(@ApiParam(name = "id", value = "The Engine's ID.", required = true, example = "1") @PathVariable(value = "id") id : Long, @ApiParam(name = "turbine", value = "The type of turbine system in the engine.", required = true) @RequestParam(name = "turbine") turbine : String) = {
    val updated = engineCommandService.updateEngineTurbineSystem(id, turbine)
    if(updated == null) throw new ElementNotUpdatedException[Engine](classOf[Engine])
    ResponseEntity.status(HttpStatus.OK).body(updated)
  }

  @ApiOperation(value = "Delete an Engine.", response = classOf[Engine])
  @PreAuthorize("hasRole('WRITE_PRIVILEGE')")
  @ResponseBody
  @DeleteMapping(Array("/{id}"))
  def deleteEngine(@ApiParam(name = "id", value = "The Engine ID.", required = true, example = "1") @PathVariable(value = "id") engineId : Long) = {
    engineCommandService.deleteEngine(engineId)
    ResponseEntity.status(HttpStatus.NO_CONTENT).build()
  }

}
