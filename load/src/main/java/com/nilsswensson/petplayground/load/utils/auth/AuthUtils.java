package com.nilsswensson.petplayground.load.utils.auth;

import com.nilsswensson.petplayground.common.auth.AuthenticationRequest;
import com.nilsswensson.petplayground.common.auth.AuthenticationResponse;
import com.nilsswensson.petplayground.load.utils.WebUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;

public class AuthUtils {
    private static final WebClient AUTH_CLIENT = WebClient.builder()
            .baseUrl("http://localhost:9093/api/v1/auth")
            .build();

    private static final String ACCESS_TOKEN_PREFIX = "access_token=";
    private static final String REFRESH_TOKEN_PREFIX = "refresh_token=";


    public static String signIn(final String inviteUrl) throws IOException {


        final MultiValueMap<String, ResponseCookie> signInCookies = AUTH_CLIENT.get()
                .uri(inviteUrl)
                .accept(MediaType.ALL)
                .exchangeToMono(response -> {
                    MultiValueMap<String, ResponseCookie> cookies = response.cookies();
                    for (var cookie : cookies.entrySet()) {
                        System.out.println(cookie.getKey() + " : " + cookie.getValue());
                    }
                    return Mono.just(response.cookies());
                })
                .block();

        System.out.println(signInCookies);

        return signInCookies.toString();
    }

    public static String postUnauthorized(final String uri, final Object json, final String referer) {
        final MultiValueMap<String, ResponseCookie> signInCookies = AUTH_CLIENT.post()
                .uri(uri)
                .header("Origin", "http://localhost:8080")
                .header("Referer", referer)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(json)
                .accept(MediaType.ALL)
                .exchangeToMono(response -> {
                    MultiValueMap<String, ResponseCookie> cookies = response.cookies();
                    for (var cookie : cookies.entrySet()) {
                        System.out.println(cookie.getKey() + " : " + cookie.getValue());
                    }
                    return Mono.just(response.cookies());
                })
                .block();

        return signInCookies.toString();
    }

    public static String postUnauthorized0(final String uri, final Object json, final String referer) {
        final String res = AUTH_CLIENT.post()
                .uri(uri)
                .header("Origin", "http://localhost:8080")
                .header("Referer", referer)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(json)
                .accept(MediaType.ALL)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return res;
    }

    public static AuthenticationResponse register(final Object json) {
        return AUTH_CLIENT.post()
                .uri("/register")
                .header("Origin", "http://localhost:9094")
                .header("Referer", "")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(json)
                .accept(MediaType.ALL)
                .retrieve()
                .bodyToMono(AuthenticationResponse.class)
                .block();
    }

    public static AuthenticationResponse authenticate(final Object json) {
        return AUTH_CLIENT.post()
                .uri("/authenticate")
                .header("Origin", "http://localhost:9094")
                .header("Referer", "")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(json)
                .accept(MediaType.ALL)
                .retrieve()
                .bodyToMono(AuthenticationResponse.class)
                .block();
    }

    public static String whoami(final String uri, final Object json) {
        return AUTH_CLIENT.post()
                .uri(uri)
                .header("Origin", "http://localhost:9094/")
                .header("Referer", "")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(json)
                .accept(MediaType.ALL)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public static MultiValueMap<String, ResponseCookie> login0(final AuthenticationRequest request) {
        MultiValueMap<String, ResponseCookie> responseCookies = WebUtils.CLIENT.post()
                .uri("/api/v1/auth/authenticate")
                .header("Origin", "http://localhost:8080")
                .header("Referer", "http://localhost:8080/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .accept(MediaType.ALL)
                .exchangeToMono(response -> {
                    MultiValueMap<String, ResponseCookie> cookies = response.cookies();
                    for (var cookie : cookies.entrySet()) {
                        //System.out.println(cookie.getKey() + " : " + cookie.getValue());
                    }

                    return Mono.just(response.cookies());
                })
                .block();


        return responseCookies;
    }

    public static MultiValueMap<String, ResponseCookie> login1(final AuthenticationRequest request) {
        MultiValueMap<String, ResponseCookie> responseCookies = WebUtils.CLIENT.post()
                .uri("/auth/login")
                .header("Origin", "http://localhost:8080")
                .header("Referer", "http://localhost:8080/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .accept(MediaType.ALL)
                .exchangeToMono(response -> Mono.fromCallable(response::cookies))
                .block();
        return responseCookies;
    }



//    private static TokenHolder tokenHolder(final ResponseEntity<Void> response) {
//        final List<String> cookies = response.getHeaders().get("Set-Cookie");
//
//        final TokenHolder.TokenHolderBuilder builder = TokenHolder.builder();
//
//        for(String cookie : cookies) {
//            cookie = cookie.replace("; HttpOnly;SameSite=Strict;Path=/;", "");
//
//            if (cookie.contains(ACCESS_TOKEN_PREFIX)) {
//                builder.accessToken(cookie.replace(ACCESS_TOKEN_PREFIX, ""));
//            }
//
//            if (cookie.contains(REFRESH_TOKEN_PREFIX)) {
//                builder.refreshToken(cookie.replace(REFRESH_TOKEN_PREFIX, ""));
//            }
//        }
//        // TODO check if tokens are not null, use retry template
//        return builder.build();
//    }
}
