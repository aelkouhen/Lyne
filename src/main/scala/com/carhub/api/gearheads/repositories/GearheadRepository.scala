package com.carhub.api.gearheads.repositories

import java.lang.Long

import com.carhub.api.gearheads.model.Gearhead
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
trait GearheadRepository extends CrudRepository[Gearhead, Long] {}
