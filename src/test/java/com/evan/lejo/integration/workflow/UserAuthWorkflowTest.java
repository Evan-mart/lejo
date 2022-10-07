package com.evan.lejo.integration.workflow;

import com.evan.lejo.configuration.security.AuthRole;
import com.evan.lejo.configuration.security.User;
import com.evan.lejo.configuration.security.UserAccessResolver;
import com.evan.lejo.configuration.security.jwt.JwtUtils;
import com.evan.lejo.mock.MockAccount;
import com.evan.lejo.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@AutoConfigureWebTestClient
@ActiveProfiles( "test" )
@TestPropertySource( locations = "classpath:application-test.properties" )
@DirtiesContext( classMode = DirtiesContext.ClassMode.BEFORE_CLASS )
public class UserAuthWorkflowTest {

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


    @BeforeEach
    public void setup() {
        User user = new User();
        user.addRole( AuthRole.ROLE_USER );

        Mockito.when( userAccessResolver.getUser( Mockito.anyString() ) )
               .thenReturn( user );

        initMock();
    }


    @Test
    @DisplayName( "As a user I should create an account then I should signing" )
    private void EntryPoint() {
        createAccount();

        signin();

        Map< String, Object > user = getAccount();

        Assertions.assertEquals( 1L, user.get( "id" ) );
    }


    private void createAccount() {
        webTestClient
                .post()
                .uri( "/lejo/auth/register" )
                .bodyValue( Map.of(
                        "username", "toto",
                        "email", "toto@gmail.com",
                        "password", "1234"
                ) )
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }


    private void signin() {
        webTestClient
                .post()
                .uri( "/lejo/auth/signin" )
                .bodyValue( Map.of(
                        "username", "toto",
                        "password", "1234"
                ) )
                .exchange()
                .expectHeader()
                .exists( HttpHeaders.AUTHORIZATION )
                .expectStatus()
                .is2xxSuccessful();
    }


    private Map< String, Object > getAccount() {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken( "toto", "1234" )
        );

        SecurityContextHolder.getContext().setAuthentication( authentication );

        String jwt = jwtUtils.generateJwtToken( authentication );

        FluxExchangeResult< Map > result =
                webTestClient
                        .get()
                        .uri( "/lejo/users/accounts/1" )
                        .header( "Authorization", "Bearer " + jwt )
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
