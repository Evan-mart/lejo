package com.evan.lejo.api.security;

import com.evan.lejo.api.environment.Environment;
import com.evan.lejo.api.environment.Variable;
import com.evan.lejo.api.security.exception.JwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/**
 * @author Romain Lavabre <romainlavabre98@gmail.com>
 */
@Service
public class UserAccessResolverImpl implements com.evan.lejo.api.security.UserAccessResolver {

    protected final Environment environment;


    public UserAccessResolverImpl( Environment environment ) {
        this.environment = environment;
    }


    @Override
    public User getUser( String token ) throws JwtException {

        Jws< Claims > claims = signAndGetClaims( token );

        
        User user = new User();

        if ( claims.getBody().get( "username" ) != null ) {
            user.setUsername( claims.getBody().get( "username" ).toString() );
        }

        Map< String, List< String > > roles = claims.getBody().get( "realm_access", Map.class );

        for ( String role : roles.get( "roles" ) ) {
            user.addRole( role );
        }

        return user;
    }


    private Jws< Claims > signAndGetClaims( String token ) throws JwtException {

        try {

            X509EncodedKeySpec keySpec =
                    new X509EncodedKeySpec( Base64.getDecoder().decode( environment.getEnv( Variable.SECURITY_PUBLIC_KEY ) ) );

            KeyFactory fact = KeyFactory.getInstance( "RSA" );

            PublicKey pubKey = fact.generatePublic( keySpec );


            return Jwts.parser()
                       .setSigningKey( pubKey )
                       .parseClaimsJws( token );
        } catch ( Throwable th ) {
            throw new JwtException( th.getMessage() );
        }
    }
}
