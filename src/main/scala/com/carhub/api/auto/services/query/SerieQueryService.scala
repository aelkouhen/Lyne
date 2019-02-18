package com.carhub.api.auto.services.query

import com.carhub.api.auto.domain.Serie
import com.carhub.api.auto.repositories.SerieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{PageRequest, Sort}
import org.springframework.stereotype.Service

@Autowired
@Service
class SerieQueryService(serieRepository : SerieRepository){

  def getSeriesPage(page : Int, size: Int, sortDirection : String, sort : String) =
    serieRepository.findAll(new PageRequest(page, size, new Sort(Sort.Direction.valueOf(sortDirection), sort)))

  def getSeriesList(page : Int, size: Int, sortDirection : String, sort : String) =
    getSeriesPage(page, size, sortDirection, sort).getContent

  def countAllSeries() = serieRepository.count

  def getSerieCars(serie : Serie) = serieRepository.getSerieCars(serie.id)

}
