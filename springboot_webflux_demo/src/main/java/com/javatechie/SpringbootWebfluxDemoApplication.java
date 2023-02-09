package com.javatechie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.util.StopWatch;

@SpringBootApplication
public class SpringbootWebfluxDemoApplication {

    @Bean()
    @Scope(value = "prototype")
    public StopWatch stopWatch() {
        return new StopWatch();
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebfluxDemoApplication.class, args);
    }

}
