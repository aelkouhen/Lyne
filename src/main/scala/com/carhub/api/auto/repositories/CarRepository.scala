package com.carhub.api.auto.repositories


import java.lang.Long

import com.carhub.api.auto.domain.Car
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
trait CarRepository extends JpaRepository[Car, Long] {

  @Query(value = "SELECT * FROM vehicle v where v.vehicle_type = 'CAR' AND v.name = :name", nativeQuery=true)
  def findByName(@Param("name") name : String) : Option[Car]
}
