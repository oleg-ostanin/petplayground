package com.nilsswensson.petplayground.common.util;

public class UriConstants {
    public static final String LOCALHOST = "http://localhost:9093/api/v1/auth";
    public static final String FACADE_PORT = "9093";
    public static final String FACADE_URL = LOCALHOST + ":" + FACADE_PORT;
    public static final String API_V1 = "/api/v1";
    public static final String AUTH = "/auth";
    public static final String AUTH_URL = FACADE_URL + API_V1 + AUTH;
    public static final String REST = "/rest";
    public static final String REST_URL = FACADE_URL + API_V1 + REST;


}
