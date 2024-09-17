package com.jeni.order;

import com.jeni.order.clientStub.InventoryClient;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class OrderServiceApplicationTests {

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
                            "skuCode": "123",
                            "quantity": 2
                    }
                """;
		InventoryClient.getInventory("123",2);
		RestAssured.given()
				.contentType("application/json")
				.body(requestBodyString)
				.when()
				.get("/api/order")
				.then()
				.statusCode(200)
				.contentType("text/plain")
				//.body("id", Matchers.notNullValue())
				.body(Matchers.equalTo("success"));

	}
}
