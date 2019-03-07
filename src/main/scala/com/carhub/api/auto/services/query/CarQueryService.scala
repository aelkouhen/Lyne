package com.carhub.api.auto.services.query

import java.util
import java.util.UUID

import com.carhub.api.auto.domain.{Car, Vehicle}
import com.carhub.api.auto.repositories.{CarRepository, MakeRepository, ModelRepository, SerieRepository}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{PageRequest, Sort}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import scala.collection.JavaConverters._

@Autowired
@Transactional(readOnly = true)
@Service
class CarQueryService(carRepository: CarRepository,
                      serieRepository: SerieRepository,
                      modelRepository: ModelRepository,
                      makeRepository: MakeRepository){

  def getCarsPage(page : Int, size: Int, sortDirection : Sort.Direction, sort : String) =
    carRepository.findAll(PageRequest.of(page, size, sortDirection, sort))

  def getCarsListAsc(page : Int, size: Int, sort : String) =
    getCarsPage(page, size, Sort.Direction.ASC, sort).getContent

  def getCarsListDesc(page : Int, size: Int, sort : String) =
    getCarsPage(page, size, Sort.Direction.DESC, sort).getContent

  def countAllCars() = carRepository.count

  def getSerieCars(serieId : UUID) = {
    val vehicles = asScalaBuffer(serieRepository.findById(serieId).get.vehicles)
    new util.ArrayList[Car]((asJavaCollection(vehicles.filter(_.isInstanceOf[Car]).asInstanceOf[Seq[Car]])))
  }

  def getMakeCars(makeId : UUID) = {
    var vehicles = new util.ArrayList[Vehicle]()
    makeRepository.findById(makeId).get.models.forEach(m => m.series.forEach(s => vehicles.addAll(s.vehicles)))
    val cars = asJavaCollection(asScalaBuffer(vehicles).filter(_.isInstanceOf[Car]).asInstanceOf[Seq[Car]])

    new util.ArrayList[Car](cars)
  }

  def getModelCars(modelId : UUID) = {
    var vehicles = new util.ArrayList[Vehicle]()
    modelRepository.findById(modelId).get.series.forEach(s => vehicles.addAll(s.vehicles))
    val cars = asJavaCollection(asScalaBuffer(vehicles).filter(_.isInstanceOf[Car]).asInstanceOf[Seq[Car]])

    new util.ArrayList[Car](cars)
  }

  def findCarsByName(name : String) = carRepository.findCarsByName(name)

  def findCarById(carId : UUID) = carRepository.findById(carId).get

  def findCarFiles(carId : UUID) = carRepository.findById(carId).get.getFiles

  def findCarPhotos(carId : UUID) = {
    carRepository.findById(carId).get.getImages
  }

  def findCarVideos(carId : UUID) = carRepository.findById(carId).get.getVideos
}
