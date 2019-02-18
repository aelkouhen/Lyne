package com.carhub.api.auto.services.query

import com.carhub.api.auto.repositories.PhotoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{PageRequest, Sort}
import org.springframework.stereotype.Service

@Autowired
@Service
class PhotoQueryService(photoRepository : PhotoRepository) {

  def getPhotosPage(page : Int, size: Int, sortDirection : String, sort : String) =
    photoRepository.findAll(new PageRequest(page, size, new Sort(Sort.Direction.valueOf(sortDirection), sort)))

  def getPhotosList(page : Int, size: Int, sortDirection : String, sort : String) =
    getPhotosPage(page, size, sortDirection, sort).getContent

  def countAllPhotos() = photoRepository.count
}
