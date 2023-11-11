package com.nilsswensson.petplayground.load.client;

import com.nilsswensson.petplayground.common.auth.AuthenticationRequest;
import com.nilsswensson.petplayground.common.auth.AuthenticationResponse;
import com.nilsswensson.petplayground.common.auth.RegisterRequest;
import feign.Headers;
import feign.RequestLine;
import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.RequestBody;


public interface FacadeAuthFeignClient {

    @RequestLine("POST /register")
    @Headers("Content-Type: application/json")
    @Timed
    AuthenticationResponse register(@RequestBody RegisterRequest request);

    @RequestLine("POST /authenticate")
    @Headers("Content-Type: application/json")
    @Timed
    AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request);

    @RequestLine("POST /whoami")
    @Headers("Content-Type: application/json")
    @Timed
    String whoami(@RequestBody String email);
}
