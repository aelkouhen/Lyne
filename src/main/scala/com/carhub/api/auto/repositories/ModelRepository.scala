package com.carhub.api.auto.repositories

import java.lang.Long
import java.util

import com.carhub.api.auto.domain.Model
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
trait ModelRepository extends JpaRepository[Model, Long] {

  @Query(value = "SELECT m.* FROM model m WHERE m.make_id = :id", nativeQuery=true)
  def getMakeModels(@Param("id") makeId : Long) : util.List[Model]

  @Query(value = "SELECT m.* FROM model m WHERE m.name like CONCAT('%',:name,'%')", nativeQuery=true)
  def findModelsByName(@Param("name") name : String) : util.List[Model]
}
