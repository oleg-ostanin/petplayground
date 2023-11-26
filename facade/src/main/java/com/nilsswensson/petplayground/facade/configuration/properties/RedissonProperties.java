package com.nilsswensson.petplayground.facade.configuration.properties;

import lombok.Data;

@Data
public class RedissonProperties {
    private String url;
    private String password;
}
