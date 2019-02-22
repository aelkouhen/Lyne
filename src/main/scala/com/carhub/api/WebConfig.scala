package com.carhub.api

import java.nio.charset.Charset
import java.util

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.http.converter.{HttpMessageConverter, StringHttpMessageConverter}
import org.springframework.web.servlet.config.annotation.{EnableWebMvc, WebMvcConfigurationSupport}

@Configuration
@EnableWebMvc
class WebConfig extends WebMvcConfigurationSupport{

  @Bean
  def responseBodyConverter(): StringHttpMessageConverter = {
    val converter = new StringHttpMessageConverter
    converter.setSupportedMediaTypes(util.Arrays.asList(new MediaType("text", "plain", Charset.forName("UTF-8"))))
    converter
  }

  @Bean
  def customJackson2HttpMessageConverter() : MappingJackson2HttpMessageConverter = {
    val jsonConverter = new MappingJackson2HttpMessageConverter
    val objectMapper = new ObjectMapper
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    jsonConverter.setObjectMapper(objectMapper)
    jsonConverter
  }

  override def configureMessageConverters(converters: util.List[HttpMessageConverter[_]]): Unit = {
    converters.add(responseBodyConverter)
    converters.add(customJackson2HttpMessageConverter)
    super.configureMessageConverters(converters)
    super.addDefaultHttpMessageConverters(converters)
  }
}
