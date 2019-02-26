package com.carhub.api.auto.services.command

import java.awt.image.BufferedImage
import java.util.{Calendar, Date}

import com.carhub.api.auto.domain.Video
import com.carhub.api.auto.repositories.VideoRepository
import com.google.common.io.Files
import javax.imageio.ImageIO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Autowired
@Transactional
@Service
class VideoCommandService(videoRepository : VideoRepository) {

  def addVideo(video : MultipartFile) : Video = {
    val VideoMeta = new Video
    VideoMeta.size = video.getSize
    VideoMeta.name = Files.getNameWithoutExtension(video.getOriginalFilename)
    VideoMeta.format = Files.getFileExtension(video.getOriginalFilename)
    VideoMeta.content = video.getBytes
    VideoMeta.created = Calendar.getInstance().getTime()

    val bimg : BufferedImage = ImageIO.read(video.getInputStream)
    VideoMeta.width = bimg.getWidth
    VideoMeta.height = bimg.getHeight

    addVideo(VideoMeta)
  }

  def addVideo(video : Video) = videoRepository.save(video)

  def UpdatePhoto(video: Video) = {
    val videoToUpdate = videoRepository.getOne(video.id)
    videoToUpdate.caption = video.caption
    videoToUpdate.content = video.content
    videoToUpdate.created = video.created
    videoToUpdate.format = video.format
    videoToUpdate.url = video.url
    videoToUpdate.size = video.size
    videoToUpdate.height = video.height
    videoToUpdate.width = video.width

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

  def UpdateVideoExtension(video : Video, format : String) = {
    val videoToUpdate = videoRepository.getOne(video.id)
    videoToUpdate.format = format

    videoRepository.save(videoToUpdate)
  }

  def UpdateVideoLink(video : Video, url : String) = {
    val videoToUpdate = videoRepository.getOne(video.id)
    videoToUpdate.url = url

    videoRepository.save(videoToUpdate)
  }

  def deleteVideo(video: Video) = videoRepository.delete(video)
}
