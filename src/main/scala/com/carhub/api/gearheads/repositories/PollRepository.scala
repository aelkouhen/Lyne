package com.carhub.api.gearheads.repositories

import java.lang.Long

import com.carhub.api.gearheads.model.activities.Poll
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
trait PollRepository extends JpaRepository[Poll, Long] {}
