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

  def UpdateFile(file : File) = {
    val fileToUpdate = fileRepository.getOne(file.id)
    fileToUpdate.caption = file.caption
    fileToUpdate.content = file.content
    fileToUpdate.created = file.created
    fileToUpdate.format = file.format
    fileToUpdate.url = file.url
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

  def UpdateFileExtension(file : File, format : String) = {
    val fileToUpdate = fileRepository.getOne(file.id)
    fileToUpdate.format = format

    fileRepository.save(fileToUpdate)
  }

  def UpdateFileLink(file : File, url : String) = {
    val fileToUpdate = fileRepository.getOne(file.id)
    fileToUpdate.url = url

    fileRepository.save(fileToUpdate)
  }

  def UpdateFileSize(file : File, size : Long) = {
    val fileToUpdate = fileRepository.getOne(file.id)
    fileToUpdate.size = size

    fileRepository.save(fileToUpdate)
  }

  def deleteFile(file : File) = fileRepository.delete(file)
}
