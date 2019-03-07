package com.carhub.api

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.{EnableWebMvc, ResourceHandlerRegistry, WebMvcConfigurer}

@Configuration
@EnableWebMvc
class WebConfig extends WebMvcConfigurer {
  override def addResourceHandlers(registry: ResourceHandlerRegistry): Unit = {
    registry.addResourceHandler("**/**").addResourceLocations("classpath:/META-INF/resources/")
  }
}

