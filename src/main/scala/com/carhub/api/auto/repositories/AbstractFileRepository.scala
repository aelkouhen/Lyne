package com.carhub.api.auto.repositories

import java.lang.Long
import java.util

import com.carhub.api.auto.domain.AbstractFile
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
trait AbstractFileRepository extends JpaRepository[AbstractFile, Long] {

  @Query(value = "SELECT f.* FROM abstract_file f where f.name like CONCAT('%',:name,'%')", nativeQuery=true)
  def findFileByName(@Param("name") name : String) : util.List[AbstractFile]

  @Query(value = "SELECT f.* FROM abstract_file f where f.extension like CONCAT('%',:ext,'%')", nativeQuery=true)
  def findFileByExtension(@Param("ext") ext : String) : util.List[AbstractFile]
}
