package com.carhub.api

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{ComponentScan, Configuration}
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = Array("com.carhub.api.gearheads.repositories", "com.carhub.api.auto.repositories"))
class SpringBootConfig
