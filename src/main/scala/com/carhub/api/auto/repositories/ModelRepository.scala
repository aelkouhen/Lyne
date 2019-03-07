package com.carhub.api.auto.repositories

import java.util
import java.util.UUID

import com.carhub.api.auto.domain.Model
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
trait ModelRepository extends JpaRepository[Model, UUID] {

  @Query(value = "SELECT m.* FROM model m WHERE m.name like CONCAT('%',:name,'%')", nativeQuery=true)
  def findModelsByName(@Param("name") name : String) : util.List[Model]
}
