package com.api_java_spring_mongodb.api_java_spring_mongodb.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class CategoryEntity {

    @Id
    private String id;

    private String title;

    private String description;

    private List<ProductEntity> productsInTheCategory;

    public CategoryEntity() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductEntity> getProductsInTheCategory() {
        return productsInTheCategory;
    }

    public void setProductsInTheCategory(List<ProductEntity> productsInTheCategory) {
        this.productsInTheCategory = productsInTheCategory;
    }

}
