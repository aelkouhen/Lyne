package com.carhub.api.auto.services.command

import java.util.Date

import com.carhub.api.auto.domain.Photo
import com.carhub.api.auto.repositories.PhotoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Autowired
@Service
class PhotoCommandService(photoRepository : PhotoRepository){

  def addPhoto(photo : Photo) = photoRepository.save(photo)

  def UpdatePhoto(photo : Photo) = {
    val photoToUpdate = photoRepository.getOne(photo.id)
    photoToUpdate.caption = photo.caption
    photoToUpdate.content = photo.content
    photoToUpdate.created = photo.created
    photoToUpdate.extension = photo.extension
    photoToUpdate.link = photo.link
    photoToUpdate.size = photo.size

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

  def UpdatePhotoExtension(photo : Photo, extension : String) = {
    val photoToUpdate = photoRepository.getOne(photo.id)
    photoToUpdate.extension = extension

    photoRepository.save(photoToUpdate)
  }

  def UpdatePhotoLink(photo : Photo, link : String) = {
    val photoToUpdate = photoRepository.getOne(photo.id)
    photoToUpdate.link = link

    photoRepository.save(photoToUpdate)
  }

  def UpdatePhotoSize(photo : Photo, size : Long) = {
    val photoToUpdate = photoRepository.getOne(photo.id)
    photoToUpdate.size = size

    photoRepository.save(photoToUpdate)
  }

  def deletePhoto(photo : Photo) = photoRepository.delete(photo)
}
