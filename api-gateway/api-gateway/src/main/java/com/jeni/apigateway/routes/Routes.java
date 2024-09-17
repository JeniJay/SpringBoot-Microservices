package com.jeni.apigateway.routes;

import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;
@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute()
    {
        return GatewayRouterFunctions.route("product-service")
                .route(RequestPredicates.path("/api/product"),
                        HandlerFunctions.http("http://localhost:9091"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker
                        ("productServiceCircuitBreaker",
                                URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> productServiceSwaggerRoute()
    {
        return GatewayRouterFunctions.route("product-service-swagger")
                .route(RequestPredicates.path("aggregate/product-service/v3/api-docs"),
                        HandlerFunctions.http("http://localhost:9091"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker
                        ("productServiceCircuitBreakerSwagger",
                                URI.create("forward:/fallbackRoute")))
                .filter(setPath("/v3/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse>orderServiceRoute()
    {
        return GatewayRouterFunctions.route("order-service")
                .route(RequestPredicates.path("/api/order"),
                        HandlerFunctions.http("http://localhost:9096"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker
                        ("orderServiceCircuitBreaker",
                                URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> routeOrderServiceSwagger()
    {
        return GatewayRouterFunctions.route("order-service-swagger")
                .route(RequestPredicates.path("aggregate/order-service/v3/api-docs"),
                        HandlerFunctions.http("http://localhost:9096"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker
                        ("orderServiceCircuitBreakerSwagger",
                                URI.create("forward:/fallbackRoute")))
                .filter(setPath("/v3/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse>inventoryServiceRoute()
    {
        return GatewayRouterFunctions.route("inventory-service")
                .route(RequestPredicates.path("/api/inventory"),
                        HandlerFunctions.http("http://localhost:9093"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker
                        ("inventoryServiceCircuitBreaker",
                                URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> routeInventoryServiceSwagger()
    {
        return GatewayRouterFunctions.route("inventory-service-swagger")
                .route(RequestPredicates.path("aggregate/inventory-service/v3/api-docs"),
                        HandlerFunctions.http("http://localhost:9093"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker
                        ("inventoryServiceCircuitBreakerSwagger",
                                URI.create("forward:/fallbackRoute")))
                .filter(setPath("/v3/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackRoute()
    {
        return GatewayRouterFunctions.route("fallbackRoute")
                .GET("/fallbackRoute",
                        request->ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).body("Service in unavailable, please try again later"))
                .build();
    }
}
