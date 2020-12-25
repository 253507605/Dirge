package com.dirge.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyBean implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("项目启动之前执行");
    }
}
