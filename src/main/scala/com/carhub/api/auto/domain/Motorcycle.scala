package com.carhub.api.auto.domain

import javax.persistence._

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("MOTORCYCLE")
class Motorcycle extends Vehicle {

}
