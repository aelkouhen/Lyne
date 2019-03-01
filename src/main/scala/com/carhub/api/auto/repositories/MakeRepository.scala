package com.carhub.api.auto.repositories

import java.lang.Long
import java.util

import com.carhub.api.auto.domain.Make
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
trait MakeRepository extends JpaRepository[Make, Long] {

  @Query(value = "SELECT m.* FROM make m WHERE m.name like CONCAT('%',:name,'%')", nativeQuery=true)
  def findMakesByName(@Param("name") name : String) : util.List[Make]

  @Query(value = "SELECT m.* FROM make m WHERE m.id = :id", nativeQuery=true)
  def findMakeById(@Param("id") id : Long) : Make
}
