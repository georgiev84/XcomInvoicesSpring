package com.petar.workspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.petar.workspring.*")
@ComponentScan(basePackages = { "com.petar.workspring.*" })
@EntityScan("com.petar.workspring.*")
@SpringBootApplication
public class WorkspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkspringApplication.class, args);


    }

}
