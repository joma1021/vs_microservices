package com.example.restservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {


    public Product() {

    }

    private int id;
    private String name;
    private Category category;
    private String details;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public StringBuilder productsToString(Product[] products) {
        StringBuilder productString = new StringBuilder();
        for (Product product : products) {
            productString.append(" ").append(product.name);
        }
        return productString;
    }
}
