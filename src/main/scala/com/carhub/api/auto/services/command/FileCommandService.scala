package com.carhub.api.auto.services.command

import java.util.{Calendar, Date}

import com.carhub.api.auto.domain.File
import com.carhub.api.auto.repositories.FileRepository
import com.google.common.io.Files
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Autowired
@Transactional
@Service
class FileCommandService(fileRepository : FileRepository) {

  def addFile(file : MultipartFile) : File = {
    val fileMeta = new File
    fileMeta.size = file.getSize
    fileMeta.name = Files.getNameWithoutExtension(file.getOriginalFilename)
    fileMeta.format = Files.getFileExtension(file.getOriginalFilename)
    fileMeta.content = file.getBytes
    fileMeta.created = Calendar.getInstance().getTime()
    addFile(fileMeta)
  }

  def addFile(file : File) : File = fileRepository.save(file)

  def updateFile(fileId : Long, file : File) = {
    val fileToUpdate = fileRepository.getOne(fileId)
    fileToUpdate.caption = file.caption
    fileToUpdate.content = file.content
    fileToUpdate.created = file.created
    fileToUpdate.format = file.format
    fileToUpdate.url = file.url
    fileToUpdate.size = file.size

    fileRepository.save(fileToUpdate)
  }

  def updateFileCaption(fileId : Long, caption : String) = {
    val fileToUpdate = fileRepository.getOne(fileId)
    fileToUpdate.caption = caption

    fileRepository.save(fileToUpdate)
  }

  def updateFileContent(fileId : Long, content : Array[Byte]) = {
    val fileToUpdate = fileRepository.getOne(fileId)
    fileToUpdate.content = content

    fileRepository.save(fileToUpdate)
  }

  def updateFileCreationDate(fileId : Long, date : Date) = {
    val fileToUpdate = fileRepository.getOne(fileId)
    fileToUpdate.created = date

    fileRepository.save(fileToUpdate)
  }

  def updateFileExtension(fileId : Long, format : String) = {
    val fileToUpdate = fileRepository.getOne(fileId)
    fileToUpdate.format = format

    fileRepository.save(fileToUpdate)
  }

  def updateFileLink(fileId : Long, url : String) = {
    val fileToUpdate = fileRepository.getOne(fileId)
    fileToUpdate.url = url

    fileRepository.save(fileToUpdate)
  }

  def updateFileSize(fileId : Long, size : Long) = {
    val fileToUpdate = fileRepository.getOne(fileId)
    fileToUpdate.size = size

    fileRepository.save(fileToUpdate)
  }

  def deleteFile(fileId : Long) = {
    val fileToDelete = fileRepository.getOne(fileId)
    fileRepository.delete(fileToDelete)
  }
}
