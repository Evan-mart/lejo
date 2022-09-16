package com.evan.lejo.api.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Service
@Scope( value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.INTERFACES )
public class RequestImpl implements Request {

    private final HttpServletRequest    request;
    private final Map< String, Object > parameters;
    private final Map< String, String > queryStrings;
    private       String                body;


    public RequestImpl() throws JsonProcessingException {
        this.parameters   = new HashMap<>();
        this.queryStrings = new HashMap<>();
        this.request      = (( ServletRequestAttributes ) RequestContextHolder.getRequestAttributes()).getRequest();
        this.parseJson();
    }


    @Override
    public Object getParameter( final String name ) {
        assert name != null && !name.isBlank() : "variable name should not be null or blank";

        return this.parameters.get( name );
    }


    @Override
    public void setParameter( final String name, final Object value ) {
        assert name != null && !name.isBlank() : "variable name should not be null or blank";

        this.parameters.put( name, value );
    }


    @Override
    public Integer getPort() {
        return this.request.getServerPort();
    }


    @Override
    public String getUri() {
        return this.request.getRequestURI();
    }


    private void parseJson() throws JsonProcessingException {

        if ( this.request.getContentType() == null
                || !this.request.getContentType().contains( "application/json" ) ) {
            return;
        }

        try {
            body = StreamUtils.copyToString( request.getInputStream(), StandardCharsets.UTF_8 );
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        if ( body == null ) {

            final StringBuffer json = new StringBuffer();


            String         line   = null;
            BufferedReader reader = null;

            try {
                reader = this.request.getReader();

                while ( (line = reader.readLine()) != null ) {
                    json.append( line );
                }
            } catch ( final IOException e ) {
                e.printStackTrace();
            }

            final String jsonStr = json.toString();

            this.body = jsonStr;

            if ( jsonStr.isBlank() ) {
                return;
            }
        }

        final ObjectMapper objectMapper = new ObjectMapper();

        final Map< String, Object > map = objectMapper.readValue( body, HashMap.class );

        for ( final Map.Entry< String, Object > input : map.entrySet() ) {

            if ( input.getValue() instanceof Map ) {
                final Map< String, Object > secondLevel = ( Map< String, Object > ) input.getValue();

                for ( final Map.Entry< String, Object > content : secondLevel.entrySet() ) {
                    this.parameters.put( input.getKey() + "_" + content.getKey(), content.getValue() );
                }

                continue;
            }

            if ( input.getValue() instanceof List ) {

                for ( final Map< String, Object > thirdLevel : ( List< Map< String, Object > > ) input.getValue() ) {
                    for ( final Map.Entry< String, Object > content : thirdLevel.entrySet() ) {
                        final String key = input.getKey() + "_" + content.getKey();

                        if ( this.parameters.containsKey( key ) ) {
                            final List< Object > list = ( List< Object > ) this.parameters.get( key );
                            list.add( content.getValue() );
                        } else {
                            final List< Object > list = new ArrayList<>();
                            list.add( content.getValue() );

                            this.parameters.put( key, list );
                        }
                    }
                }

                continue;
            }

            parameters.put( input.getKey(), input.getValue() );
        }
    }
}
