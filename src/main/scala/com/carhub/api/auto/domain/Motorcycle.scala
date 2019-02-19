package com.carhub.api.auto.domain

import com.carhub.api.auto.utils.jsonapi.annotations.JsonApi
import javax.persistence._

@Entity
@JsonApi(apiType= "motorcycle")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("MOTORCYCLE")
class Motorcycle extends Vehicle {}