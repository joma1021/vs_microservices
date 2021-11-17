package com.example.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
public class RestServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(RestServiceApplication.class);
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    private static Product[] getProducts()
    {
        final String uri = "http://localhost:8081/product-service/api/product/all";

        RestTemplate restTemplate = new RestTemplate();

        Product[] products = restTemplate.getForObject(
                uri, Product[].class);
       return products;
    }



    @GetMapping("/search")
    public Product[] products(@RequestParam(value = "term", defaultValue = "") String term) {
        Product[] products = getProducts();
        // Product[] searchedProducts = Arrays.stream(products).filter(product -> product.getName().contains(term));

        return products;
    }
}
