package com.carhub.api.auto.services.query

import java.util.UUID

import com.carhub.api.auto.domain.enumerations.FuelType
import com.carhub.api.auto.repositories.EngineRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{PageRequest, Sort}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Autowired
@Transactional(readOnly = true)
@Service
class EngineQueryService(engineRepository: EngineRepository) {

  def getEnginesPage(page : Int, size: Int, sortDirection : Sort.Direction, sort : String) =
    engineRepository.findAll(PageRequest.of(page, size, sortDirection, sort))

  def getEnginesListAsc(page : Int, size: Int, sort : String) =
    getEnginesPage(page, size, Sort.Direction.ASC, sort).getContent

  def getEnginesListDesc(page : Int, size: Int, sort : String) =
    getEnginesPage(page, size, Sort.Direction.DESC, sort).getContent

  def countAllEngines() = engineRepository.count

  def findEnginesByName(name : String) = engineRepository.findEnginesByName(name)

  def findEngineById(engineId : UUID) = engineRepository.findById(engineId).get

  def findEnginesByFuelType(fuelType : String) = engineRepository.findEnginesByFuelType(FuelType.valueOf(fuelType.toUpperCase).toString)
}
