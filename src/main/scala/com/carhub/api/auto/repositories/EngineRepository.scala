package com.carhub.api.auto.repositories

import java.util
import java.util.UUID

import com.carhub.api.auto.domain.Engine
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
trait EngineRepository extends JpaRepository[Engine, UUID] {

  @Query(value = "SELECT e.* FROM engine e where e.name like CONCAT('%',:name,'%')", nativeQuery=true)
  def findEnginesByName(@Param("name") name : String) : util.List[Engine]

  @Query(value = "SELECT e.* FROM engine e where e.fuel like CONCAT('%',:fuel,'%')", nativeQuery=true)
  def findEnginesByFuelType(@Param("fuel") fuel : String) : util.List[Engine]
}
