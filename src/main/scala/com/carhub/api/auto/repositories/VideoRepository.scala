package com.carhub.api.auto.repositories

import java.lang.Long
import java.util

import com.carhub.api.auto.domain.Video
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
trait VideoRepository extends JpaRepository[Video, Long] {

  @Query(value = "SELECT f.* FROM abstract_file f where f.file_type = 'VIDEO' AND f.name like CONCAT('%',:name,'%')", nativeQuery=true)
  def findVideosByName(@Param("name") name : String) : util.List[Video]

  @Query(value = "SELECT f.* FROM abstract_file f where f.file_type = 'VIDEO' AND f.extension like CONCAT('%',:ext,'%')", nativeQuery=true)
  def findVideosByExtension(@Param("ext") ext : String) : util.List[Video]

}
