package com.carhub.api.auto.services.command

import java.util.UUID

import com.carhub.api.auto.domain._
import com.carhub.api.auto.domain.enumerations._
import com.carhub.api.auto.repositories.EngineRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Autowired
@Transactional
@Service
class EngineCommandService(engineRepository: EngineRepository) {

  def addEngine(engine : Engine)= engineRepository.save(engine)

  def updateEngine(engineId : UUID, engine : Engine)= {
    val engineToUpdate = engineRepository.getOne(engineId)
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
    engineToUpdate.cylinderPosition = engine.cylinderPosition
    engineToUpdate.torque = engine.torque
    engineToUpdate.turbineSystem = engine.turbineSystem
    engineToUpdate.valvesPerCylinder = engine.valvesPerCylinder

    engineRepository.save(engineToUpdate)
  }

  def updateEngineCompressionRatio(engineId : UUID, compressionRatio : Double)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.compressionRatio = compressionRatio

    engineRepository.save(engineToUpdate)
  }

  def updateEngineCoolantCapacity(engineId : UUID, coolantCapacity : Double)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.coolantCapacity = coolantCapacity

    engineRepository.save(engineToUpdate)
  }

  def updateEngineCylinderBore(engineId : UUID, cylinderBore : Double)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.cylinderBore = cylinderBore

    engineRepository.save(engineToUpdate)
  }

  def updateEngineDisplacement(engineId : UUID, engineDisplacement : Double)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.engineDisplacement = engineDisplacement

    engineRepository.save(engineToUpdate)
  }

  def updateEnginePower(engineId : UUID, enginePower : Double)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.enginePower = enginePower

    engineRepository.save(engineToUpdate)
  }

  def updateEngineFuelType(engineId : UUID, fuelType : String)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.fuelType = FuelType.valueOf(fuelType)

    engineRepository.save(engineToUpdate)
  }

  def updateEngineInjectionSystem(engineId : UUID, injectionSystem : String)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.injectionSystem = InjectionSystem.valueOf(injectionSystem)

    engineRepository.save(engineToUpdate)
  }

  def updateEngineName(engineId : UUID, name : String)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.name = name

    engineRepository.save(engineToUpdate)
  }

  def updateEngineNumberOfCylinder(engineId : UUID, numberOfCylinders : Int)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.numberOfCylinders = numberOfCylinders

    engineRepository.save(engineToUpdate)
  }

  def updateEngineOilCapacity(engineId : UUID, oilCapacity : Double)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.oilCapacity = oilCapacity

    engineRepository.save(engineToUpdate)
  }

  def updateEnginePistonStroke(engineId : UUID, pistonStroke : Double)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.pistonStroke = pistonStroke

    engineRepository.save(engineToUpdate)
  }

  def updateEnginePositionOfCylinder(engineId : UUID, positionOfCylinders : String)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.cylinderPosition = CylinderPosition.valueOf(positionOfCylinders)

    engineRepository.save(engineToUpdate)
  }

  def updateEngineTorque(engineId : UUID, torque : Int)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.torque = torque

    engineRepository.save(engineToUpdate)
  }

  def updateEngineTurbineSystem(engineId : UUID, turbine : String)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.turbineSystem = TurbineSystem.valueOf(turbine)

    engineRepository.save(engineToUpdate)
  }

  def updateEngineValvesPerCylynder(engineId : UUID, valvesPerCylinder : Int)= {
    val engineToUpdate = engineRepository.getOne(engineId)
    engineToUpdate.valvesPerCylinder = valvesPerCylinder

    engineRepository.save(engineToUpdate)
  }

  def deleteEngine(engineId : UUID)= {
    val engineToDelete = engineRepository.getOne(engineId)
    if (engineToDelete != null) engineRepository.delete(engineToDelete)
  }
}
