package com.carhub.api.auto.repositories

import java.lang.Long
import java.util

import com.carhub.api.auto.domain.Engine
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
trait EngineRepository extends JpaRepository[Engine, Long] {

  @Query(value = "SELECT e.* FROM engine e where e.name like CONCAT('%',:name,'%')", nativeQuery=true)
  def findByName(@Param("name") name : String) : util.List[Engine]

  @Query(value = "SELECT e.* FROM engine e where e.fuel_type like CONCAT('%',:fuel,'%')", nativeQuery=true)
  def findByFuelType(@Param("fuel") fuel : String) : util.List[Engine]
}
