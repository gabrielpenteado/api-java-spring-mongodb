package com.api_java_spring_mongodb.api_java_spring_mongodb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.api_java_spring_mongodb.api_java_spring_mongodb.dto.CategoryDTO;
import com.api_java_spring_mongodb.api_java_spring_mongodb.entity.CategoryEntity;
import com.api_java_spring_mongodb.api_java_spring_mongodb.exceptions.CategoryNotFoundException;
import com.api_java_spring_mongodb.api_java_spring_mongodb.repository.CategoryRepository;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryEntity> getAll() {
        // Sort the category List by title ASC, a->b->c ...etc alphabethic
        Sort sort = Sort.by(Direction.ASC, "title");
        return this.categoryRepository.findAll(sort);
    }

    public Optional<CategoryEntity> getById(String id) {
        return this.categoryRepository.findById(id);
    }

    public CategoryEntity create(CategoryDTO categoryDTO) {
        CategoryEntity newCategory = new CategoryEntity();
        newCategory.setTitle(categoryDTO.title());
        newCategory.setDescription(categoryDTO.description());

        this.categoryRepository.save(newCategory);
        return newCategory;
    }

    public CategoryEntity update(String id, CategoryDTO categoryDTO) {
        CategoryEntity category = this.categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        if (!categoryDTO.title().isEmpty())
            category.setTitle(categoryDTO.title());

        if (!categoryDTO.description().isEmpty())
            category.setDescription(categoryDTO.description());

        this.categoryRepository.save(category);

        return category;
    }

    public CategoryEntity delete(String id) {
        CategoryEntity category = this.categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        this.categoryRepository.delete(category);

        return category;
    }

}
