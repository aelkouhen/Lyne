package com.carhub.api.auto.services.command

import java.util.Date

import com.carhub.api.auto.domain.{Car, File}
import com.carhub.api.auto.repositories.FileRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Autowired
@Service
class FileCommandService(fileRepository : FileRepository) {

  def addFile(file : File) = fileRepository.save(file)

  def UpdateFile(file : File) = {
    val fileToUpdate = fileRepository.getOne(file.id)
    fileToUpdate.caption = file.caption
    fileToUpdate.content = file.content
    fileToUpdate.created = file.created
    fileToUpdate.extension = file.extension
    fileToUpdate.link = file.link
    fileToUpdate.size = file.size

    fileRepository.save(fileToUpdate)
  }

  def UpdateFileCaption(file : File, caption : String) = {
    val fileToUpdate = fileRepository.getOne(file.id)
    fileToUpdate.caption = caption

    fileRepository.save(fileToUpdate)
  }

  def UpdateFileContent(file : File, content : Array[Byte]) = {
    val fileToUpdate = fileRepository.getOne(file.id)
    fileToUpdate.content = content

    fileRepository.save(fileToUpdate)
  }

  def UpdateFileCreationDate(file : File, date : Date) = {
    val fileToUpdate = fileRepository.getOne(file.id)
    fileToUpdate.created = date

    fileRepository.save(fileToUpdate)
  }

  def UpdateFileExtension(file : File, extension : String) = {
    val fileToUpdate = fileRepository.getOne(file.id)
    fileToUpdate.extension = extension

    fileRepository.save(fileToUpdate)
  }

  def UpdateFileLink(file : File, link : String) = {
    val fileToUpdate = fileRepository.getOne(file.id)
    fileToUpdate.link = link

    fileRepository.save(fileToUpdate)
  }

  def UpdateFileSize(file : File, size : Long) = {
    val fileToUpdate = fileRepository.getOne(file.id)
    fileToUpdate.size = size

    fileRepository.save(fileToUpdate)
  }

  def deleteFile(file : File) = fileRepository.delete(file)
}
