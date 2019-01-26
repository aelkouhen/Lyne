package com.carhub.api.gearheads.repositories

import java.lang.Long

import com.carhub.api.gearheads.model.activities.Thread
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
trait ThreadRepository extends JpaRepository[Thread, Long] {}
