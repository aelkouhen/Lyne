package com.carhub.api.auto.repositories

import java.lang.Long
import java.util

import com.carhub.api.auto.domain.{Car, Make, Model, Serie}
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
trait MakeRepository extends JpaRepository[Make, Long] {

  @Query(value = "SELECT m.* FROM model m WHERE m.make_id = :id", nativeQuery=true)
  def getMakesModels(@Param("id") makeId : scala.Long) : util.List[Model]

  @Query(value = "SELECT s.* FROM serie s join model m WHERE s.model_id = m.id AND m.make_id = :id", nativeQuery=true)
  def getMakesSeries(@Param("id") makeId : scala.Long) : util.List[Serie]

  @Query(value = "SELECT v.* FROM vehicle v, model m, serie s WHERE v.vehicle_type = 'CAR' AND v.serie_id = s.id AND s.model_id = m.id AND m.make_id = :id", nativeQuery=true)
  def getMakesCars(@Param("id") makeId : scala.Long) : util.List[Car]

  @Query(value = "SELECT m.* FROM make m WHERE m.name like CONCAT('%',:name,'%')", nativeQuery=true)
  def findMakesByName(@Param("name") name : String) : util.List[Make]
}
