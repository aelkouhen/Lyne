package com.carhub.api.auto.services.query

import com.carhub.api.auto.domain.{Model, Serie}
import com.carhub.api.auto.repositories.CarRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{PageRequest, Sort}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Autowired
@Transactional(readOnly = true)
@Service
class CarQueryService(carRepository: CarRepository){

  def getCarsPage(page : Int, size: Int, sortDirection : Sort.Direction, sort : String) =
    carRepository.findAll(new PageRequest(page, size, sortDirection, sort))

  def getCarsListAsc(page : Int, size: Int, sort : String) =
    getCarsPage(page, size, Sort.Direction.ASC, sort).getContent

  def getCarsListDesc(page : Int, size: Int, sort : String) =
    getCarsPage(page, size, Sort.Direction.DESC, sort).getContent

  def countAllCars() = carRepository.count

  def getSerieCars(serie : Serie) = carRepository.getSerieCars(serie.id)

  def getMakesCars(makeId : Long) = carRepository.getMakesCars(makeId)

  def getModelCars(model : Model) = carRepository.getModelCars(model.id)

  def findCarsByName(name : String) = carRepository.findCarsByName(name)
}
