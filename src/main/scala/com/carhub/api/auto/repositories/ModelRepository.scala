package com.carhub.api.auto.repositories

import java.lang.Long
import java.util

import com.carhub.api.auto.domain.{Car, Model, Serie}
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
trait ModelRepository extends JpaRepository[Model, Long] {

  @Query(value = "SELECT m.* FROM model m WHERE m.make_id = :id", nativeQuery=true)
  def getMakesModels(@Param("id") makeId : Long) : util.List[Model]
}
