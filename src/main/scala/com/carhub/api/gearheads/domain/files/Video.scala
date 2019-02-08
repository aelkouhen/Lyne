package com.carhub.api.gearheads.domain.files

import com.carhub.api.utils.jsonapi.annotations.JsonApi
import javax.persistence._

@Entity
@Table(name = "video")
@JsonApi(apiType = "video")
class Video extends File with Serializable {}