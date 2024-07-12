package com.api_java_spring_mongodb.api_java_spring_mongodb.dto;

import java.math.BigDecimal;

public record ProductDTO(String title, String description, BigDecimal price, String categoryId) {

}
