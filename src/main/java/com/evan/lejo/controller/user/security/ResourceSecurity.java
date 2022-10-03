package com.evan.lejo.controller.user.security;

import com.evan.lejo.configuration.security.Security;
import com.evan.lejo.configuration.security.response.Message;
import com.evan.lejo.entity.Account;
import com.evan.lejo.entity.Order;
import com.evan.lejo.exception.HttpNotFoundException;
import com.evan.lejo.repository.AccountRepository;
import org.springframework.stereotype.Service;

/**
 * @author Romain Lavabre <romainlavabre98@gmail.com>
 */
@Service( "UserResourceSecurity" )
public class ResourceSecurity {

    private static  ResourceSecurity  instance;
    protected final Security          security;
    protected final AccountRepository accountRepository;


    public ResourceSecurity(
            Security security,
            AccountRepository accountRepository
    ) {
        this.security          = security;
        this.accountRepository = accountRepository;
        instance               = this;
    }


/*    public static void assertAccessAllowed( RequestedDocument requestedDocument ) {
        assertAccessAllowed( requestedDocument.getIntervention().getOrder() );
    }


    public static void assertAccessAllowed( OrderDocument orderDocument ) {
        assertAccessAllowed( orderDocument.getOrder() );
    }


    public static void assertAccessAllowed( InterventionDocument interventionDocument ) {
        assertAccessAllowed( interventionDocument.getIntervention().getOrder() );
    }*/


/*    public static void assertAccessAllowed( Order order ) {
        assertAccessAllowed( order.getAccount() );
    }*/


/*    public static void assertAccessAllowed( OrderAddress orderAddress ) {
        assertAccessAllowed( orderAddress.getOrder() );
    }


    public static void assertAccessAllowed( QualityInspection qualityInspection ) {
        assertAccessAllowed( qualityInspection.getIntervention().getOrder() );
    }


    public static void assertAccessAllowed( Client client ) {
        assertAccessAllowed( client.getOrder() );
    }


    public static void assertAccessAllowed( Quotation quotation ) {
        assertAccessAllowed( quotation.getIntervention() );
    }*/


    public static void assertAccessAllowed( Order order ) {
        instance.assertOrderEquals( order );
    }


    private void assertOrderEquals( Order order ) {
        Account account = accountRepository.findOrFail( security.getId() );

        for ( Order accountOrder : account.getOrders() ) {
            if ( accountOrder == order ) {
                return;
            }

            throw new HttpNotFoundException( Message.RESOURCE_NOT_FOUND );
        }
    }
}
