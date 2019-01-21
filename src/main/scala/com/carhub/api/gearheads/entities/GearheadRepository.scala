package com.carhub.api.gearheads.entities

import java.lang.Long

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
trait GearheadRepository extends CrudRepository[Gearhead, Long] {}
