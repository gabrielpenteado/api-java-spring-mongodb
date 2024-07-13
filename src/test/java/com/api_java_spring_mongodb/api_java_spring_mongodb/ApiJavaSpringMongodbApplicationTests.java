package com.api_java_spring_mongodb.api_java_spring_mongodb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.api_java_spring_mongodb.api_java_spring_mongodb.entity.CategoryEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApiJavaSpringMongodbApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testCreateCategorySuccess() {
		CategoryEntity newCategory = new CategoryEntity();

		newCategory.setTitle("testCatTitle");
		newCategory.setDescription("testCatDesc");

		webTestClient
				.post()
				.uri("/api/category")
				.bodyValue(newCategory)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$.title").isEqualTo(newCategory.getTitle())
				.jsonPath("$.description").isEqualTo(newCategory.getDescription())
				.returnResult();
	}

}
