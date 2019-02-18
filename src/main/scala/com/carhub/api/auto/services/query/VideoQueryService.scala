package com.carhub.api.auto.services.query

import com.carhub.api.auto.repositories.VideoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{PageRequest, Sort}
import org.springframework.stereotype.Service

@Autowired
@Service
class VideoQueryService(videoRepository : VideoRepository) {

  def getVideosPage(page : Int, size: Int, sortDirection : String, sort : String) =
    videoRepository.findAll(new PageRequest(page, size, new Sort(Sort.Direction.valueOf(sortDirection), sort)))

  def getVideosList(page : Int, size: Int, sortDirection : String, sort : String) =
    getVideosPage(page, size, sortDirection, sort).getContent

  def countAllVideos() = videoRepository.count
}
