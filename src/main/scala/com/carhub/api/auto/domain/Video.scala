package com.carhub.api.auto.domain

import com.carhub.api.auto.utils.jsonapi.annotations.JsonApi
import javax.persistence._

@Entity
@Table(name = "video")
@JsonApi(apiType = "video")
class Video extends File with Serializable {}