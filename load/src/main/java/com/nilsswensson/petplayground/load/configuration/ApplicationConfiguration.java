package com.nilsswensson.petplayground.load.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nilsswensson.petplayground.load.client.FacadeAuthFeignClient;
import com.nilsswensson.petplayground.load.client.FacadeRestFeignClient;
import com.nilsswensson.petplayground.load.configuration.properties.FeignClientProperties;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class ApplicationConfiguration {
    @Bean
    @ConfigurationProperties("integration.auth-client")
    public FeignClientProperties authClientProperties() {
        return new FeignClientProperties();
    }

    @Bean
    @ConfigurationProperties("integration.rest-client")
    public FeignClientProperties restClientProperties() {
        return new FeignClientProperties();
    }

    @Bean
    public FacadeAuthFeignClient authClient() {
        return Feign.builder()
                .logLevel(Logger.Level.FULL)
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder(new ObjectMapper()
                        .setSerializationInclusion(JsonInclude.Include.ALWAYS)
                        .configure(SerializationFeature.INDENT_OUTPUT, true)
                        .registerModules(Collections.emptyList())))
                .decoder(new JacksonDecoder())
                .target(FacadeAuthFeignClient.class, authClientProperties().getUrl());
    }

    @Bean
    public FacadeRestFeignClient restClient() {
        return Feign.builder()
                .logLevel(Logger.Level.FULL)
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder(new ObjectMapper()
                        .setSerializationInclusion(JsonInclude.Include.ALWAYS)
                        .configure(SerializationFeature.INDENT_OUTPUT, true)
                        .registerModules(Collections.emptyList())))
                .decoder(new JacksonDecoder())
                .target(FacadeRestFeignClient.class, restClientProperties().getUrl());
    }

}
