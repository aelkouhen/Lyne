package com.carhub.api.auto.services.command

import java.awt.image.BufferedImage
import java.util.{Calendar, Date, UUID}

import com.carhub.api.auto.domain.dto.Photo
import com.carhub.api.auto.domain.{Make, Model}
import com.carhub.api.auto.repositories.MakeRepository
import com.carhub.api.auto.utils.jwt.JwtUtil
import com.google.common.io.Files
import com.google.common.net.HttpHeaders
import javax.imageio.ImageIO
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.cloud.client.ServiceInstance
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient

@Autowired
@Transactional
@Service
class MakeCommandService(makeRepository : MakeRepository,
                         modelCommandService: ModelCommandService)  {

  val mediaServiceInstance : ServiceInstance = null

  @Value("${security.oauth2.resource.token-type}")
  val tokenType : String = null

  @Value("${media.photo-endpoint}")
  val photoEndpoint : String = null

  def addMake(make : Make) = makeRepository.save(make)

  def updateMake (makeId : UUID, make : Make) = {
    val makeToUpdate = makeRepository.getOne(makeId)
    makeToUpdate.about = make.about
    makeToUpdate.foundationDate = make.foundationDate
    makeToUpdate.founder = make.founder
    makeToUpdate.headquarterLocation = make.headquarterLocation
    makeToUpdate.closed = make.closed
    makeToUpdate.logoId = make.logoId
    makeToUpdate.name = make.name
    makeToUpdate.oldName = make.oldName
    makeToUpdate.models.addAll(make.models)

    makeRepository.save(makeToUpdate)
  }

  def updateMakeDescription (makeId : UUID, description : String) = {
    val makeToUpdate = makeRepository.getOne(makeId)
    makeToUpdate.about = description

    makeRepository.save(makeToUpdate)
  }

  def updateMakeFoundationDate (makeId : UUID, foundationDate : Date) = {
    val makeToUpdate = makeRepository.getOne(makeId)
    makeToUpdate.foundationDate = foundationDate

    makeRepository.save(makeToUpdate)
  }

  def updateMakeFounder (makeId : UUID, founder : String) = {
    val makeToUpdate = makeRepository.getOne(makeId)
    makeToUpdate.founder = founder

    makeRepository.save(makeToUpdate)
  }

  def updateMakeHQLocation (makeId : UUID, headquarterLocation : String) = {
    val makeToUpdate = makeRepository.getOne(makeId)
    makeToUpdate.headquarterLocation = headquarterLocation

    makeRepository.save(makeToUpdate)
  }

  def updateMakeClosed (makeId : UUID, isClosed : Boolean) = {
    val makeToUpdate = makeRepository.getOne(makeId)
    makeToUpdate.closed = isClosed

    makeRepository.save(makeToUpdate)
  }


  def updateMakeLogo (makeId : UUID, logo : MultipartFile) = {
    val makeToUpdate = makeRepository.getOne(makeId)
    makeToUpdate.logoId = addPhoto(logo)

    makeRepository.save(makeToUpdate)
  }

  def updateMakeName (makeId : UUID, name : String) = {
    val makeToUpdate = makeRepository.getOne(makeId)
    makeToUpdate.name = name

    makeRepository.save(makeToUpdate)
  }

  def updateMakeOldName (makeId : UUID, oldName : String) = {
    val makeToUpdate = makeRepository.getOne(makeId)
    makeToUpdate.oldName = oldName

    makeRepository.save(makeToUpdate)
  }

  def updateMakeAddModel (makeId : UUID, model : Model) = {
    val modelCreated = modelCommandService.addModel(model)
    val makeToUpdate = makeRepository.getOne(makeId)
    modelCommandService.updateModelMake(modelCreated.id, makeToUpdate)
    makeToUpdate.models.add(modelCreated)

    makeRepository.save(makeToUpdate)
  }

  def deleteMake(makeId : UUID) = {
    val makeToDelete = makeRepository.getOne(makeId)
    makeRepository.delete(makeToDelete)
  }

  def addPhoto(photo: MultipartFile): UUID ={
    val photoMeta = new Photo
    photoMeta.size = photo.getSize
    photoMeta.name = Files.getNameWithoutExtension(photo.getOriginalFilename)
    photoMeta.format = Files.getFileExtension(photo.getOriginalFilename)
    photoMeta.content = photo.getBytes
    photoMeta.mimeType = photo.getContentType
    photoMeta.created = Calendar.getInstance().getTime()

    val bimg : BufferedImage = ImageIO.read(photo.getInputStream)
    photoMeta.width = bimg.getWidth
    photoMeta.height = bimg.getHeight

    val client = WebClient.builder()
      .baseUrl(mediaServiceInstance.getUri.toString)
      .defaultHeader(HttpHeaders.AUTHORIZATION, tokenType + " " + JwtUtil.token())
      .build()

    var request = client
      .method(HttpMethod.POST)
      .uri(photoEndpoint).body(BodyInserters.fromObject(photo))

    val p = request.retrieve()
      .bodyToMono(classOf[Photo])
      .block()

    p.id
  }
}
