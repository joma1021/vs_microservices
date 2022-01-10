package com.hska.webshop.categoryservice.exceptions;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(int id) {
        super("Could not find category " + id);
    }
}
