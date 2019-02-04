package com.carhub.api.auto.repositories

import java.lang.Long

import com.carhub.api.auto.domain.Motorcycle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
trait MotorcycleRepository extends JpaRepository[Motorcycle, Long] {}
