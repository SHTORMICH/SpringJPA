package com.kabaldin.socialmedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class SocialMediaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocialMediaApplication.class, args);
    }
}
