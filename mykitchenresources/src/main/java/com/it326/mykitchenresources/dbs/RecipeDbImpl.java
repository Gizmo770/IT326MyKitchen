package com.it326.mykitchenresources.dbs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.annotation.PostConstruct;

@Component
public class RecipeDbImpl implements RecipeDb {

    @Value("${edamam.appId}")
    private String appId;

    @Value("${edamam.appKey}")
    private String appKey;

    @Value("${edamam.url}")
    private String appUrl;

    private WebClient.Builder webClientBuilder;
    private WebClient webClient;

    public RecipeDbImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @PostConstruct
    public void init() {
        this.webClient = webClientBuilder
            .baseUrl(appUrl)
            .exchangeStrategies(ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1048576)) // 1 MB
                .build())
            .build();
    }
    
    public String searchRecipeData(String ingredients) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("type", "public")
                        .queryParam("q", ingredients)
                        .queryParam("app_id", appId)
                        .queryParam("app_key", appKey)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
    // Example:
    // https://api.edamam.com/api/recipes/v2?type=public&q=tortilla%20beef%20onion&app_id=af0ccd2e&app_key=%20cae7ab16a0397fd185eed623a719193e
    // Returns recipes involving tortilla, beef, and onion.
}
