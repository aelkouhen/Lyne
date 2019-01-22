package com.carhub.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@EnableWebSecurity
class SpringSecurityConfig{

  @Autowired
  def configureGlobal(auth: AuthenticationManagerBuilder) {
    auth
      .inMemoryAuthentication()
      .withUser("user").password("password").roles("USER")
  }

}
