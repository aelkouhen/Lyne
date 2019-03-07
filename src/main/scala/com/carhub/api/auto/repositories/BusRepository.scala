package com.carhub.api.auto.repositories

import java.util.UUID

import com.carhub.api.auto.domain.Bus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
trait BusRepository extends JpaRepository[Bus, UUID] {}
