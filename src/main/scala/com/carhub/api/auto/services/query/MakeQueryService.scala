package com.carhub.api.auto.services.query

import java.util.UUID

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
    makeRepository.findAll(PageRequest.of(page, size, sortDirection, sort))

  def getMakesListAsc(page : Int, size: Int, sort : String) =
    getMakesPage(page, size, Sort.Direction.ASC, sort).getContent

  def getMakesListDesc(page : Int, size: Int, sort : String) =
    getMakesPage(page, size, Sort.Direction.DESC, sort).getContent

  def countAllMakes() = makeRepository.count

  def findMakesByName(name : String) = makeRepository.findMakesByName(name)

  def findMakeById(makeId : UUID) = makeRepository.findById(makeId).get
}
