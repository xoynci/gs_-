package com.xyc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StartUpApp3 {

    public static void main(String[] args) {
        SpringApplication.run(StartUpApp3.class, args);
    }

}
