package com.carhub.api.auto.services.query

import com.carhub.api.auto.repositories.VideoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{PageRequest, Sort}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Autowired
@Transactional(readOnly = true)
@Service
class VideoQueryService(videoRepository : VideoRepository) {

  def getVideosPage(page : Int, size: Int, sortDirection : Sort.Direction, sort : String) =
    videoRepository.findAll(new PageRequest(page, size, sortDirection, sort))

  def getVideosListAsc(page : Int, size: Int, sort : String) =
    getVideosPage(page, size, Sort.Direction.ASC, sort).getContent

  def getVideosListDesc(page : Int, size: Int, sort : String) =
    getVideosPage(page, size, Sort.Direction.DESC, sort).getContent

  def countAllVideos() = videoRepository.count

  def findVideoById(videoId : Long) = videoRepository.findVideoById(videoId)

  def findVideosByName(name : String) = videoRepository.findVideosByName(name)

  def findVideosByExtension(format : String) = videoRepository.findVideosByExtension(format)
}
