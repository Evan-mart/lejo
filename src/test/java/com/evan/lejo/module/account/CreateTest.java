package com.evan.lejo.module.account;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public class CreateTest {

/*    private Create< Account > getService() {
        AccountRepository accountRepository = Mockito.mock( AccountRepository.class );

        return new com.evan.lejo.module.account.Create(
                accountRepository
        );
    }


    @Test
    public void test_success() {
        for ( Request request : dpSuccess() ) {
            Account account = new Account();

            getService().create( request, account );

            Assertions.assertNotNull( request.getParameter( AccountParameter.USERNAME ), account.getUsername() );
            Assertions.assertNotNull( request.getParameter( AccountParameter.PASSWORD ), account.getPassword() );
        }
    }


    @Test
    public void test_fail() {
        for ( Request request : dpFail() ) {
            Assertions.assertThrows(
                    RuntimeException.class,
                    () -> getService().create( request, new Account() )
            );
        }
    }


    private List< Request > dpSuccess() {
        return List.of(
                MockRequest.build( Map.of(
                        AccountParameter.USERNAME, "jo",
                        AccountParameter.PASSWORD, "kfnvso"
                ) )
        );
    }


    private List< Request > dpFail() {
        return List.of(
                MockRequest.build( Map.of(
                        AccountParameter.USERNAME, " "
                ) ),
                MockRequest.build( Map.of(
                        AccountParameter.PASSWORD, ""
                ) )
        );
    }*/
}
