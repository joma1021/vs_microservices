package com.hska.webshop.productservice.model;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * This class contains details about categories.
 */
@Entity
public class Category {

    private int id;
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
