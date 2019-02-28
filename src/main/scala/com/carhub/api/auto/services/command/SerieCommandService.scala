package com.carhub.api.auto.services.command

import java.util

import com.carhub.api.auto.domain.{Model, Serie, Vehicle}
import com.carhub.api.auto.repositories.SerieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Autowired
@Transactional
@Service
class SerieCommandService(serieRepository : SerieRepository){

  def addSerie(serie : Serie) = serieRepository.save(serie)

  def updateSerie (serieId: Long, serie : Serie) = {
    val serieToUpdate = serieRepository.getOne(serieId)
    serieToUpdate.model = serie.model
    serieToUpdate.name = serie.name
    serieToUpdate.productionEndYear = serie.productionEndYear
    serieToUpdate.productionStartYear = serie.productionStartYear
    serieToUpdate.vehicles.addAll(serie.vehicles)

    serieRepository.save(serieToUpdate)
  }

  def updateSerieModel (serieId: Long, model : Model) = {
    val serieToUpdate = serieRepository.getOne(serieId)
    serieToUpdate.model = model
    serieRepository.save(serieToUpdate)
  }

  def updateSerieName (serieId: Long, name : String) = {
    val serieToUpdate = serieRepository.getOne(serieId)
    serieToUpdate.name = name

    serieRepository.save(serieToUpdate)
  }

  def updateSerieStartYear (serieId: Long, productionStartYear : Int) = {
    val serieToUpdate = serieRepository.getOne(serieId)
    serieToUpdate.productionStartYear = productionStartYear

    serieRepository.save(serieToUpdate)
  }

  def updateSerieEndYear (serieId: Long, productionEndYear : Int) = {
    val serieToUpdate = serieRepository.getOne(serieId)
    serieToUpdate.productionEndYear = productionEndYear

    serieRepository.save(serieToUpdate)
  }

  def updateSerieVehicles (serieId: Long, vehicles : util.List[Vehicle]) = {
    val serieToUpdate = serieRepository.getOne(serieId)
    serieToUpdate.vehicles.addAll(vehicles)

    serieRepository.save(serieToUpdate)
  }

  def updateSerieAddVehicle (serieId: Long, vehicle : Vehicle) = {
    val serieToUpdate = serieRepository.getOne(serieId)
    serieToUpdate.vehicles.add(vehicle)

    serieRepository.save(serieToUpdate)
  }

  def deleteSerie(serieId: Long) = serieRepository.delete(serieId)
}
