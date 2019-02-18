package com.carhub.api.auto.repositories

import java.lang.Long
import java.util

import com.carhub.api.auto.domain.{Car, Make, Model, Serie}
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
trait MakeRepository extends JpaRepository[Make, Long] {

  @Query(value = "SELECT * FROM model m WHERE m.make_id = :id", nativeQuery=true)
  def getMakesModels(@Param("id") makeId : Long) : util.List[Model]

  @Query(value = "SELECT s.* FROM serie s, model m WHERE s.model_id = m.id AND m.make_id = :id", nativeQuery=true)
  def getMakesSeries(@Param("id") makeId : Long) : util.List[Serie]

  @Query(value = "SELECT v.* FROM vehicle v, model m, serie s WHERE v.vehicle_type = 'CAR' AND v.serie_id = s.id AND s.model_id = m.id AND m.make_id = :id", nativeQuery=true)
  def getMakesCars(@Param("id") makeId : Long) : util.List[Car]
}
