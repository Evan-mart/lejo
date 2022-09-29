package com.evan.lejo.controller.user.security;

import com.evan.lejo.configuration.security.Security;
import com.evan.lejo.configuration.security.response.Message;
import com.evan.lejo.entity.Order;
import com.evan.lejo.exception.HttpNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Romain Lavabre <romainlavabre98@gmail.com>
 */
@Service( "UserResourceSecurity" )
public class ResourceSecurity {

    private static  ResourceSecurity instance;
    protected final Security         security;
    protected final AccessResolver   accessResolver;
    protected final Cache            cache;


    public ResourceSecurity(
            Security security,
            AccessResolver accessResolver,
            Cache cache ) {
        this.security       = security;
        this.accessResolver = accessResolver;
        this.cache          = cache;
        instance            = this;
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
        if ( instance.cache.isContains( Order.class, order.getId(), instance.security.getId() ) ) {
            return;
        }

        boolean hasAccess = instance.accessResolver.hasAccess( order, instance.security.getId() );

        if ( hasAccess ) {
            instance.cache.write( Order.class, order.getId(), instance.security.getId() );
            return;
        }

        throw new HttpNotFoundException( Message.RESOURCE_NOT_FOUND );
    }
}
