package com.carhub.api.gearheads.repositories

import java.util.UUID

import com.carhub.api.gearheads.entities.Gearhead
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
trait GearheadRepository extends CrudRepository[Gearhead, UUID] {}
