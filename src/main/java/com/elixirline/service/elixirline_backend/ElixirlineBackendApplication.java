package com.elixirline.service.elixirline_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ElixirlineBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElixirlineBackendApplication.class, args);
    }

}
