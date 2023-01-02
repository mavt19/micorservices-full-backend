package com.msvc.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msvc.product.dto.ProductRequest;
import com.msvc.product.dto.ProductResponse;
import com.msvc.product.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.reactive.function.BodyInserters;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@AutoConfigureWebTestClient(timeout = "20000")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@Autowired
	private WebTestClient webTestClient;
//	@Autowired
//	private MockMvc mockMvc;
//	@Autowired
//	private ObjectMapper content;
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.2.21");

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry){
		registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}
//	@Test
//	void shouldCreateProduct() throws Exception {
//		ProductRequest request = getProductRequest();
//		String requestString = content.writeValueAsString(request);
//		mockMvc.perform(MockMvcRequestBuilders.post("api/v1/product")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(requestString)
//		).andExpect(status().isCreated());
//
//	}

//	@Test
//	void retrieveOfferCatalogALDM_capl_mt() {
//
//		webTestClient.post()
//				.uri("api/v1/product")
//				.accept(MediaType.APPLICATION_JSON)
//				.body(BodyInserters.fromValue(offercatalogaldmRequestMap.get(CAPL_MT)))
//				.exchange()
//				.expectStatus().is2xxSuccessful()
//				.expectBody(OfferCatalogAldmResponse.class)
//				.consumeWith(exchangeResult -> {
//					OfferCatalogAldmResponse result = exchangeResult.getResponseBody();
//					assertNotNull(result.getOfferList());
//					assertNotNull(result.getOfferingsApiResponse());
//					Assertions.assertThat(result.getOfferList()).isNotEmpty();
//					assertEquals("Movistar Total HD 1 Gb + 26 Gb", result.getOfferList().get(0).getProductName());
//
//				});
//	}

	@Test
	@DisplayName("Should create product")
	void shouldCreateProduct() {
		webTestClient.post().uri("/api/v1/product")
				.body(BodyInserters.fromValue(getProductRequest()))
				.exchange()
				.expectStatus().isCreated()
				.expectStatus().is2xxSuccessful()
				.expectBody(ProductResponse.class)
				.consumeWith(exchangeResult -> {
					ProductResponse result = exchangeResult.getResponseBody();
					System.out.println(result);

				});
	}

	@Test
	@DisplayName("Should get products")
	void shouldGetProducts() {
		webTestClient.get().uri("/api/v1/product")
				.exchange()
				.expectStatus().isOk()
				.expectStatus().is2xxSuccessful()
				.expectBodyList(ProductResponse.class)
				.consumeWith(exchangeResult -> {
					List<ProductResponse> result = exchangeResult.getResponseBody();
					System.out.println(result);

				});
	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("product")
				.description("description")
				.price(BigDecimal.valueOf(20))
				.build();
	}
}
