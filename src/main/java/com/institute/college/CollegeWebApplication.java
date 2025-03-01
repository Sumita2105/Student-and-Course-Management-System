package com.institute.college;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.institute.college.repository")
@EntityScan("com.institute.college.model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class CollegeWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollegeWebApplication.class, args);
    }

}
