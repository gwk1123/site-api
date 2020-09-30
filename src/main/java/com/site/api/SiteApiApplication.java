package com.site.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages ={"comm.sibe","comm.ota.ctrip.transform","comm.config"})
@SpringBootApplication
public class SiteApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiteApiApplication.class, args);
    }

}
