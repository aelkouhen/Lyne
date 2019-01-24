package com.carhub.api.gearheads.repositories

import java.lang.Long

import com.carhub.api.gearheads.model.locations.Location
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
trait LocationRepository extends CrudRepository[Location, Long] {}
