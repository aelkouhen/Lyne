package com.carhub.api.auto.services.command

import java.util
import java.util.Date

import com.carhub.api.auto.domain.{Make, Model, Photo}
import com.carhub.api.auto.repositories.MakeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Autowired
@Service
class MakeCommandService(makeRepository : MakeRepository)  {

  def addMake(make : Make) = makeRepository.save(make)

  def UpdateMake (make : Make) = {
    val makeToUpdate = makeRepository.getOne(make.id)
    makeToUpdate.about = make.about
    makeToUpdate.foundationDate = make.foundationDate
    makeToUpdate.founder = make.founder
    makeToUpdate.headquarterLocation = make.headquarterLocation
    makeToUpdate.isClosed = make.isClosed
    makeToUpdate.logo = make.logo
    makeToUpdate.name = make.name
    makeToUpdate.oldName = make.oldName
    makeToUpdate.models.addAll(make.models)

    makeRepository.save(makeToUpdate)
  }

  def UpdateMakeDescription (make : Make, description : String) = {
    val makeToUpdate = makeRepository.getOne(make.id)
    makeToUpdate.about = description

    makeRepository.save(makeToUpdate)
  }

  def UpdateMakeFoundationDate (make : Make, foundationDate : Date) = {
    val makeToUpdate = makeRepository.getOne(make.id)
    makeToUpdate.foundationDate = foundationDate

    makeRepository.save(makeToUpdate)
  }

  def UpdateMakeFounder (make : Make, founder : String) = {
    val makeToUpdate = makeRepository.getOne(make.id)
    makeToUpdate.founder = founder

    makeRepository.save(makeToUpdate)
  }

  def UpdateMakeHQLocation (make : Make, headquarterLocation : String) = {
    val makeToUpdate = makeRepository.getOne(make.id)
    makeToUpdate.headquarterLocation = headquarterLocation

    makeRepository.save(makeToUpdate)
  }

  def UpdateMakeClosed (make : Make, isClosed : Boolean) = {
    val makeToUpdate = makeRepository.getOne(make.id)
    makeToUpdate.isClosed = isClosed

    makeRepository.save(makeToUpdate)
  }

  def UpdateMakeLogo (make : Make, logo : Photo) = {
    val makeToUpdate = makeRepository.getOne(make.id)
    makeToUpdate.logo = logo

    makeRepository.save(makeToUpdate)
  }

  def UpdateMakeName (make : Make, name : String) = {
    val makeToUpdate = makeRepository.getOne(make.id)
    makeToUpdate.name = name

    makeRepository.save(makeToUpdate)
  }

  def UpdateMakeOldName (make : Make, oldName : String) = {
    val makeToUpdate = makeRepository.getOne(make.id)
    makeToUpdate.oldName = oldName

    makeRepository.save(makeToUpdate)
  }

  def UpdateMakeModels (make : Make, models : util.List[Model]) = {
    val makeToUpdate = makeRepository.getOne(make.id)
    makeToUpdate.models.addAll(models)

    makeRepository.save(makeToUpdate)
  }

  def UpdateMakeAddModel (make : Make, model : Model) = {
    val makeToUpdate = makeRepository.getOne(make.id)
    makeToUpdate.models.add(model)

    makeRepository.save(makeToUpdate)
  }

  def deleteMake(make : Make) = makeRepository.delete(make)
}
