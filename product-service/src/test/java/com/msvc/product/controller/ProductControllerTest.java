package com.msvc.product.controller;

import com.msvc.product.dto.ProductRequest;
import com.msvc.product.dto.ProductResponse;
import com.msvc.product.repository.ProductRepository;
import com.msvc.product.service.ProductService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
class ProductControllerTest {

    @Autowired
    private WebTestClient client;

    @Autowired
    private ProductRepository repository;

    @MockBean
    ProductService productService;

    @Before
    public void setupDb(){
        repository.deleteAll();
    }

    private static ProductResponse productResponse;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        productResponse = ProductResponse.builder().idResponse("1").name("product").price(new BigDecimal(20)).createdDate(LocalDateTime.now()).build();
    }
    @Test
    @DisplayName("Should create product")
    void create() {
        Mono<ProductResponse> productMock = Mono.just(productResponse);
        when(productService.create(Mockito.any(ProductRequest.class))).thenReturn(productMock);
        client.post().uri("/api/v1/product")
                .body(BodyInserters.fromValue(getProductRequest()))
                .exchange()
                .expectStatus().isCreated()
                .expectStatus().is2xxSuccessful()
                .expectBody(ProductResponse.class)
                .consumeWith(exchangeResult -> {
                    ProductResponse result = exchangeResult.getResponseBody();
                    System.out.println(result);
                    assertThat(result.getIdResponse()).isEqualTo("1");
                    assertThat(result.getName()).isEqualTo("product");
                    assertThat(result.getName()).isNotBlank();
                    assertThat(result.getDescription()).isNull();
                    assertThat(result.getPrice()).isEqualTo(new BigDecimal(20));
                    assertThat(result.getCreatedDate()).isNotNull();

                });
    }

    @Test
    @DisplayName("Should get products")
    void findAll() {
        when(productService.findAll()).thenReturn(Flux.just(productResponse));

        client.get().uri("/api/v1/product")
                .exchange()
                .expectStatus().isOk()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(ProductResponse.class)
                .consumeWith(exchangeResult -> {
                    List<ProductResponse> result = exchangeResult.getResponseBody();
                    System.out.println(result);
                    System.out.println(result.size());

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