package com.carhub.api.auto.services.query

import com.carhub.api.auto.repositories.SerieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{PageRequest, Sort}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Autowired
@Transactional(readOnly = true)
@Service
class SerieQueryService(serieRepository : SerieRepository){

  def getSeriesPage(page : Int, size: Int, sortDirection : Sort.Direction, sort : String) =
    serieRepository.findAll(new PageRequest(page, size, sortDirection, sort))

  def getSeriesListAsc(page : Int, size: Int, sort : String) =
    getSeriesPage(page, size, Sort.Direction.ASC, sort).getContent

  def getSeriesListDesc(page : Int, size: Int, sort : String) =
    getSeriesPage(page, size, Sort.Direction.DESC, sort).getContent

  def countAllSeries() = serieRepository.count

  def findSeriesByName(name : String) = serieRepository.findSeriesByName(name)

  def getMakeSeries(makeId : Long) = serieRepository.getMakeSeries(makeId)

  def getModelSeries(modelId : Long) = serieRepository.getModelSeries(modelId)
}
