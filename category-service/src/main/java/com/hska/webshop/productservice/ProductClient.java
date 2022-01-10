package com.hska.webshop.categoryservice;

import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

public class ProductClient {

    private final static Logger LOGGER = Logger.getLogger(ProductClient.class.getName());

    static void deleteProductsByCategoryId(int id) {
        final String uri = "http://product-service:8081/api/product/category/";

        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.delete(uri + id);
        } catch (Exception e) {
            LOGGER.severe("Failed to delete products with categoryId '" + id + "'ErrorMessage: " + e.getMessage());
            throw e;
        }
    }

}