package com.jeni.order.config;

import com.jeni.order.client.InventoryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

@Configuration
public class RestClientConfig {

    @Value("${inventoryservice.url}")
    private String inventoryClientURL;

    @Bean
    public InventoryClient inventoryClient() {
        RestClient restClient= RestClient.builder()
                //.baseUrl("http://localhost:9093")
                .baseUrl(inventoryClientURL)
                .requestFactory(getRequestFactory())
                .build();
       var restClientAdapter= RestClientAdapter.create(restClient);
       var httpServiceProxyFactory= HttpServiceProxyFactory.builderFor(restClientAdapter).build();
       return httpServiceProxyFactory.createClient(InventoryClient.class);
    }

    private ClientHttpRequestFactory getRequestFactory() {
        ClientHttpRequestFactorySettings clientHttpRequestFactorySettings  = ClientHttpRequestFactorySettings.DEFAULTS
                .withConnectTimeout(Duration.ofSeconds(3))
                .withReadTimeout(Duration.ofSeconds(3));
        return ClientHttpRequestFactories.get(clientHttpRequestFactorySettings);
    }


}
