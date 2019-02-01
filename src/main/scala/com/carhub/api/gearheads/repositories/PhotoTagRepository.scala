package com.carhub.api.gearheads.repositories

import java.lang.Long

import com.carhub.api.gearheads.domain.files.PhotoTag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
trait PhotoTagRepository extends JpaRepository[PhotoTag, Long] {}
