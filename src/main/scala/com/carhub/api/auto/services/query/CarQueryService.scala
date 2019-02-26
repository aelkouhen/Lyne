package com.carhub.api.auto.services.query

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

  def getSerieCars(serieId : Long) = carRepository.getSerieCars(serieId)

  def getMakeCars(makeId : Long) = carRepository.getMakeCars(makeId)

  def getModelCars(modelId : Long) = carRepository.getModelCars(modelId)

  def findCarsByName(name : String) = carRepository.findCarsByName(name)

  def findCarById(carId : Long) = carRepository.findCarById(carId)

  def findCarFiles(carId : Long) = carRepository.findCarById(carId).getFiles

  def findCarPhotos(carId : Long) = carRepository.findCarById(carId).getImages

  def findCarVideos(carId : Long) = carRepository.findCarById(carId).getVideos
}
