package com.carhub.api.gearheads.repositories

import java.lang.Long

import com.carhub.api.gearheads.domain.activities.Relationship
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
trait RelationshipRepository extends JpaRepository[Relationship, Long] {}
