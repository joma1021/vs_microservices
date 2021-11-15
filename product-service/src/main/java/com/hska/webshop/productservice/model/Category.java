package com.hska.webshop.productservice.model;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * This class contains details about categories.
 */
@Entity
//@Table(name = "category")
public class Category {

    /**
     *
     */
    private int id;
    private String name;
    private Set<Product> products = new HashSet<Product>(0);

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, Set<Product> products) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
