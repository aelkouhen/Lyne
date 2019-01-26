package com.carhub.api.gearheads.repositories

import java.lang.Long

import com.carhub.api.gearheads.model.files.PhotoAlbum
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
trait PhotoAlbumRepository extends JpaRepository[PhotoAlbum, Long] {}
