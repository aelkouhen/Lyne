package com.carhub.api

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.{ComponentScan, Configuration}
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = Array("com.carhub.api.auto.repositories"))
class SpringBootConfig
