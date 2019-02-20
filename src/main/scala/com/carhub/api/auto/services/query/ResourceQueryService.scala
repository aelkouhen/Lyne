package com.carhub.api.auto.services.query

import com.carhub.api.auto.repositories.ResourceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{PageRequest, Sort}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Autowired
@Transactional(readOnly = true)
@Service
class ResourceQueryService(abstractFileRepository : ResourceRepository) {

  def getAbstractFilesPage(page : Int, size: Int, sortDirection : Sort.Direction, sort : String) =
    abstractFileRepository.findAll(new PageRequest(page, size, sortDirection, sort))

  def getAbstractFilesListAsc(page : Int, size: Int, sort : String) =
    getAbstractFilesPage(page, size, Sort.Direction.ASC, sort).getContent

  def getAbstractFilesListDesc(page : Int, size: Int, sort : String) =
    getAbstractFilesPage(page, size, Sort.Direction.DESC, sort).getContent

  def countAllAbstractFiles() = abstractFileRepository.count

  def findAbstractFilesByName(name : String) = abstractFileRepository.findFilesByName(name)

  def findAbstractFilesByExtension(ext : String) = abstractFileRepository.findFilesByExtension(ext)
}
