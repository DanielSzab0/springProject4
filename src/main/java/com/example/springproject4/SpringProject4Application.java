package com.example.springproject4;

import com.example.springproject4.util.AdminUserCommandLineRunner;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.springproject4.repository")
public class SpringProject4Application {

    @Autowired
    private AdminUserCommandLineRunner adminUserCommandLineRunner;

    public static void main(String[] args) {
        SpringApplication.run(SpringProject4Application.class, args);
    }

}
