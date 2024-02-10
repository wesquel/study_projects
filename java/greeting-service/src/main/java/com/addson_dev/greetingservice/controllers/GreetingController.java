package com.addson_dev.greetingservice.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.addson_dev.greetingservice.config.GreetingConfig;
import com.addson_dev.greetingservice.model.Greeting;

@RestController
public class GreetingController {
  
  private static final String template = "%s, %s!";
  private final AtomicLong counter = new AtomicLong();

  @Autowired
  private GreetingConfig config;

  @GetMapping("/greeting")
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "") String name) {
    if(name.isEmpty()){
      name = config.getValue();
    }
    return new Greeting(counter.incrementAndGet(), String.format(template, config.getGreeting(), name));
  }
  
  

}
