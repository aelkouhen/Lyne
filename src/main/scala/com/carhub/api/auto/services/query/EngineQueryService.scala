package com.carhub.api.auto.services.query

import com.carhub.api.auto.repositories.EngineRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{PageRequest, Sort}
import org.springframework.stereotype.Service

@Autowired
@Service
class EngineQueryService(engineRepository: EngineRepository) {

  def getEnginesPage(page : Int, size: Int, sortDirection : String, sort : String) =
    engineRepository.findAll(new PageRequest(page, size, new Sort(Sort.Direction.valueOf(sortDirection), sort)))

  def getEnginesList(page : Int, size: Int, sortDirection : String, sort : String) =
    getEnginesPage(page, size, sortDirection, sort).getContent

  def countAllEngines() = engineRepository.count

  def findEngineByName(name : String) = engineRepository.findByName(name)

  def findEngineByFuelType(fuelType : String) = engineRepository.findByFuelType(fuelType)
}
