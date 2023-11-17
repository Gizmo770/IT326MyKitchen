package com.itproject.MyKitchenResources;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "com.itproject.MyKitchenResources.Entities")
public class JpaConfig {
    // Other configurations if needed
}
