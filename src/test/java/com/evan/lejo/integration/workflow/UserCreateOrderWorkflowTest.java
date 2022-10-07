package com.evan.lejo.integration.workflow;

import com.evan.lejo.configuration.security.AuthRole;
import com.evan.lejo.configuration.security.User;
import com.evan.lejo.configuration.security.UserAccessResolver;
import com.evan.lejo.configuration.security.jwt.JwtUtils;
import com.evan.lejo.mock.MockAccount;
import com.evan.lejo.repository.AccountRepository;
import com.evan.lejo.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.ZonedDateTime;
import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@AutoConfigureWebTestClient
@ActiveProfiles( "test" )
@TestPropertySource( locations = "classpath:application-test.properties" )
@DirtiesContext( classMode = DirtiesContext.ClassMode.BEFORE_CLASS )
public class UserCreateOrderWorkflowTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @MockBean
    private UserAccessResolver userAccessResolver;

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private OrderRepository orderRepository;


    @BeforeEach
    public void setup() {
        User user = new User();
        user.addRole( AuthRole.ROLE_USER );

        Mockito.when( userAccessResolver.getUser( Mockito.anyString() ) )
               .thenReturn( user );

        initMock();
    }


    @Test
    @DisplayName( "As a user, I should create an order" )
    public void EntryPoint() {

        createOrder();

        Map< String, Object > order = getOrder();

        Assertions.assertEquals( 1L, order.get( "account_id" ) );
    }


    private void createOrder() {
        webTestClient
                .post()
                .uri( "/lejo/users/orders" )
                //.header( "Authorization", "Bearer mocked" )
                .header( "Authorization", "Bearer mocked" )
                .bodyValue( Map.of(
                        "account_id", 1,
                        "status", 0,
                        "created_at", ZonedDateTime.now().toString()
                ) )
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }


    private Map< String, Object > getOrder() {
        FluxExchangeResult< Map > result =
                webTestClient
                        .get()
                        .uri( "/lejo/users/accounts/1/orders" )
                        .header( "Authorization", "Bearer mocked" )
                        .exchange()
                        .returnResult( Map.class );

        return result.getResponseBody().blockFirst();
    }


    private void initMock() {
        Mockito.when( accountRepository.findOrFail( Mockito.anyLong() ) )
               .thenReturn( MockAccount.build( Map.of(
                       "id", 1L,
                       "username", "toto"
               ) ) );
    }
}
