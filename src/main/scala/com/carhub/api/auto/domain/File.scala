package com.carhub.api.auto.domain

import javax.persistence._

@Entity
@Table(name = "file")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("FILE")
class File extends Resource with Serializable {}
