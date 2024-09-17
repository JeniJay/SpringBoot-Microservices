package com.jeni.product;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Matches;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductApplicationTests {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUpURL()
    {
        RestAssured.baseURI="http://localhost";
        RestAssured.port=port;
    }

    @Test
    void contextLoads() {
        String requestBodyString = """
                    {
                            "name": "Jiniya",
                            "price": 12344.12
                    }
                """;
        RestAssured.given()
                .contentType("application/json")
                .body(requestBodyString)
                .when()
                .post("/api/product")
                .then()
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("name",Matchers.equalTo("Jiniya"));

    }
}