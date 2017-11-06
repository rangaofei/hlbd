package com.hanlinbode.hlbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class HlbdApplication {

    public static void main(String[] args) {
        SpringApplication.run(HlbdApplication.class, args);
    }
}
