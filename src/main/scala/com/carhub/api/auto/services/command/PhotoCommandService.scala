package com.carhub.api.auto.services.command

import java.awt.image.BufferedImage
import java.util.{Calendar, Date}

import com.carhub.api.auto.domain.Photo
import com.carhub.api.auto.repositories.PhotoRepository
import com.google.common.io.Files
import javax.imageio.ImageIO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Autowired
@Transactional
@Service
class PhotoCommandService(photoRepository : PhotoRepository){

  def addPhoto(photo : MultipartFile) : Photo = {
    val photoMeta = new Photo
    photoMeta.size = photo.getSize
    photoMeta.name = Files.getNameWithoutExtension(photo.getOriginalFilename)
    photoMeta.format = Files.getFileExtension(photo.getOriginalFilename)
    photoMeta.content = photo.getBytes
    photoMeta.created = Calendar.getInstance().getTime()

    val bimg : BufferedImage = ImageIO.read(photo.getInputStream)
    photoMeta.width = bimg.getWidth
    photoMeta.height = bimg.getHeight

    addPhoto(photoMeta)
  }

  def addPhoto(photo : Photo) = photoRepository.save(photo)

  def updatePhoto(photoId : Long, photo : Photo) = {
    val photoToUpdate = photoRepository.getOne(photoId)
    photoToUpdate.caption = photo.caption
    photoToUpdate.name = photo.name
    photoToUpdate.content = photo.content
    photoToUpdate.created = photo.created
    photoToUpdate.format = photo.format
    photoToUpdate.url = photo.url
    photoToUpdate.size = photo.size
    photoToUpdate.height = photo.height
    photoToUpdate.width = photo.width

    photoRepository.save(photoToUpdate)
  }

  def updatePhotoCaption(photoId : Long, caption : String) = {
    val photoToUpdate = photoRepository.getOne(photoId)
    photoToUpdate.caption = caption

    photoRepository.save(photoToUpdate)
  }

  def updatePhotoContent(photoId : Long, file : MultipartFile) = {
    val photoToUpdate = photoRepository.getOne(photoId)
    photoToUpdate.content = file.getBytes
    photoToUpdate.size = file.getSize
    photoToUpdate.name = Files.getNameWithoutExtension(file.getOriginalFilename)
    photoToUpdate.format = Files.getFileExtension(file.getOriginalFilename)
    val bimg : BufferedImage = ImageIO.read(file.getInputStream)
    photoToUpdate.width = bimg.getWidth
    photoToUpdate.height = bimg.getHeight

    photoRepository.save(photoToUpdate)
  }

  def updatePhotoCreationDate(photoId : Long, date : Date) = {
    val photoToUpdate = photoRepository.getOne(photoId)
    photoToUpdate.created = date

    photoRepository.save(photoToUpdate)
  }

  def updatePhotoExtension(photoId : Long, format : String) = {
    val photoToUpdate = photoRepository.getOne(photoId)
    photoToUpdate.format = format

    photoRepository.save(photoToUpdate)
  }

  def updatePhotoLink(photoId : Long, url : String) = {
    val photoToUpdate = photoRepository.getOne(photoId)
    photoToUpdate.url = url

    photoRepository.save(photoToUpdate)
  }

  def deletePhoto(photoId : Long) = {
    val photoToDelete = photoRepository.getOne(photoId)
    photoRepository.delete(photoToDelete)
  }
}
