package com.xmu.wowomall.footprint;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.xmu.wowomall.footprint.mapper")
@EnableEurekaClient
public class FootprintApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootprintApplication.class, args);
    }

}
