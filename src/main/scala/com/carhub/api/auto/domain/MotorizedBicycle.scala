package com.carhub.api.auto.domain

import com.carhub.api.auto.utils.jsonapi.annotations.JsonApi
import javax.persistence._

@Entity
@JsonApi(apiType= "motorized_bicycle")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("MOTORIZED_BICYCLE")
class MotorizedBicycle extends Vehicle {}