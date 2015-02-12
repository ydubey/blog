package com.elega9t.sample.springboot.it.steps;

import com.elega9t.sample.springboot.Application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@IntegrationTest({"server.port=0"})
@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
public abstract class BaseStepdefs {

  @Value("${local.server.port}")
  private int port;

  @Value("${app.base}")
  private String appBase;

  public String relativePath(String path) {
    return String.format("%s/%s", appBase, path);
  }

}
