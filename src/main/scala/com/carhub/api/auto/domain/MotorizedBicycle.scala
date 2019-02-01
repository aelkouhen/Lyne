package com.carhub.api.auto.domain

import javax.persistence._

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("MOTORIZED_BICYCLE")
class MotorizedBicycle extends Vehicle {

}
