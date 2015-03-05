package com.ac.cs5551.lab7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ac010168
 *
 * Primary Launcher for the REST Service
 */
@ComponentScan
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(Application.class);
  }
  
  /** Main method, which is starting point for service using Spring launcher */
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
