package com.carhub.api.auto.repositories

import java.lang.Long
import java.util

import com.carhub.api.auto.domain.{Car, Serie}
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
trait SerieRepository extends JpaRepository[Serie, Long] {

  @Query(value = "SELECT v.* FROM vehicle v WHERE v.vehicle_type = 'CAR' AND v.serie_id = :id", nativeQuery=true)
  def getSerieCars(@Param("id") serieId : Long) : util.List[Car]

}
