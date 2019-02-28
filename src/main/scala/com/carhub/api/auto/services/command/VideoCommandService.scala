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

  def updateVideo(videoId : Long, video: Video) = {
    val videoToUpdate = videoRepository.getOne(videoId)
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

  def updateVideoCaption(videoId : Long, caption : String) = {
    val videoToUpdate = videoRepository.getOne(videoId)
    videoToUpdate.caption = caption

    videoRepository.save(videoToUpdate)
  }

  def updateVideoContent(videoId : Long, file : MultipartFile) = {
    val videoToUpdate = videoRepository.getOne(videoId)
    videoToUpdate.content = file.getBytes
    videoToUpdate.size = file.getSize
    videoToUpdate.name = Files.getNameWithoutExtension(file.getOriginalFilename)
    videoToUpdate.format = Files.getFileExtension(file.getOriginalFilename)
    val bimg : BufferedImage = ImageIO.read(file.getInputStream)
    videoToUpdate.width = bimg.getWidth
    videoToUpdate.height = bimg.getHeight

    videoRepository.save(videoToUpdate)
  }

  def updateVideoCreationDate(videoId : Long, date : Date) = {
    val videoToUpdate = videoRepository.getOne(videoId)
    videoToUpdate.created = date

    videoRepository.save(videoToUpdate)
  }

  def updateVideoExtension(videoId : Long, format : String) = {
    val videoToUpdate = videoRepository.getOne(videoId)
    videoToUpdate.format = format

    videoRepository.save(videoToUpdate)
  }

  def updateVideoLink(videoId : Long, url : String) = {
    val videoToUpdate = videoRepository.getOne(videoId)
    videoToUpdate.url = url

    videoRepository.save(videoToUpdate)
  }

  def deleteVideo(videoId : Long) = {
    val videoToDelete = videoRepository.getOne(videoId)
    videoRepository.delete(videoToDelete)
  }
}
