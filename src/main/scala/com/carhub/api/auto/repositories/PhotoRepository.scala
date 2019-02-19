package com.carhub.api.auto.repositories

import java.lang.Long
import java.util

import com.carhub.api.auto.domain.Photo
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
trait PhotoRepository extends JpaRepository[Photo, Long] {

  @Query(value = "SELECT f.* FROM abstract_file f where f.file_type = 'PHOTO' AND f.name like CONCAT('%',:name,'%')", nativeQuery=true)
  def findPhotosByName(@Param("name") name : String) : util.List[Photo]

  @Query(value = "SELECT f.* FROM abstract_file f where f.file_type = 'PHOTO' AND f.extension like CONCAT('%',:ext,'%')", nativeQuery=true)
  def findPhotosByExtension(@Param("ext") ext : String) : util.List[Photo]

  @Query(value = "SELECT f.content FROM abstract_file f, make m where m.logo_id = f.id AND f.file_type = 'PHOTO' AND m.id like CONCAT('%',:id,'%')", nativeQuery=true)
  def getMakeIcon(@Param("id") id : Long) : Array[Byte]
}
