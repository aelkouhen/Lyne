package com.carhub.api.auto.repositories

import java.lang.Long
import java.util

import com.carhub.api.auto.domain.File
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
trait FileRepository extends JpaRepository[File, Long] {

  @Query(value = "SELECT r.* FROM resource r where r.resource_type = 'FILE' AND r.id = :id", nativeQuery=true)
  def findFileById(@Param("id") id : Long) : File

  @Query(value = "SELECT r.* FROM resource r where r.resource_type = 'FILE' AND r.name like CONCAT('%',:name,'%')", nativeQuery=true)
  def findFilesByName(@Param("name") name : String) : util.List[File]

  @Query(value = "SELECT r.* FROM resource r where r.resource_type = 'FILE' AND r.format like CONCAT('%',:format,'%')", nativeQuery=true)
  def findFilesByExtension(@Param("format") format : String) : util.List[File]
}
