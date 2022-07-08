package com.humeyratezcan.bitirmeprojesi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan
@ComponentScan
@EnableJpaRepositories
public class BitirmeprojesiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BitirmeprojesiApplication.class, args);















    }

}
