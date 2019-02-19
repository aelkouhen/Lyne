package com.carhub.api.auto.domain

import com.carhub.api.auto.utils.jsonapi.annotations.JsonApi
import javax.persistence._

@Entity
@Table(name = "photo")
@JsonApi(apiType = "photo")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("PHOTO")
class Photo extends AbstractFile with Serializable {}
