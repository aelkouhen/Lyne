package com.carhub.api.auto.repositories

import java.util
import java.util.UUID

import com.carhub.api.auto.domain.Car
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
trait CarRepository extends JpaRepository[Car, UUID] {

  @Query(value = "SELECT v.* FROM vehicle v where v.vehicle_type = 'CAR' AND v.name like CONCAT('%',:name,'%')", nativeQuery=true)
  def findCarsByName(@Param("name") name : String) : util.List[Car]
}
