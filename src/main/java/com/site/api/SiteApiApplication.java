package com.site.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients(basePackages = {"com.sibecommon.feign"})
@MapperScan({"com.sibecommon.repository.mapper"})
@ComponentScan(basePackages ={"com.sibecommon","com.site.api"})
@SpringBootApplication
public class SiteApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiteApiApplication.class, args);
    }

}
