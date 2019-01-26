package com.carhub.api.gearheads.repositories

import java.lang.Long

import com.carhub.api.gearheads.model.activities.Choice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
trait ChoiceRepository extends JpaRepository[Choice, Long] {}
