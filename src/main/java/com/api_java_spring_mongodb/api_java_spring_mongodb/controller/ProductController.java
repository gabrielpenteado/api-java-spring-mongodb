package com.api_java_spring_mongodb.api_java_spring_mongodb.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_java_spring_mongodb.api_java_spring_mongodb.dto.ProductDTO;
import com.api_java_spring_mongodb.api_java_spring_mongodb.entity.ProductEntity;
import com.api_java_spring_mongodb.api_java_spring_mongodb.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAll() {
        List<ProductEntity> products = this.productService.getAll();
        return ResponseEntity.ok().body(products);
    }

    @PostMapping
    public ResponseEntity<ProductEntity> create(@RequestBody ProductDTO productDTO) {
        ProductEntity newProduct = this.productService.create(productDTO);
        return ResponseEntity.ok().body(newProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> update(@PathVariable("id") String id, @RequestBody ProductDTO productDTO) {
        ProductEntity updatedProduct = this.productService.update(id, productDTO);
        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductEntity> delete(@PathVariable("id") String id) {
        ProductEntity deletedProduct = this.productService.delete(id);
        return ResponseEntity.ok().body(deletedProduct);
    }

}
