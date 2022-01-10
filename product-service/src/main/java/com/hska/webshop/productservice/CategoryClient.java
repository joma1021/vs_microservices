package com.hska.webshop.productservice;

import com.hska.webshop.productservice.model.Category;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class CategoryClient {

    private final static Logger LOGGER = Logger.getLogger(CategoryClient.class.getName());

    static Category getCategoryById(int id) {
        final String uri = "http://category-service:8085/api/category/";
        Category category;

        RestTemplate restTemplate = new RestTemplate();
        try {
            category = restTemplate.getForObject(uri + id, Category.class);
        } catch (Exception e) {
            LOGGER.severe("Failed to get category with id '" + id + "'ErrorMessage: " + e.getMessage());
            throw e;
        }
        return category;
    }

    static List<Category> getCategories() {
        final String uri = "http://category-service:8085/api/category/all";

        RestTemplate restTemplate = new RestTemplate();
        Category[] categories;

        try {
            categories = restTemplate.getForObject(uri, Category[].class);
        } catch (Exception e) {
            LOGGER.severe("Failed to get categories: ErrorMessage:" + e.getMessage());
            throw e;
        }
        return Arrays.asList(categories);
    }

}