package com.carhub.api.auto.services.query

import java.util
import java.util.UUID

import com.carhub.api.auto.domain.Serie
import com.carhub.api.auto.repositories.{MakeRepository, ModelRepository, SerieRepository}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{PageRequest, Sort}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Autowired
@Transactional(readOnly = true)
@Service
class SerieQueryService(serieRepository : SerieRepository,
                        makeRepository: MakeRepository,
                        modelRepository: ModelRepository){

  def getSeriesPage(page : Int, size: Int, sortDirection : Sort.Direction, sort : String) =
    serieRepository.findAll(PageRequest.of(page, size, sortDirection, sort))

  def getSeriesListAsc(page : Int, size: Int, sort : String) =
    getSeriesPage(page, size, Sort.Direction.ASC, sort).getContent

  def getSeriesListDesc(page : Int, size: Int, sort : String) =
    getSeriesPage(page, size, Sort.Direction.DESC, sort).getContent

  def countAllSeries() = serieRepository.count

  def findSeriesByName(name : String) = serieRepository.findSeriesByName(name)

  def findSerieById(serieId : UUID) = serieRepository.findById(serieId).get

  def getMakeSeries(makeId : UUID) = {
    var series = new util.ArrayList[Serie]()
    makeRepository.findById(makeId).get.models.forEach(m => series.addAll(m.series))
    series
  }

  def getModelSeries(modelId : UUID) = modelRepository.findById(modelId).get.series
}
