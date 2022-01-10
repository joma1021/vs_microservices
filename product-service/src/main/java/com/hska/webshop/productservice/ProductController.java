package com.hska.webshop.productservice;

import com.hska.webshop.productservice.exceptions.ProductNotFoundException;
import com.hska.webshop.productservice.model.Category;
import com.hska.webshop.productservice.model.FullProduct;
import com.hska.webshop.productservice.model.Product;
import com.hska.webshop.productservice.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static com.hska.webshop.productservice.CategoryClient.getCategoryById;


@RestController
@RequestMapping(path="/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path="/{id}", produces="application/json")
    public @ResponseBody FullProduct getProductById(@PathVariable int id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return new FullProduct( product.getId(), product.getName(), product.getPrice(), getCategoryById(product.getCategoryId()), product.getDetails());
    }

    @GetMapping(path="/byCategory/{categoryId}", produces="application/json")
    public @ResponseBody
    ResponseEntity<Collection<Product>> getProductByCategoryId(@PathVariable int categoryId) {
        Iterable<Product> products = productRepository.findAll();
		Stream<Product> stream = StreamSupport.stream(products.spliterator(), false);
        Predicate<Product> categoryPredicate = prod -> prod.getCategoryId() == categoryId;
        Collection<Product> response = stream.filter(categoryPredicate ).collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public @ResponseBody ResponseEntity<Collection<Product>> filterProductsByPriceAndTerm(@RequestParam(required = false) String term, @RequestParam(required = false) String priceMin, @RequestParam(required = false) String priceMax) {
        Iterable<Product> products = productRepository.findAll();
        Stream<Product> stream = StreamSupport.stream(products.spliterator(), false);
        Predicate<Product> priceTermPredicate = prod -> prod.getPrice() >= Double.parseDouble(priceMin) && prod.getPrice() <= Double.parseDouble(priceMax) && prod.getName().toLowerCase().contains(term);
        Collection<Product> response = stream.filter(priceTermPredicate).collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path="/add", consumes="application/json", produces="application/json")
    public Product newProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    @DeleteMapping(path="category/{categoryId}")
    public @ResponseBody ResponseEntity<Integer> deleteProductsByCategoryId(@PathVariable int categoryId) {
        Iterable<Product> products = productRepository.findAll();
        Stream<Product> stream = StreamSupport.stream(products.spliterator(), false);
        Predicate<Product> categoryPredicate = prod -> prod.getCategoryId() == categoryId;
        Collection<Product> productList = stream.filter(categoryPredicate).collect(Collectors.toList());

        try {
            productRepository.deleteAll(productList);
        } catch(Exception e) {
            return new ResponseEntity<>(categoryId, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(categoryId, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable int id) {
        HttpStatus response;

        try {
            productRepository.deleteById(id);
            response = HttpStatus.OK;
        } catch(Exception e) {
            response = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(id, response);
    }

    @GetMapping(path="/all", produces="application/json")
    public @ResponseBody Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }
}