package com.api_java_spring_mongodb.api_java_spring_mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.api_java_spring_mongodb.api_java_spring_mongodb.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends MongoRepository<CategoryEntity, String> {

}
