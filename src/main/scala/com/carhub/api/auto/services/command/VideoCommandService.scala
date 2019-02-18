package com.carhub.api.auto.services.command

import java.util.Date

import com.carhub.api.auto.domain.Video
import com.carhub.api.auto.repositories.VideoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Autowired
@Transactional
@Service
class VideoCommandService(videoRepository : VideoRepository) {

  def addVideo(video : Video) = videoRepository.save(video)

  def UpdatePhoto(video: Video) = {
    val videoToUpdate = videoRepository.getOne(video.id)
    videoToUpdate.caption = video.caption
    videoToUpdate.content = video.content
    videoToUpdate.created = video.created
    videoToUpdate.extension = video.extension
    videoToUpdate.link = video.link
    videoToUpdate.size = video.size

    videoRepository.save(videoToUpdate)
  }

  def UpdateVideoCaption(video : Video, caption : String) = {
    val videoToUpdate = videoRepository.getOne(video.id)
    videoToUpdate.caption = caption

    videoRepository.save(videoToUpdate)
  }

  def UpdateVideoContent(video : Video, content : Array[Byte]) = {
    val videoToUpdate = videoRepository.getOne(video.id)
    videoToUpdate.content = content

    videoRepository.save(videoToUpdate)
  }

  def UpdateVideoCreationDate(video : Video, date : Date) = {
    val videoToUpdate = videoRepository.getOne(video.id)
    videoToUpdate.created = date

    videoRepository.save(videoToUpdate)
  }

  def UpdateVideoExtension(video : Video, extension : String) = {
    val videoToUpdate = videoRepository.getOne(video.id)
    videoToUpdate.extension = extension

    videoRepository.save(videoToUpdate)
  }

  def UpdateVideoLink(video : Video, link : String) = {
    val videoToUpdate = videoRepository.getOne(video.id)
    videoToUpdate.link = link

    videoRepository.save(videoToUpdate)
  }

  def UpdateVideoSize(video : Video, size : Long) = {
    val videoToUpdate = videoRepository.getOne(video.id)
    videoToUpdate.size = size

    videoRepository.save(videoToUpdate)
  }

  def deleteVideo(video: Video) = videoRepository.delete(video)
}
