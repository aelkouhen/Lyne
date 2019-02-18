package com.carhub.api.auto.services.command

import java.util
import java.util.Date

import com.carhub.api.auto.domain.{Make, Model, Serie}
import com.carhub.api.auto.repositories.ModelRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Autowired
@Transactional
@Service
class ModelCommandService(modelRepository : ModelRepository) {

  def addModel(model : Model) = modelRepository.save(model)

  def UpdateModel (model : Model) = {
    val modelToUpdate = modelRepository.getOne(model.id)
    modelToUpdate.creationDate = model.creationDate
    modelToUpdate.generation = model.generation
    modelToUpdate.make = model.make
    modelToUpdate.name = model.name
    modelToUpdate.series.addAll(model.series)

    modelRepository.save(modelToUpdate)
  }

  def UpdateModelMake (model : Model, make : Make) = {
    val modelToUpdate = modelRepository.getOne(model.id)
    modelToUpdate.make = make

    modelRepository.save(modelToUpdate)
  }

  def UpdateModelName (model : Model, name : String) = {
    val modelToUpdate = modelRepository.getOne(model.id)
    modelToUpdate.name = name

    modelRepository.save(modelToUpdate)
  }

  def UpdateModelGeneration(model : Model, generation : String) = {
    val modelToUpdate = modelRepository.getOne(model.id)
    modelToUpdate.generation = generation

    modelRepository.save(modelToUpdate)
  }

  def UpdateModelCreationDate (model : Model, creationDate : Date) = {
    val modelToUpdate = modelRepository.getOne(model.id)
    modelToUpdate.creationDate = creationDate

    modelRepository.save(modelToUpdate)
  }

  def UpdateModelSeries (model : Model, series : util.List[Serie]) = {
    val modelToUpdate = modelRepository.getOne(model.id)
    modelToUpdate.series.addAll(series)

    modelRepository.save(modelToUpdate)
  }

  def UpdateModelAddSerie (model : Model, serie : Serie) = {
    val modelToUpdate = modelRepository.getOne(model.id)
    modelToUpdate.series.add(serie)

    modelRepository.save(modelToUpdate)
  }

  def deleteModel(model : Model) = modelRepository.delete(model)
}
