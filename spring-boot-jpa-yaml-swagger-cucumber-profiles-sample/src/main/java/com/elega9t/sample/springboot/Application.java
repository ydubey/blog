package com.elega9t.sample.springboot;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.models.dto.ResponseMessage;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Collections;

@EnableSwagger
@SpringBootApplication
public class Application {

  private SpringSwaggerConfig springSwaggerConfig;

  public static void main(final String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Autowired
  public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
    this.springSwaggerConfig = springSwaggerConfig;
  }

  @Bean
  public SwaggerSpringMvcPlugin customImplementation() {
    ApiInfo
        apiInfo =
        new ApiInfo("Sample SpringBoot REST API", "API for SpringBoot Sample",
                    "Sample SpringBoot REST API terms of service", "someone@somewhere.com",
                    "Sample SpringBoot REST API Licence Type",
                    "Sample SpringBoot REST API License URL");
    return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
        .globalResponseMessage(RequestMethod.GET, Collections.<ResponseMessage>emptyList())
        .globalResponseMessage(RequestMethod.POST, Collections.<ResponseMessage>emptyList())
        .globalResponseMessage(RequestMethod.PUT, Collections.<ResponseMessage>emptyList())
        .globalResponseMessage(RequestMethod.DELETE, Collections.<ResponseMessage>emptyList())
        .apiInfo(apiInfo).includePatterns("/api/.*")
        .genericModelSubstitutes(DeferredResult.class);
  }

}
