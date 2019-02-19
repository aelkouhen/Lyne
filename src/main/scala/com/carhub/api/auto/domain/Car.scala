package com.carhub.api.auto.domain

import com.carhub.api.auto.utils.jsonapi.annotations.JsonApi
import javax.persistence._

@Entity
@JsonApi(apiType= "car")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("CAR")
class Car extends Vehicle {}