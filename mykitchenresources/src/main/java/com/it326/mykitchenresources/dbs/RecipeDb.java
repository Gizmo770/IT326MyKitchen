package com.it326.mykitchenresources.dbs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class RecipeDb {

    @Value("${edamam.url}")
    private String appUrl;

    @Value("${edamam.app_id}")
    private String appId;

    @Value("${edamam.app_key}")
    private String appKey;

    private final RestTemplate restTemplate;

    public RecipeDb(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String searchRecipeData(String ingredients) {
        String url = UriComponentsBuilder.fromHttpUrl(appUrl)
        .queryParam("q", ingredients)
        .queryParam("app_id", appId)
        .queryParam("app_key", appKey)
        .toUriString();

        return restTemplate.getForObject(url, String.class);
    }
    // Example:
    // https://api.edamam.com/api/recipes/v2?type=public&q=tortilla%20beef%20onion&app_id=af0ccd2e&app_key=%20cae7ab16a0397fd185eed623a719193e
    // Returns recipes involving tortilla, beef, and onion.
}
