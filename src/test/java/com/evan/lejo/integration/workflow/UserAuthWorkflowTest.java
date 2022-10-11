package com.evan.lejo.integration.workflow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class UserAuthWorkflowTest {

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    public void setup() {

        initMock();
    }


    @Test
    @DisplayName("As a user I should create an account then I should signing")
    public void EntryPoint() {
        createAccount();

        login();
    }


    private void createAccount() {
        webTestClient
                .post()
                .uri("/lejo/auth/register")
                .bodyValue(Map.of(
                        "username", "toto",
                        "email", "toto@gmail.com",
                        "password", "1234"
                ))
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }


    private void login() {

        webTestClient
                .post()
                .uri("/lejo/auth/signin")
                .bodyValue(Map.of(
                        "username", "toto",
                        "password", "1234"
                ))
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }


    private Map<String, Object> getAccount(long id) {

        FluxExchangeResult<Map> result =
                webTestClient
                        .get()
                        .uri("/lejo/users/accounts/" + id)
                        .exchange()
                        .expectStatus().is2xxSuccessful()
                        .returnResult(Map.class);

        return result.getResponseBody().blockFirst();
    }


    private void initMock() {
    }
}
