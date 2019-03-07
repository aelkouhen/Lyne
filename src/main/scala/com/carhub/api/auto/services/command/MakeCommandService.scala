package com.carhub.api.auto.services.command

import java.util.{Date, UUID}

import com.carhub.api.auto.domain.{Make, Model}
import com.carhub.api.auto.repositories.MakeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Autowired
@Transactional
@Service
class MakeCommandService(makeRepository : MakeRepository,
                         modelCommandService: ModelCommandService,
                         photoCommandService: PhotoCommandService)  {

  def addMake(make : Make) = makeRepository.save(make)

  def updateMake (makeId : UUID, make : Make) = {
    val makeToUpdate = makeRepository.getOne(makeId)
    makeToUpdate.about = make.about
    makeToUpdate.foundationDate = make.foundationDate
    makeToUpdate.founder = make.founder
    makeToUpdate.headquarterLocation = make.headquarterLocation
    makeToUpdate.closed = make.closed
    makeToUpdate.logo = make.logo
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
    val logoCreated = photoCommandService.addPhoto(logo)
    val makeToUpdate = makeRepository.getOne(makeId)
    photoCommandService.updatePhotoCaption(logoCreated.id, "The " + makeToUpdate.name + "'s logo")
    makeToUpdate.logo = logoCreated

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
}
