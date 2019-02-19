package com.carhub.api.auto.domain

import com.carhub.api.auto.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import javax.persistence._

@Entity
@Table(name = "file")
@JsonApi(apiType = "file")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("FILE")
class File extends AbstractFile with Serializable {}
