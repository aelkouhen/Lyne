package com.carhub.api.auto.services.command

import java.util.{Date, UUID}

import com.carhub.api.auto.domain.{Make, Model, Serie}
import com.carhub.api.auto.repositories.ModelRepository
import com.carhub.api.auto.services.query.MakeQueryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Autowired
@Transactional
@Service
class ModelCommandService(modelRepository : ModelRepository,
                          makeQueryService: MakeQueryService,
                          serieCommandService: SerieCommandService) {

  def addModel(model : Model) = modelRepository.save(model)

  def updateModel (modelId : UUID, model : Model) = {
    val modelToUpdate = modelRepository.getOne(modelId)
    modelToUpdate.creationDate = model.creationDate
    modelToUpdate.generation = model.generation
    modelToUpdate.make = model.make
    modelToUpdate.name = model.name
    modelToUpdate.series.addAll(model.series)

    modelRepository.save(modelToUpdate)
  }

  def updateModelMake (modelId : UUID, makeId : UUID) : Model = {
    val make = makeQueryService.findMakeById(makeId)
    updateModelMake(modelId, make)
  }

  def updateModelMake (modelId : UUID, make : Make) : Model = {
    val modelToUpdate = modelRepository.getOne(modelId)
    modelToUpdate.make = make

    modelRepository.save(modelToUpdate)
  }

  def updateModelName (modelId : UUID, name : String) : Model  = {
    val modelToUpdate = modelRepository.getOne(modelId)
    modelToUpdate.name = name

    modelRepository.save(modelToUpdate)
  }

  def updateModelGeneration(modelId : UUID, generation : String) : Model  = {
    val modelToUpdate = modelRepository.getOne(modelId)
    modelToUpdate.generation = generation

    modelRepository.save(modelToUpdate)
  }

  def updateModelCreationDate (modelId : UUID, creationDate : Date) : Model  = {
    val modelToUpdate = modelRepository.getOne(modelId)
    modelToUpdate.creationDate = creationDate

    modelRepository.save(modelToUpdate)
  }

  def updateModelAddSerie (modelId : UUID, serie : Serie) : Model  = {
    val serieCreated = serieCommandService.addSerie(serie)
    val modelToUpdate = modelRepository.getOne(modelId)
    serieCommandService.updateSerieModel(serieCreated.id, modelToUpdate)
    modelToUpdate.series.add(serie)

    modelRepository.save(modelToUpdate)
  }

  def deleteModel(modelId : UUID) = {
    val modelToDelete = modelRepository.getOne(modelId)
    modelRepository.delete(modelToDelete)
  }
}
