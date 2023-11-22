package com.nilsswensson.petplayground.facade.configuration;

import com.nilsswensson.petplayground.facade.configuration.properties.RedissonProperties;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    @ConfigurationProperties("application.integration.redisson")
    public RedissonProperties redissonProperties() {
        return new RedissonProperties();
    }

    @Bean
    public RedissonClient redissonClient(RedissonProperties redissonProperties) {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(redissonProperties.getUrl())
                .setPassword(redissonProperties.getPassword());

        return Redisson.create(config);
    }
}
