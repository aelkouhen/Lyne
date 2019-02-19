package com.carhub.api.auto.domain

import com.carhub.api.auto.utils.jsonapi.annotations.JsonApi
import javax.persistence._

@Entity
@Table(name = "video")
@JsonApi(apiType = "video")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("VIDEO")
class Video extends AbstractFile with Serializable {}