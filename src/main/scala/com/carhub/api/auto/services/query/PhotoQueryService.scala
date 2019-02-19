package com.carhub.api.auto.services.query

import com.carhub.api.auto.repositories.PhotoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{PageRequest, Sort}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Autowired
@Transactional(readOnly = true)
@Service
class PhotoQueryService(photoRepository : PhotoRepository) {

  def getPhotosPage(page : Int, size: Int, sortDirection : Sort.Direction, sort : String) =
    photoRepository.findAll(new PageRequest(page, size, sortDirection, sort))

  def getPhotosListAsc(page : Int, size: Int, sort : String) =
    getPhotosPage(page, size, Sort.Direction.ASC, sort).getContent

  def getPhotosListDesc(page : Int, size: Int, sort : String) =
    getPhotosPage(page, size, Sort.Direction.DESC, sort).getContent

  def countAllPhotos() = photoRepository.count

  def findPhotosByName(name : String) = photoRepository.findPhotosByName(name)

  def findPhotosByExtension(ext : String) = photoRepository.findPhotosByExtension(ext)

  def getMakeIcon(makeId : Long) = photoRepository.getMakeIcon(makeId)
}
