package com.hska.webshop.productservice;

import com.hska.webshop.productservice.exceptions.ProductNotFoundException;
import com.hska.webshop.productservice.model.Product;
import com.hska.webshop.productservice.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path="/{id}", produces="application/json")
    public @ResponseBody Product getProduct(@PathVariable int id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @PostMapping(path="/add", consumes="application/json", produces="application/json")
    public Product newProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable int id) {

       productRepository.deleteById(id);

       return ResponseEntity.noContent().build();
    }

    @GetMapping(path="/all", produces="application/json")
    public @ResponseBody Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }
}