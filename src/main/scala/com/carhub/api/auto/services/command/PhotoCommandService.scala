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

  def UpdatePhoto(photo : Photo) = {
    val photoToUpdate = photoRepository.getOne(photo.id)
    photoToUpdate.caption = photo.caption
    photoToUpdate.content = photo.content
    photoToUpdate.created = photo.created
    photoToUpdate.format = photo.format
    photoToUpdate.url = photo.url
    photoToUpdate.size = photo.size
    photoToUpdate.height = photo.height
    photoToUpdate.width = photo.width

    photoRepository.save(photoToUpdate)
  }

  def UpdatePhotoCaption(photo : Photo, caption : String) = {
    val photoToUpdate = photoRepository.getOne(photo.id)
    photoToUpdate.caption = caption

    photoRepository.save(photoToUpdate)
  }

  def UpdatePhotoContent(photo : Photo, content : Array[Byte]) = {
    val photoToUpdate = photoRepository.getOne(photo.id)
    photoToUpdate.content = content

    photoRepository.save(photoToUpdate)
  }

  def UpdatePhotoCreationDate(photo : Photo, date : Date) = {
    val photoToUpdate = photoRepository.getOne(photo.id)
    photoToUpdate.created = date

    photoRepository.save(photoToUpdate)
  }

  def UpdatePhotoExtension(photo : Photo, format : String) = {
    val photoToUpdate = photoRepository.getOne(photo.id)
    photoToUpdate.format = format

    photoRepository.save(photoToUpdate)
  }

  def UpdatePhotoLink(photo : Photo, url : String) = {
    val photoToUpdate = photoRepository.getOne(photo.id)
    photoToUpdate.url = url

    photoRepository.save(photoToUpdate)
  }

  def deletePhoto(photo : Photo) = photoRepository.delete(photo)
}
