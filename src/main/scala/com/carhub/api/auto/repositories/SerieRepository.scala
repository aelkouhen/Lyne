package com.carhub.api.auto.repositories

import java.lang.Long
import java.util

import com.carhub.api.auto.domain.Serie
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
trait SerieRepository extends JpaRepository[Serie, Long] {

  @Query(value = "SELECT s.* FROM serie s join model m WHERE s.model_id = m.id AND m.make_id = :id", nativeQuery=true)
  def getMakeSeries(@Param("id") makeId : Long) : util.List[Serie]

  @Query(value = "SELECT s.* FROM serie s WHERE s.model_id = :id", nativeQuery=true)
  def getModelSeries(@Param("id") modelId : Long) : util.List[Serie]

  @Query(value = "SELECT s.* FROM serie s WHERE s.name like CONCAT('%',:name,'%')", nativeQuery=true)
  def findSeriesByName(@Param("name") name : String) : util.List[Serie]
}
