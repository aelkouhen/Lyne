package com.carhub.api.auto.repositories

import java.lang.Long
import java.util

import com.carhub.api.auto.domain.Resource
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
trait ResourceRepository extends JpaRepository[Resource, Long] {

  @Query(value = "SELECT r.* FROM resource r where r.name like CONCAT('%',:name,'%')", nativeQuery=true)
  def findResourcesByName(@Param("name") name : String) : util.List[Resource]

  @Query(value = "SELECT r.* FROM resource r where r.format like CONCAT('%',:format,'%')", nativeQuery=true)
  def findResourcesByExtension(@Param("format") format : String) : util.List[Resource]
}
