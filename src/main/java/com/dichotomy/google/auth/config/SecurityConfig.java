package com.dichotomy.google.auth.config;
// Add the necessary dependencies in your pom.xml file
// (e.g., spring-boot-starter-security, spring-boot-starter-oauth2-client)

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

   SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
   return http.formLogin(Customizer.withDefaults())
           .oauth2Login(Customizer.withDefaults())
           .build();}
}



