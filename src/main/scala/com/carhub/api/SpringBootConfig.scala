package com.carhub.api

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.context.annotation.{ComponentScan, Configuration}

@Configuration
@ComponentScan
@EnableAutoConfiguration(exclude = Array(classOf[HibernateJpaAutoConfiguration]))
class SpringBootConfig
