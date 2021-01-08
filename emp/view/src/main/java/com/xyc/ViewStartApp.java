package com.xyc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class ViewStartApp {
    public static void main(String[] args){
        SpringApplication.run(ViewStartApp.class,args);
    }
}
