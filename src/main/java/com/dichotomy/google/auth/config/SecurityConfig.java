package com.dichotomy.google.auth.config;
// Add the necessary dependencies in your pom.xml file
// (e.g., spring-boot-starter-security, spring-boot-starter-oauth2-client)

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
   @Bean
   public DefaultSecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
   http
           // Configure form login and OAuth2 login

           // Configure authorization rules
           .authorizeHttpRequests(authorizeRequests ->
                   authorizeRequests
                           .requestMatchers("/login/**").permitAll()
                           // Allow access to the login page and the OAuth2 callback endpoint
                           // Require authentication for other endpoints
                           .anyRequest().authenticated()
           )
//           .formLogin(Customizer.withDefaults())
           .oauth2Login(Customizer.withDefaults());




       return http.build();
   }

}



