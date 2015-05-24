package org.vincibean.salestaxes.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.vincibean.salestaxes")
public class SalesTaxesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesTaxesApplication.class, args);
    }
}
