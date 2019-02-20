package com.carhub.api.auto.repositories

import java.lang.Long
import java.util

import com.carhub.api.auto.domain.Photo
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
trait PhotoRepository extends JpaRepository[Photo, Long] {

  @Query(value = "SELECT r.* FROM resource r where r.resource_type = 'PHOTO' AND r.name like CONCAT('%',:name,'%')", nativeQuery=true)
  def findPhotosByName(@Param("name") name : String) : util.List[Photo]

  @Query(value = "SELECT r.* FROM resource r where r.resource_type = 'PHOTO' AND r.format like CONCAT('%',:ext,'%')", nativeQuery=true)
  def findPhotosByExtension(@Param("ext") ext : String) : util.List[Photo]

  @Query(value = "SELECT r.content FROM resource r, make m where m.logo_id = r.id AND m.id like CONCAT('%',:id,'%')", nativeQuery=true)
  def getMakeIcon(@Param("id") id : Long) : Array[Byte]
}
