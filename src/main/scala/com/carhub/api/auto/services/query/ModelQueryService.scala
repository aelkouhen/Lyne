package com.carhub.api.auto.services.query

import com.carhub.api.auto.domain.Model
import com.carhub.api.auto.repositories.ModelRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{PageRequest, Sort}
import org.springframework.stereotype.Service

@Autowired
@Service
class ModelQueryService(modelRepository: ModelRepository) {

  def getModelsPage(page : Int, size: Int, sortDirection : String, sort : String) =
    modelRepository.findAll(new PageRequest(page, size, new Sort(Sort.Direction.valueOf(sortDirection), sort)))

  def getModelsList(page : Int, size: Int, sortDirection : String, sort : String) =
    getModelsPage(page, size, sortDirection, sort).getContent

  def countAllModels() = modelRepository.count

  def getModelSeries(model : Model) = modelRepository.getModelSeries(model.id)

  def getModelCars(model : Model) = modelRepository.getModelCars(model.id)
}
