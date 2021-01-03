package com.cy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //启动eureka服务端
public class ItcaseServiceErukaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItcaseServiceErukaApplication.class, args);
    }

}
