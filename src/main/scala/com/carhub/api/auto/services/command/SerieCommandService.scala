package com.carhub.api.auto.services.command

import java.util.UUID

import com.carhub.api.auto.domain.{Car, Model, Serie}
import com.carhub.api.auto.repositories.SerieRepository
import com.carhub.api.auto.services.query.ModelQueryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Autowired
@Transactional
@Service
class SerieCommandService(serieRepository : SerieRepository,
                          modelQueryService: ModelQueryService,
                          carCommandService: CarCommandService){

  def addSerie(serie : Serie) = serieRepository.save(serie)

  def updateSerie (serieId: UUID, serie : Serie) = {
    val serieToUpdate = serieRepository.getOne(serieId)
    serieToUpdate.model = serie.model
    serieToUpdate.name = serie.name
    serieToUpdate.productionEndYear = serie.productionEndYear
    serieToUpdate.productionStartYear = serie.productionStartYear
    serieToUpdate.vehicles.addAll(serie.vehicles)

    serieRepository.save(serieToUpdate)
  }

  def updateSerieModel (serieId : UUID, modelId : UUID) : Serie = {
    val model = modelQueryService.findModelById(modelId)
    updateSerieModel(serieId, model)
  }

  def updateSerieModel (serieId: UUID, model : Model) : Serie = {
    val serieToUpdate = serieRepository.getOne(serieId)
    serieToUpdate.model = model

    serieRepository.save(serieToUpdate)
  }

  def updateSerieName (serieId: UUID, name : String) = {
    val serieToUpdate = serieRepository.getOne(serieId)
    serieToUpdate.name = name

    serieRepository.save(serieToUpdate)
  }

  def updateSerieStartYear (serieId: UUID, productionStartYear : Int) = {
    val serieToUpdate = serieRepository.getOne(serieId)
    serieToUpdate.productionStartYear = productionStartYear

    serieRepository.save(serieToUpdate)
  }

  def updateSerieEndYear (serieId: UUID, productionEndYear : Int) = {
    val serieToUpdate = serieRepository.getOne(serieId)
    serieToUpdate.productionEndYear = productionEndYear

    serieRepository.save(serieToUpdate)
  }


  def updateSerieAddCar (serieId: UUID, car: Car) = {
    val carCreated = carCommandService.addCar(car)
    val serieToUpdate = serieRepository.getOne(serieId)
    carCommandService.updateCarSerie(carCreated.id, serieToUpdate)
    serieToUpdate.vehicles.add(car)

    serieRepository.save(serieToUpdate)
  }

  def deleteSerie(serieId: UUID) = {
    val serieToDelete = serieRepository.getOne(serieId)
    serieRepository.delete(serieToDelete)
  }
}
