package org.cocktail.cocktailappapi.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "org.cocktail.db")
@EnableJpaRepositories(basePackages = "org.cocktail.db")
public class JpaConfig {
}
