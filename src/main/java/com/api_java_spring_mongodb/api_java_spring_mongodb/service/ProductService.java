package com.api_java_spring_mongodb.api_java_spring_mongodb.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.api_java_spring_mongodb.api_java_spring_mongodb.dto.ProductDTO;
import com.api_java_spring_mongodb.api_java_spring_mongodb.entity.CategoryEntity;
import com.api_java_spring_mongodb.api_java_spring_mongodb.entity.ProductEntity;
import com.api_java_spring_mongodb.api_java_spring_mongodb.exceptions.CategoryNotFoundException;
import com.api_java_spring_mongodb.api_java_spring_mongodb.exceptions.ProductNotFoundException;
import com.api_java_spring_mongodb.api_java_spring_mongodb.repository.ProductRepository;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;

    }

    public List<ProductEntity> getAll() {
        /// Sort the product List by title ASC, a->b->c ...etc alphabethic
        Sort sort = Sort.by(Direction.ASC, "title");
        return this.productRepository.findAll(sort);
    }

    public ProductEntity create(ProductDTO productDTO) {
        CategoryEntity category = this.categoryService.getById(productDTO.categoryId())
                .orElseThrow(CategoryNotFoundException::new);

        ProductEntity newProduct = new ProductEntity();
        newProduct.setTitle(productDTO.title());
        newProduct.setDescription(productDTO.description());
        newProduct.setPrice(productDTO.price());
        newProduct.setCategory(category);

        this.productRepository.save(newProduct);

        return newProduct;
    }

    public ProductEntity update(String id, ProductDTO productDTO) {
        ProductEntity product = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        CategoryEntity category = this.categoryService.getById(productDTO.categoryId())
                .orElseThrow(CategoryNotFoundException::new);

        if (!productDTO.title().isEmpty())
            product.setTitle(productDTO.title());
        if (!productDTO.description().isEmpty())
            product.setDescription(productDTO.description());
        if (!(productDTO.price() == null))
            product.setPrice(productDTO.price());
        if (!(productDTO.categoryId() == null))
            product.setCategory(category);

        this.productRepository.save(product);

        return product;

    }

    public ProductEntity delete(String id) {
        ProductEntity product = this.productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        this.productRepository.delete(product);

        return product;
    }

}
