package com.carhub.api.auto.domain

import com.carhub.api.auto.utils.jsonapi.annotations.JsonApi
import javax.persistence._

@Entity
@JsonApi(apiType= "bus")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("BUS")
class Bus extends Vehicle {}