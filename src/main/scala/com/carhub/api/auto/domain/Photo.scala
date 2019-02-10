package com.carhub.api.auto.domain

import com.carhub.api.auto.utils.jsonapi.annotations.JsonApi
import javax.persistence._

@Entity
@Table(name = "photo")
@JsonApi(apiType = "photo")
class Photo extends File with Serializable {}
