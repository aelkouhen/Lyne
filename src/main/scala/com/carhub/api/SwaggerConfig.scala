package com.carhub.api

import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import com.google.common.collect.ImmutableList
import org.springframework.context.annotation.Bean
import org.springframework.core.annotation.Order
import springfox.documentation.service.ApiDescription
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.ApiListingBuilderPlugin
import springfox.documentation.spi.service.contexts.ApiListingContext
import springfox.documentation.swagger.common.SwaggerPluginSupport


@Configuration
@EnableSwagger2
class SwaggerConfig {

  @Bean
  def api: Docket = new Docket(DocumentationType.SWAGGER_2)
                      .select
                      .apis(RequestHandlerSelectors.basePackage("com.carhub.api.auto.controllers"))
                      .paths(PathSelectors.any)
                      .build


  @Bean
  @Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
  def getApiPathEnrichPlugin: ApiListingBuilderPlugin = new ApiListingBuilderPlugin() {
    override def supports(delimiter: DocumentationType): Boolean = true

    override def apply(apiListingContext: ApiListingContext): Unit = {
      var apis = apiListingContext.apiListingBuilder.build.getApis
      val builder : ImmutableList.Builder[ApiDescription] = ImmutableList.builder[ApiDescription]()
      if (apis != null) {
        apis.forEach(api => builder.add(new ApiDescription(api.getGroupName.get, api.getPath + "?apiDescription=" + api.getDescription, api.getDescription, api.getOperations, api.isHidden)))
        apis = builder.build
        apiListingContext.apiListingBuilder.apis(apis)
      }
    }
  }
}