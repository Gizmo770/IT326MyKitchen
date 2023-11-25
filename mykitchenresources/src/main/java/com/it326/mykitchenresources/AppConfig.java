package com.it326.mykitchenresources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Value("${edamam.url}")
    private String appUrl;

    @Bean
    public WebClient webClient(WebClient.Builder webClientBuilder) {
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
            .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1048576)) // 1 MB
            .build();
        return webClientBuilder
            .baseUrl(appUrl)
            .exchangeStrategies(exchangeStrategies)
            .build();
    }
}
