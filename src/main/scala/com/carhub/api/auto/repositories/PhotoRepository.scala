package com.carhub.api.auto.repositories

import java.lang.Long

import com.carhub.api.auto.domain.Photo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
trait PhotoRepository extends JpaRepository[Photo, Long] {}
