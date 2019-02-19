package com.carhub.api.auto.services.query

import com.carhub.api.auto.domain.Model
import com.carhub.api.auto.repositories.ModelRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{PageRequest, Sort}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Autowired
@Transactional(readOnly = true)
@Service
class ModelQueryService(modelRepository: ModelRepository) {

  def getModelsPage(page : Int, size: Int, sortDirection : Sort.Direction, sort : String) =
    modelRepository.findAll(new PageRequest(page, size, sortDirection, sort))

  def getModelsListAsc(page : Int, size: Int, sort : String) =
    getModelsPage(page, size, Sort.Direction.ASC, sort).getContent

  def getModelsListDesc(page : Int, size: Int, sort : String) =
    getModelsPage(page, size, Sort.Direction.DESC, sort).getContent

  def countAllModels() = modelRepository.count

  def getModelSeries(model : Model) = modelRepository.getModelSeries(model.id)

  def getModelCars(model : Model) = modelRepository.getModelCars(model.id)
}
