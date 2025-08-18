package com.dutra.delivery.infra.config;

import com.dutra.delivery.infra.http.CourierApiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class CourierApiClientConfig {

    @Bean
    public CourierApiClient courierApiClient(RestClient.Builder builder) {

        RestClient restClient = builder.baseUrl("http://localhost:8081")
                .build();

        RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);

        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter)
                .build();

        return proxyFactory.createClient(CourierApiClient.class);
    }
}
