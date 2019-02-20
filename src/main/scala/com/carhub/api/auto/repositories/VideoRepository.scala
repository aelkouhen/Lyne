package com.carhub.api.auto.repositories

import java.lang.Long
import java.util

import com.carhub.api.auto.domain.Video
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
trait VideoRepository extends JpaRepository[Video, Long] {

  @Query(value = "SELECT r.* FROM resource r where r.resource_type = 'VIDEO' AND r.name like CONCAT('%',:name,'%')", nativeQuery=true)
  def findVideosByName(@Param("name") name : String) : util.List[Video]

  @Query(value = "SELECT r.* FROM resource r where r.resource_type = 'VIDEO' AND r.format like CONCAT('%',:ext,'%')", nativeQuery=true)
  def findVideosByExtension(@Param("ext") ext : String) : util.List[Video]
}
