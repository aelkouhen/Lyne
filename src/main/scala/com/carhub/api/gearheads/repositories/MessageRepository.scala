package com.carhub.api.gearheads.repositories

import java.lang.Long

import com.carhub.api.gearheads.domain.activities.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
trait MessageRepository extends JpaRepository[Message, Long] {}
