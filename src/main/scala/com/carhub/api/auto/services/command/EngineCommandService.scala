package com.carhub.api.auto.services.command

import com.carhub.api.auto.domain._
import com.carhub.api.auto.repositories.EngineRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Autowired
@Transactional
@Service
class EngineCommandService(engineRepository: EngineRepository) {

  def addEngine(engine : Engine)= engineRepository.save(engine)

  def updateEngine(engineId : Long, engine : Engine)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.compressionRatio = engine.compressionRatio
    engineToUpdate.coolantCapacity = engine.coolantCapacity
    engineToUpdate.cylinderBore = engine.cylinderBore
    engineToUpdate.engineDisplacement = engine.engineDisplacement
    engineToUpdate.enginePower = engine.enginePower
    engineToUpdate.fuel = engine.fuel
    engineToUpdate.injection = engine.injection
    engineToUpdate.name = engine.name
    engineToUpdate.numberOfCylinders = engine.numberOfCylinders
    engineToUpdate.oilCapacity = engine.oilCapacity
    engineToUpdate.pistonStroke = engine.pistonStroke
    engineToUpdate.cylinders = engine.cylinders
    engineToUpdate.torque = engine.torque
    engineToUpdate.turbine = engine.turbine
    engineToUpdate.valvesPerCylinder = engine.valvesPerCylinder

    engineRepository.save(engineToUpdate)
  }

  def updateEngineCompressionRatio(engineId : Long, compressionRatio : Double)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.compressionRatio = compressionRatio

    engineRepository.save(engineToUpdate)
  }

  def updateEngineCoolantCapacity(engineId : Long, coolantCapacity : Double)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.coolantCapacity = coolantCapacity

    engineRepository.save(engineToUpdate)
  }

  def updateEngineCylinderBore(engineId : Long, cylinderBore : Double)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.cylinderBore = cylinderBore

    engineRepository.save(engineToUpdate)
  }

  def updateEngineDisplacement(engineId : Long, engineDisplacement : Double)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.engineDisplacement = engineDisplacement

    engineRepository.save(engineToUpdate)
  }

  def updateEnginePower(engineId : Long, enginePower : Double)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.enginePower = enginePower

    engineRepository.save(engineToUpdate)
  }

  def updateEngineFuelType(engineId : Long, fuelType : String)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.fuel = Fuel.valueOf(fuelType)

    engineRepository.save(engineToUpdate)
  }

  def updateEngineInjectionSystem(engineId : Long, injectionSystem : String)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.injection = Injection.valueOf(injectionSystem)

    engineRepository.save(engineToUpdate)
  }

  def updateEngineName(engineId : Long, name : String)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.name = name

    engineRepository.save(engineToUpdate)
  }

  def updateEngineNumberOfCylinder(engineId : Long, numberOfCylinders : Int)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.numberOfCylinders = numberOfCylinders

    engineRepository.save(engineToUpdate)
  }

  def updateEngineOilCapacity(engineId : Long, oilCapacity : Double)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.oilCapacity = oilCapacity

    engineRepository.save(engineToUpdate)
  }

  def updateEnginePistonStroke(engineId : Long, pistonStroke : Double)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.pistonStroke = pistonStroke

    engineRepository.save(engineToUpdate)
  }

  def updateEnginePositionOfCylinder(engineId : Long, positionOfCylinders : String)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.cylinders = Cylinder.valueOf(positionOfCylinders)

    engineRepository.save(engineToUpdate)
  }

  def updateEngineTorque(engineId : Long, torque : Int)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.torque = torque

    engineRepository.save(engineToUpdate)
  }

  def updateEngineTurbineSystem(engineId : Long, turbine : String)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.turbine = Turbine.valueOf(turbine)

    engineRepository.save(engineToUpdate)
  }

  def updateEngineValvesPerCylynder(engineId : Long, valvesPerCylinder : Int)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.valvesPerCylinder = valvesPerCylinder

    engineRepository.save(engineToUpdate)
  }

  def deleteEngine(engineId : Long)= {
    val engineToDelete = engineRepository.getOne(engineId)
    if (engineToDelete != null) engineRepository.delete(engineToDelete)
  }
}
