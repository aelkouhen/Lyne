package com.carhub.api

import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.{ApiInfoBuilder, PathSelectors, RequestHandlerSelectors}
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import com.google.common.collect.ImmutableList
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.core.annotation.Order
import springfox.documentation.service.{ApiDescription, ApiInfo}
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.ApiListingBuilderPlugin
import springfox.documentation.spi.service.contexts.ApiListingContext
import springfox.documentation.swagger.common.SwaggerPluginSupport


@Configuration
@EnableSwagger2
class SwaggerConfig {

  @Value("${swagger.api.version}")
  private val apiVersion: String = None.orNull

  @Bean
  def api: Docket = new Docket(DocumentationType.SWAGGER_2)
                      .select
                      .apis(RequestHandlerSelectors.basePackage("com.carhub.api.auto.controllers"))
                      .paths(PathSelectors.any)
                      .build
                      .apiInfo(getInfos)

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

  private def getInfos: ApiInfo = {
    new ApiInfoBuilder().
      title("Lyne API").
      description(swaggerAPIDescription).
      version(apiVersion).build()
  }

  private val swaggerAPIDescription =
    s"""
       |Lyne Api allow you to create / access to the Automotive concepts.
       |It represents various type of vehicles like (Cars, Motorcycles, Buses and so on ...).
       |
       |
       |They are represented with the following concepts:
       |  - Make: refers to the vehicle's manufacturer.
       |  - Model: the name of the product.
       |  - Serie: the sub-model.
       |  - Engine: information about the engine of the vehicle.
       |  - Car.
       |  - Motorcycle.
       |  - MotorizedBicycle.
       |
       |
       |There is also some util. entities to add some information.
       |  - Resource : Type of an attached resource (could be a file, a photo or a video).
       |  - File : Type of an attached file.
       |  - Photo : Type of an attached photo.
       |  - Video : Type of an attached video.
       |
       |
       |The Api follows the CQRS (Command-Query Responsibility Segregation) patterns and thus is composed of 2 parts :
       |  - Commands: Create, Update, Delete the main concepts.
       |  - Queries: to retrieve the concepts data.
       |
       |
       |How to use the API :
       |  - Run the Eureka discovery server.
       |  - Get an OAuth2 Access Token with Authentication Service (https://github.com/aelkouhen/Bibi)
       |  - Calling the API with the paths bellow:
     """.stripMargin
}