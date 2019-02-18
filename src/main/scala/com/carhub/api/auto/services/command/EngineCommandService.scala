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

  def updateEngine(engine : Engine)= {
    val engineToUpdate = engineRepository.getOne(engine.id)
    engineToUpdate.compressionRatio = engine.compressionRatio
    engineToUpdate.coolantCapacity = engine.coolantCapacity
    engineToUpdate.cylinderBore = engine.cylinderBore
    engineToUpdate.engineDisplacement = engine.engineDisplacement
    engineToUpdate.enginePower = engine.enginePower
    engineToUpdate.fuelType = engine.fuelType
    engineToUpdate.injectionSystem = engine.injectionSystem
    engineToUpdate.name = engine.name
    engineToUpdate.numberOfCylinders = engine.numberOfCylinders
    engineToUpdate.oilCapacity = engine.oilCapacity
    engineToUpdate.pistonStroke = engine.pistonStroke
    engineToUpdate.positionOfCylinders = engine.positionOfCylinders
    engineToUpdate.torque = engine.torque
    engineToUpdate.turbineSystem = engine.turbineSystem
    engineToUpdate.valvesPerCylinder = engine.valvesPerCylinder

    engineRepository.save(engineToUpdate)
  }

  def updateEngineCompressionRatio(engine : Engine, compressionRatio : Double)= {
    val engineToUpdate = engineRepository.getOne(engine.id)
    engineToUpdate.compressionRatio = compressionRatio

    engineRepository.save(engineToUpdate)
  }

  def updateEngineCoolantCapacity(engine : Engine, coolantCapacity : Double)= {
    val engineToUpdate = engineRepository.getOne(engine.id)
    engineToUpdate.coolantCapacity = coolantCapacity

    engineRepository.save(engineToUpdate)
  }

  def updateEngineCylinderBore(engine : Engine, cylinderBore : Double)= {
    val engineToUpdate = engineRepository.getOne(engine.id)
    engineToUpdate.cylinderBore = cylinderBore

    engineRepository.save(engineToUpdate)
  }

  def updateEngineDisplacement(engine : Engine, engineDisplacement : Double)= {
    val engineToUpdate = engineRepository.getOne(engine.id)
    engineToUpdate.engineDisplacement = engineDisplacement

    engineRepository.save(engineToUpdate)
  }

  def updateEnginePower(engine : Engine, enginePower : Double)= {
    val engineToUpdate = engineRepository.getOne(engine.id)
    engineToUpdate.enginePower = enginePower

    engineRepository.save(engineToUpdate)
  }

  def updateEngineFuelType(engine : Engine, fuelType : String)= {
    val engineToUpdate = engineRepository.getOne(engine.id)
    engineToUpdate.fuelType = Fuel.valueOf(fuelType)

    engineRepository.save(engineToUpdate)
  }

  def updateEngineInjectionSystem(engine : Engine, injectionSystem : String)= {
    val engineToUpdate = engineRepository.getOne(engine.id)
    engineToUpdate.injectionSystem = InjectionSystem.valueOf(injectionSystem)

    engineRepository.save(engineToUpdate)
  }

  def updateEngineName(engine : Engine, name : String)= {
    val engineToUpdate = engineRepository.getOne(engine.id)
    engineToUpdate.name = name

    engineRepository.save(engineToUpdate)
  }

  def updateEngineNumberOfCylinder(engine : Engine, numberOfCylinders : Int)= {
    val engineToUpdate = engineRepository.getOne(engine.id)
    engineToUpdate.numberOfCylinders = numberOfCylinders

    engineRepository.save(engineToUpdate)
  }

  def updateEngineOilCapacity(engine : Engine, oilCapacity : Double)= {
    val engineToUpdate = engineRepository.getOne(engine.id)
    engineToUpdate.oilCapacity = oilCapacity

    engineRepository.save(engineToUpdate)
  }

  def updateEnginePistonStroke(engine : Engine, pistonStroke : Double)= {
    val engineToUpdate = engineRepository.getOne(engine.id)
    engineToUpdate.pistonStroke = pistonStroke

    engineRepository.save(engineToUpdate)
  }

  def updateEnginePositionOfCylinder(engine : Engine, positionOfCylinders : String)= {
    val engineToUpdate = engineRepository.getOne(engine.id)
    engineToUpdate.positionOfCylinders = CylinderPosition.valueOf(positionOfCylinders)

    engineRepository.save(engineToUpdate)
  }

  def updateEngineTorque(engine : Engine, torque : Int)= {
    val engineToUpdate = engineRepository.getOne(engine.id)
    engineToUpdate.torque = torque

    engineRepository.save(engineToUpdate)
  }

  def updateEngineTurbineSystem(engine : Engine, turbine : String)= {
    val engineToUpdate = engineRepository.getOne(engine.id)
    engineToUpdate.turbineSystem = TurbineSystem.valueOf(turbine)

    engineRepository.save(engineToUpdate)
  }

  def updateEngineValvesPerCylynder(engine : Engine, valvesPerCylinder : Int)= {
    val engineToUpdate = engineRepository.getOne(engine.id)
    engineToUpdate.valvesPerCylinder = valvesPerCylinder

    engineRepository.save(engineToUpdate)
  }

  def deleteEngine(engine : Engine)= engineRepository.delete(engine)
}
