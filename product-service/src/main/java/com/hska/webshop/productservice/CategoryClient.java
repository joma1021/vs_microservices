package com.hska.webshop.productservice;

import com.hska.webshop.productservice.model.Category;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

public class CategoryClient {

    private final static Logger LOGGER = Logger.getLogger(CategoryClient.class.getName());

    static Category getCategoryById(int id)
    {
        final String uri = "http://category-service:8085/api/category/";
        Category category;

        RestTemplate restTemplate = new RestTemplate();
        try {
            category = restTemplate.getForObject(uri + id, Category.class);
        } catch (Exception e) {
            LOGGER.severe("Failed to get category with id '" + id + "': " + e.getMessage());
            return null;
        }
        return category;
    }

}