package com.hska.webshop.productservice;

import com.hska.webshop.productservice.exceptions.ProductNotFoundException;
import com.hska.webshop.productservice.model.Category;
import com.hska.webshop.productservice.model.Product;
import com.hska.webshop.productservice.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller	// This means that this class is a Controller
@RequestMapping(path="/product") // This means URL's start with /demo (after Application path)
public class ProductController {
    @Autowired
    // Which is auto-generated by Spring, we will use it to handle the data
    private ProductRepository productRepository;

    @GetMapping(path="/get")
    public @ResponseBody
    Optional<Product> getProduct(@RequestParam int id) {
        // This returns a JSON or XML with the users
        return productRepository.findById(id);
    }

    @GetMapping("/{id}")
    Product one(@PathVariable int id) {

        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @PostMapping(path="/add")
    public @ResponseBody Product addNewProduct (@RequestParam String name
            , @RequestParam double price, @RequestParam Category category , @RequestParam String details) {

       Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setPrice(price);
        product.setDetails(details);
        return productRepository.save(product);
    }

//    @PostMapping(path="/add")
//    public Product newProduct(@RequestBody Product product){
//        return productRepository.save(product);
//    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable int id) {
        productRepository.deleteById(id);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }
}