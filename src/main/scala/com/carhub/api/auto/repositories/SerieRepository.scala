package com.carhub.api.auto.repositories

import java.util
import java.util.UUID

import com.carhub.api.auto.domain.Serie
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
trait SerieRepository extends JpaRepository[Serie, UUID] {

  @Query(value = "SELECT s.* FROM serie s WHERE s.name like CONCAT('%',:name,'%')", nativeQuery=true)
  def findSeriesByName(@Param("name") name : String) : util.List[Serie]
}
