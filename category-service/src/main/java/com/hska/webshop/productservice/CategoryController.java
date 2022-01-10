package com.hska.webshop.categoryservice;

import com.hska.webshop.categoryservice.exceptions.CategoryNotFoundException;
import com.hska.webshop.categoryservice.model.Category;
import com.hska.webshop.categoryservice.model.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(path="/{id}", produces="application/json")
    public @ResponseBody Category getCategory(@PathVariable int id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @PostMapping(path="/add", consumes="application/json", produces="application/json")
    public Category newCategory(@RequestBody Category category){
        return categoryRepository.save(category);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCategory(@PathVariable int id) {

       categoryRepository.deleteById(id);

       return ResponseEntity.noContent().build();
    }

    @GetMapping(path="/all", produces="application/json")
    public @ResponseBody Iterable<Category> getAllCategorys() {
        return categoryRepository.findAll();
    }
}