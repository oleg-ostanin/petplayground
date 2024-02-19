package com.nilsswensson.petplayground.facade.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nilsswensson.petplayground.facade.configuration.properties.RedissonProperties;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ApplicationConfiguration {
    @Bean
    @ConfigurationProperties("application.integration.redisson")
    public RedissonProperties redissonProperties() {
        return new RedissonProperties();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();

        return mapper;
    }

    @Bean
    public RedissonClient redissonClient(RedissonProperties redissonProperties) {
        log.info("Redis url: {}", redissonProperties.getUrl());
        log.info("Redis password: {}", redissonProperties.getPassword());

        Config config = new Config();
        config.useSingleServer()
                .setAddress(redissonProperties.getUrl())
                .setPassword(redissonProperties.getPassword());

        return Redisson.create(config);
    }
}
