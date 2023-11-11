package com.nilsswensson.petplayground.load.utils;

import com.nilsswensson.petplayground.common.auth.AuthenticationResponse;
import org.springframework.http.HttpCookie;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WebUtils {
    private static final String ACCESS_TOKEN = "access_token";
    private static final String AUTHORIZATION = "Authorization";
    private static final String REFRESH_TOKEN = "refresh_token";

    public static final WebClient CLIENT = WebClient.builder()
            .baseUrl("http://localhost:9093/api/v1/rest")
            .build();


    public static String post(final AuthenticationResponse auth, final String uri, final Object json) {
        System.out.println("=====================================");
        System.out.println("Post to " + uri);
        System.out.println("=====================================");
        System.out.println(json);
        System.out.println("=====================================");

        final String response = CLIENT.post()
                .uri(uri)
                .headers(h -> h.setBearerAuth(auth.getAccessToken()))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(json)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println("=====================================");
        System.out.println("Response from " + uri);
        System.out.println("=====================================");
        System.out.println(response);
        System.out.println("=====================================");

        return response;
    }

    public static String get(final AuthenticationResponse auth, final String uri) {
        System.out.println("=====================================");
        System.out.println("Get from " + uri);
        System.out.println("=====================================");

        final String response = CLIENT.get()
                .uri(uri)
                .headers(h -> h.setBearerAuth(auth.getAccessToken()))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println("=====================================");
        System.out.println("Response from " + uri);
        System.out.println("=====================================");
        System.out.println(response);
        System.out.println("=====================================");

        return response;
    }
//
//    public static void postGetFile(final TenantLoginInfo loginInfo, final String uri, final String json, final Path path) {
//        final MultiValueMap<String, ResponseCookie> cookies = login0(loginInfo);
//
//        String at = cookies.get(ACCESS_TOKEN).get(0).getValue();
//        String rt = cookies.get(REFRESH_TOKEN).get(0).getValue();
//
//        final Flux<DataBuffer> dataBufferFlux = CLIENT.post()
//                .uri(uri)
//                .cookie(ACCESS_TOKEN, at)
//                .cookie(REFRESH_TOKEN, rt)
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(json)
//                .accept(MediaType.ALL)
//                .retrieve()
//                .bodyToFlux(DataBuffer.class);
//
//        DataBufferUtils.write(dataBufferFlux, path, StandardOpenOption.CREATE).block(); //Creates new file or overwrites exisiting file
//
//    }


//
//    public static String postFile(final TenantLoginInfo loginInfo, final String uri, final String path)
//            throws IOException {
//        final MultiValueMap<String, ResponseCookie> cookies = login0(loginInfo);
//
//        String at = cookies.get(ACCESS_TOKEN).get(0).getValue();
//        String rt = cookies.get(REFRESH_TOKEN).get(0).getValue();
//
//        final String pathWithPrefix = "classpath:" + path;
//        byte[] file = Files.readAllBytes(ResourceUtils.getFile(pathWithPrefix).toPath());
//
//        MultipartBodyBuilder builder = new MultipartBodyBuilder();
//        builder.part("file", file).filename("/home/olegostanin/IdeaProjects/scenarios/src/main/resources/pics/me.png");
//
//        String httpStatusMono = CLIENT.post()
//                .uri(uri)
//                .cookie(ACCESS_TOKEN, at)
//                .cookie(REFRESH_TOKEN, rt)
//                .contentType(MediaType.MULTIPART_FORM_DATA)
//                .contentLength(file.length)
//                .body(BodyInserters.fromMultipartData(builder.build()))
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//
//        return httpStatusMono;
//    }
//
//    public static String get(final  loginInfo, final String uri) {
//        final MultiValueMap<String, ResponseCookie> cookies = login0(loginInfo);
//
//        String at = cookies.get(ACCESS_TOKEN).get(0).getValue();
//        String rt = cookies.get(REFRESH_TOKEN).get(0).getValue();
//
//        final String response = CLIENT.get()
//                .uri(uri)
//
//                .cookie(ACCESS_TOKEN, at)
//                .cookie(REFRESH_TOKEN, rt)
//                .header("tenant-id", String.valueOf(loginInfo.getTenantId()))
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//
//        return response;
//    }
//

//
//    public static String postEmpty(final  loginInfo, final String uri) {
//        final MultiValueMap<String, ResponseCookie> cookies = login0(loginInfo);
//
//        String at = cookies.get(ACCESS_TOKEN).get(0).getValue();//.toString().replace("access_token=", "");
//        String rt = cookies.get(REFRESH_TOKEN).get(0).getValue();//.toString().replace("refresh_token=", "");
//
//        final String response = CLIENT.post()
//                .uri(uri)
//                .cookie(ACCESS_TOKEN, at)
//                .cookie(REFRESH_TOKEN, rt)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//
//        return response;
//    }

    private static Map<String, List<String>> cookies(final MultiValueMap<String, ResponseCookie> responseCookies) {
        final Map<String, List<String>> res = new HashMap<>();

        responseCookies.forEach((k, v) -> res.put(k, v.stream().map(HttpCookie::getValue).collect(Collectors.toList())));

        return res;
    }
}
