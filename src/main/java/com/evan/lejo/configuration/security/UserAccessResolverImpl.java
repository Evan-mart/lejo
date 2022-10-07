package com.evan.lejo.configuration.security;

import com.evan.lejo.api.environment.Environment;
import com.evan.lejo.configuration.security.exception.JwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Romain Lavabre <romainlavabre98@gmail.com>
 */
@Service
public class UserAccessResolverImpl implements com.evan.lejo.configuration.security.UserAccessResolver {

    @Value( "${evan.app.jwtSecret}" )
    private String jwtSecret;


    protected final Environment environment;


    public UserAccessResolverImpl( Environment environment ) {
        this.environment = environment;
    }


    @Override
    public User getUser( String token ) throws JwtException {

        Jws< Claims > claims = signAndGetClaims( token );

        User user = new User();

        if ( claims.getBody().get( "user_id" ) != null ) {
            user.setId( Long.valueOf( claims.getBody().get( "user_id" ).toString() ) );
        }

        if ( claims.getBody().get( "sub" ) != null ) {
            user.setUsername( claims.getBody().get( "sub" ).toString() );
        }


        List< Object > roles = ( List< Object > ) claims.getBody().get( "roles" );

        for ( Object role : roles ) {
            user.addRole( role.toString() );
        }

        return user;
    }


    private Jws< Claims > signAndGetClaims( String token ) throws JwtException {

        try {


            return Jwts.parser()
                       .setSigningKey( jwtSecret )
                       .parseClaimsJws( token );
        } catch ( Throwable th ) {
            System.out.println( "errrooooooooooooooooooooooooooor" );
            System.out.println( th.getMessage() );
            throw new JwtException( th.getMessage() );
        }
    }
}
