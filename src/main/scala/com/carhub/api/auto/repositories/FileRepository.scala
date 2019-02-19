package com.carhub.api.auto.repositories

import java.lang.Long
import java.util

import com.carhub.api.auto.domain.File
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
trait FileRepository extends JpaRepository[File, Long] {

  @Query(value = "SELECT f.* FROM abstract_file f where f.file_type = 'FILE' AND f.name like CONCAT('%',:name,'%')", nativeQuery=true)
  def findFilesByName(@Param("name") name : String) : util.List[File]

  @Query(value = "SELECT f.* FROM abstract_file f where f.file_type = 'FILE' AND f.extension like CONCAT('%',:ext,'%')", nativeQuery=true)
  def findFilesByExtension(@Param("ext") ext : String) : util.List[File]
}
