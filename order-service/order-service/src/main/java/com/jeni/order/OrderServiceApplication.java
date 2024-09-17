package com.jeni.order;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableFeignClients
@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info
		(title = "Order Service API", version = "v0.0.1", description = "This is the REST API for Order Service"))
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
