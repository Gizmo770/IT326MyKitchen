package com.it326.mykitchenresources.dbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class RecipeDb {

    @Value("${edamam.appId}")
    private String appId;

    @Value("${edamam.appKey}")
    private String appKey;

    @Autowired
    private WebClient webClient;

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
