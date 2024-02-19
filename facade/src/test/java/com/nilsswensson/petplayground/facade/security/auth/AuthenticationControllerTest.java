package com.nilsswensson.petplayground.facade.security.auth;

import com.nilsswensson.petplayground.common.auth.RegisterRequest;
import com.nilsswensson.petplayground.common.token.Token;
import com.nilsswensson.petplayground.common.user.Role;
import com.redis.testcontainers.RedisContainer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.Cookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@AutoConfigureWebMvc
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

//@ExtendWith(MockitoExtension.class)

class AuthenticationControllerTest {
    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    MockMvc mvc;

//    @MockBean
//    UserRepository userRepository;
//
//    @MockBean
//    TokenRepository tokenRepository;
//    @MockBean
//    PasswordEncoder passwordEncoder;
    @Test
    @Order(10)
    void tokenWhenAnonymousThenStatusIsUnauthorized() throws Exception {
        this.mvc.perform(get("/api/v1/rest/book")).andExpect(status().isForbidden());
    }

//    @Test
//    @Order(20)
    void register() throws Exception {
        final long randLong = ThreadLocalRandom.current().nextLong();
        final String email = String.format("admin%d@mail.com", randLong);

        com.nilsswensson.petplayground.common.auth.RegisterRequest admin = RegisterRequest.builder()
                .firstname("Admin")
                .lastname("Admin")
                .email(email)
                .password("password")
                .role(Role.ADMIN)
                .build();

        MockHttpServletRequestBuilder register = post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(admin));

        final String tokens = this.mvc.perform(register)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        final AuthenticationResponse authResponse = mapper.readValue(tokens, AuthenticationResponse.class);

        MockHttpServletRequestBuilder getBook = get("/api/v1/rest/book")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + authResponse.getAccessToken());

        this.mvc.perform(getBook).andExpect(status().isOk());
    }

//    @Test
//    void tokenWithBasicThenGetToken() throws Exception {
//        MvcResult result = this.mvc.perform(post("/token").with(httpBasic("dvega", "password"))).andExpect(status().isOk()).andReturn();
//
//        assertThat(result.getResponse().getContentAsString()).isNotEmpty();
//    }
//
//    @Test
//    void rootWhenUnauthenticatedThen401() throws Exception {
//        this.mvc.perform(get("/")).andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    public void rootWithBasicStatusIsUnauthorized() throws Exception {
//        this.mvc.perform(get("/").with(httpBasic("dvega", "password"))).andExpect(status().isUnauthorized());
//    }

//    @Test
//    @WithMockUser
//    public void rootWithMockUserStatusIsOK() throws Exception {
//        this.mvc.perform(get("/")).andExpect(status().isOk());
//    }

    @DynamicPropertySource
    public static void setDatasourceProperties(final DynamicPropertyRegistry registry) {
        postgreSQLContainer.start();
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);

        redisContainer.start();
        registry.add("redis.redisson.testing", () -> true);
        registry.add("redis.redisson.exposed-test-port", () -> redisContainer.getMappedPort(6379));
        registry.add("application.integration.redisson.url", () -> redisContainer.getRedisURI());
    }

    @Container
    private static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("postgresql")
            .withUsername("postgres")
            .withPassword("postgres")
            .withStartupTimeout(Duration.ofSeconds(180));

    @Container
    public static RedisContainer redisContainer =
            new RedisContainer(DockerImageName.parse("bitnami/redis-cluster:7.0.10"))
                    .withEnv(Map.of(
                            "REDIS_PASSWORD", "redis_admin",
                            "REDISCLI_AUTH", "redis_admin",
                            "REDIS_CLUSTER_REPLICAS", "0",
                            "REDIS_NODES", "127.0.0.1",
                            "REDIS_CLUSTER_CREATOR", "yes",
                            "REDIS_CLUSTER_DYNAMIC_IPS", "no",
                            "REDIS_CLUSTER_ANNOUNCE_IP", "127.0.0.1",
                            "TESTCONTAINERS_HOST_OVERRIDE", "localhost"))
                    .withStartupTimeout(Duration.ofSeconds(10));
}