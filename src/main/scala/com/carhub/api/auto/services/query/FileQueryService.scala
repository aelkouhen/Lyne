package com.carhub.api.auto.services.query

import com.carhub.api.auto.repositories.FileRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{PageRequest, Sort}
import org.springframework.stereotype.Service

@Autowired
@Service
class FileQueryService(fileRepository : FileRepository) {

  def getFilesPage(page : Int, size: Int, sortDirection : String, sort : String) =
    fileRepository.findAll(new PageRequest(page, size, new Sort(Sort.Direction.valueOf(sortDirection), sort)))

  def getFilesList(page : Int, size: Int, sortDirection : String, sort : String) =
    getFilesPage(page, size, sortDirection, sort).getContent

  def countAllFiles() = fileRepository.count
}
