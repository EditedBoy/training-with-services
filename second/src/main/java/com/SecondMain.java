package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.main.controller")
public class SecondMain {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SecondMain.class);
//        app.setDefaultProperties(Collections.singletonMap("server.port", "8090"));
        app.run(args);
    }
}