package com.carhub.api.auto.repositories

import java.lang.Long

import com.carhub.api.auto.domain.Engine
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
trait EngineRepository extends JpaRepository[Engine, Long] {

  @Query(value = "SELECT * FROM engine e where e.name = :name", nativeQuery=true)
  def findByName(@Param("name") name : String) : Option[Engine]

  @Query(value = "SELECT * FROM engine e where e.fuel_type = :fuel", nativeQuery=true)
  def findByFuelType(@Param("fuel") fuel : String) : Option[Engine]
}
