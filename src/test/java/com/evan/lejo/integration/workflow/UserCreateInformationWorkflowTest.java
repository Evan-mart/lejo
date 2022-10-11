package com.evan.lejo.integration.workflow;

import com.evan.lejo.configuration.security.AuthRole;
import com.evan.lejo.configuration.security.User;
import com.evan.lejo.configuration.security.UserAccessResolver;
import com.evan.lejo.entity.Account;
import com.evan.lejo.mock.MockAccount;
import com.evan.lejo.repository.AccountInformationRepository;
import com.evan.lejo.repository.AccountRepository;
import com.evan.lejo.util.Cast;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.ZonedDateTime;
import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class UserCreateInformationWorkflowTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UserAccessResolver userAccessResolver;

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private AccountInformationRepository accountInformationRepository;


    @BeforeEach
    public void setup() {
        User user = new User();
        user.addRole(AuthRole.ROLE_USER);

        Mockito.when(userAccessResolver.getUser(Mockito.anyString())).thenReturn(user);

        initMock();
    }


    @Test
    public void entryPoint() {
        Account account = accountRepository.findOrFail(2L);

        createInformation(account);

        Map<String, Object> accountInformation = getAccountInformation(2L);

        Assertions.assertEquals(2L, Cast.getLong(accountInformation.get("account_id")));
    }


    private void createInformation(Account account) {
        webTestClient
                .post()
                .uri("/lejo/users/accounts/" + account.getId() + "/account_informations")
                .header("Authorization", "Bearer mocked")
                .bodyValue(Map.of(
                                "mobile", "0601020304",
                                "address", "rue chez jp",
                                "city", "jpcity",
                                "post_code", "22555"
                        )
                )
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }


    private Map<String, Object> getAccountInformation(long id) {
        FluxExchangeResult<Map> result =
                webTestClient
                        .get()
                        .uri("/lejo/users/accounts/" + id + "/account_informations")
                        .header("Authorization", "Bearer mocked")
                        .exchange()
                        .returnResult(Map.class);

        return result.getResponseBody().blockFirst();
    }


    private void initMock() {
        Mockito.when(accountRepository.findOrFail(Mockito.anyLong()))
                .thenReturn(MockAccount.build(Map.of(
                        "id", 2,
                        "username", "pj",
                        "email", "jp@gmail.com",
                        "password", "0000",
                        "created_at", ZonedDateTime.now().toString(),
                        "last_connection", ZonedDateTime.now().toString(),
                        "roles", "ROLE-USER",
                        "orders", "1"
                )));
    }
}
