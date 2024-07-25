package org.example.datascrapedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DataScrapeDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataScrapeDemoApplication.class, args);
    }
}
