package com.carhub.api.auto.services.query

import com.carhub.api.auto.repositories.CarRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{PageRequest, Sort}
import org.springframework.stereotype.Service

@Autowired
@Service
class CarQueryService(carRepository: CarRepository){

  def getCarsPage(page : Int, size: Int, sortDirection : String, sort : String) =
    carRepository.findAll(new PageRequest(page, size, new Sort(Sort.Direction.valueOf(sortDirection), sort)))

  def getCarsList(page : Int, size: Int, sortDirection : String, sort : String) =
    getCarsPage(page, size, sortDirection, sort).getContent

  def countAllCars() = carRepository.count

  def findCarByName(name : String) = carRepository.findByName(name)

  def findCarById(id : Long) = carRepository.findOne(id)
}
