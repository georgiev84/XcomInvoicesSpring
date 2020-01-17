package com.petar.workspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

@EnableJpaRepositories("com.petar.workspring.*")
@ComponentScan(basePackages = { "com.petar.workspring.*" })
@EntityScan("com.petar.workspring.*")
@SpringBootApplication
public class WorkspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkspringApplication.class, args);

//        ApplicationContext ctx = SpringApplication.run(WorkspringApplication.class, args);
//        System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//        String[] beanNames = ctx.getBeanDefinitionNames();
//        Arrays.sort(beanNames);
//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//        }
    }

}
