package com.example.restservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(RestServiceApplication.class);

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            Product[] products = restTemplate.getForObject(
                    "http://localhost:8081/product-service/api/product/all", Product[].class);
            for(int i = 0; i < products.length; i++) {
                log.info(products[i].getName());
            }

        };
    }

    private static Product[] getProducts()
    {
        final String uri = "http://localhost:8081/product-service/api/product/all";

        RestTemplate restTemplate = new RestTemplate();

        Product[] products = restTemplate.getForObject(
                uri, Product[].class);
        return products;
    }
}
