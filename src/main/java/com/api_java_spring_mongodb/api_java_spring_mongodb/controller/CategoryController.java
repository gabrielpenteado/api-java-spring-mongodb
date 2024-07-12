package com.api_java_spring_mongodb.api_java_spring_mongodb.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_java_spring_mongodb.api_java_spring_mongodb.dto.CategoryDTO;
import com.api_java_spring_mongodb.api_java_spring_mongodb.entity.CategoryEntity;
import com.api_java_spring_mongodb.api_java_spring_mongodb.entity.ProductEntity;
import com.api_java_spring_mongodb.api_java_spring_mongodb.service.CategoryService;
import com.api_java_spring_mongodb.api_java_spring_mongodb.service.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService;

    private ProductService productService;

    public CategoryController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryEntity>> getAll() {
        List<CategoryEntity> categories = this.categoryService.getAll();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ProductEntity>> getAllProductsInTheCategory(@PathVariable("id") String id) {
        List<ProductEntity> products = this.productService.getAll();

        // To compare String in Java remember to use equals because they are Objects
        List<ProductEntity> productsOfTheCategory = products.stream()
                .filter(product -> product.getCategory().getId().equals(id))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(productsOfTheCategory);

    }

    @PostMapping
    public ResponseEntity<CategoryEntity> create(@RequestBody CategoryDTO categoryDTO) {
        CategoryEntity newCategory = this.categoryService.create(categoryDTO);
        return ResponseEntity.ok().body(newCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryEntity> update(@PathVariable("id") String id, @RequestBody CategoryDTO categoryDTO) {
        CategoryEntity updatedCategory = this.categoryService.update(id, categoryDTO);
        return ResponseEntity.ok().body(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryEntity> delete(@PathVariable("id") String id) {
        CategoryEntity deletedCategory = this.categoryService.delete(id);
        return ResponseEntity.ok().body(deletedCategory);
    }

}
