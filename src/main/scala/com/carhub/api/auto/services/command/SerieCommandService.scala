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

  def UpdateSerie (serie : Serie) = {
    val serieToUpdate = serieRepository.getOne(serie.id)
    serieToUpdate.model = serie.model
    serieToUpdate.name = serie.name
    serieToUpdate.productionEndYear = serie.productionEndYear
    serieToUpdate.productionStartYear = serie.productionStartYear
    serieToUpdate.vehicles.addAll(serie.vehicles)

    serieRepository.save(serieToUpdate)
  }

  def UpdateSerieModel (serie: Serie, model : Model) = {
    val serieToUpdate = serieRepository.getOne(model.id)
    serieToUpdate.model = model
    serieRepository.save(serieToUpdate)
  }

  def UpdateSerieName (serie : Serie, name : String) = {
    val serieToUpdate = serieRepository.getOne(serie.id)
    serieToUpdate.name = name

    serieRepository.save(serieToUpdate)
  }

  def UpdateSerieStartYear (serie : Serie, productionStartYear : Int) = {
    val serieToUpdate = serieRepository.getOne(serie.id)
    serieToUpdate.productionStartYear = productionStartYear

    serieRepository.save(serieToUpdate)
  }

  def UpdateSerieEndYear (serie : Serie, productionEndYear : Int) = {
    val serieToUpdate = serieRepository.getOne(serie.id)
    serieToUpdate.productionEndYear = productionEndYear

    serieRepository.save(serieToUpdate)
  }

  def UpdateSerieVehicles (serie : Serie, vehicles : util.List[Vehicle]) = {
    val serieToUpdate = serieRepository.getOne(serie.id)
    serieToUpdate.vehicles.addAll(vehicles)

    serieRepository.save(serieToUpdate)
  }

  def UpdateSerieAddVehicle (serie : Serie, vehicle : Vehicle) = {
    val serieToUpdate = serieRepository.getOne(serie.id)
    serieToUpdate.vehicles.add(vehicle)

    serieRepository.save(serieToUpdate)
  }

  def deleteSerie(serie : Serie) = serieRepository.delete(serie)
}
