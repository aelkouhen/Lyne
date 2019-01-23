package com.carhub.api.gearheads.repositories

import java.lang.Long

import com.carhub.api.gearheads.entities.Workplace
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
trait WorkplaceRepository extends CrudRepository[Workplace, Long] {}
