package com.carhub.api.auto.services.query

import java.util.UUID

import com.carhub.api.auto.repositories.{MakeRepository, ModelRepository}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{PageRequest, Sort}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Autowired
@Transactional(readOnly = true)
@Service
class ModelQueryService(modelRepository: ModelRepository, makeRepository: MakeRepository) {

  def getModelsPage(page : Int, size: Int, sortDirection : Sort.Direction, sort : String) =
    modelRepository.findAll(PageRequest.of(page, size, sortDirection, sort))

  def getModelsListAsc(page : Int, size: Int, sort : String) =
    getModelsPage(page, size, Sort.Direction.ASC, sort).getContent

  def getModelsListDesc(page : Int, size: Int, sort : String) =
    getModelsPage(page, size, Sort.Direction.DESC, sort).getContent

  def countAllModels() = modelRepository.count

  def getMakeModels(makeId : UUID) = makeRepository.findById(makeId).get.models

  def findModelsByName(name : String) = modelRepository.findModelsByName(name)

  def findModelById(modelId : UUID) = modelRepository.findById(modelId).get
}
