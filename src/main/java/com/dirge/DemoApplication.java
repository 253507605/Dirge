package com.dirge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.dirge.mapper"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        new SpringApplication().setLazyInitialization(true);
//        SpringApplicationBuilder builder = new SpringApplicationBuilder();
//        builder.lazyInitialization(true);
    }

}
