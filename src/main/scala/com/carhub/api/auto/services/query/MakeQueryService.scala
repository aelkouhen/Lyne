package com.carhub.api.auto.services.query

import com.carhub.api.auto.domain.Make
import com.carhub.api.auto.repositories.MakeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{PageRequest, Sort}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Autowired
@Transactional(readOnly = true)
@Service
class MakeQueryService(makeRepository: MakeRepository) {

  def getMakesPage(page : Int, size: Int, sortDirection : Sort.Direction, sort : String) =
    makeRepository.findAll(new PageRequest(page, size, sortDirection, sort))

  def getMakesListAsc(page : Int, size: Int, sort : String) =
    getMakesPage(page, size, Sort.Direction.ASC, sort).getContent

  def getMakesListDesc(page : Int, size: Int, sort : String) =
    getMakesPage(page, size, Sort.Direction.DESC, sort).getContent

  def countAllMakes() = makeRepository.count

  def getMakesModels(make : Make) = makeRepository.getMakesModels(make.id)

  def getMakesSeries(make : Make) = makeRepository.getMakesSeries(make.id)

  def getMakesCars(make : Make) = makeRepository.getMakesCars(make.id)
}
