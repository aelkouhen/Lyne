package com.carhub.api.auto.repositories


import java.lang.Long
import java.util

import com.carhub.api.auto.domain.Car
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
trait CarRepository extends JpaRepository[Car, Long] {

  @Query(value = "SELECT v.* FROM vehicle v, model m, serie s WHERE v.vehicle_type = 'CAR' AND v.serie_id = s.id AND s.model_id = m.id AND m.make_id = :id", nativeQuery=true)
  def getMakesCars(@Param("id") makeId : Long) : util.List[Car]

  @Query(value = "SELECT v.* FROM vehicle v WHERE v.vehicle_type = 'CAR' AND v.serie_id = :id", nativeQuery=true)
  def getSerieCars(@Param("id") serieId : Long) : util.List[Car]

  @Query(value = "SELECT v.* FROM vehicle v, serie s WHERE v.vehicle_type = 'CAR' AND s.model_id = :id", nativeQuery=true)
  def getModelCars(@Param("id") modelId : Long) : util.List[Car]

  @Query(value = "SELECT v.* FROM vehicle v where v.vehicle_type = 'CAR' AND v.name like CONCAT('%',:name,'%')", nativeQuery=true)
  def findCarsByName(@Param("name") name : String) : util.List[Car]
}
