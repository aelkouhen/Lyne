package com.carhub.api

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.{EnableWebMvc, ResourceHandlerRegistry, WebMvcConfigurerAdapter}

@Configuration
@EnableWebMvc
class WebConfig extends WebMvcConfigurerAdapter {
  override def addResourceHandlers(registry: ResourceHandlerRegistry): Unit = {
    registry.addResourceHandler("**/**").addResourceLocations("classpath:/META-INF/resources/")
  }
}

