package com.carhub.api.auto.repositories

import java.lang.Long
import java.util

import com.carhub.api.auto.domain.{Car, Model, Serie}
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
trait ModelRepository extends JpaRepository[Model, Long] {

  @Query(value = "SELECT s.* FROM serie s WHERE s.model_id = :id", nativeQuery=true)
  def getModelSeries(@Param("id") modelId : Long) : util.List[Serie]

  @Query(value = "SELECT v.* FROM vehicle v, serie s WHERE v.vehicle_type = 'CAR' AND s.model_id = :id", nativeQuery=true)
  def getModelCars(@Param("id") modelId : Long) : util.List[Car]
}
