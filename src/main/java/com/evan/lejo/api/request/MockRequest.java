package com.evan.lejo.api.request;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Romain Lavabre <romainlavabre98@gmail.com>
 */
public class MockRequest implements Request {


    protected final Map< String, Object > parameters;


    public MockRequest() {
        this.parameters = new HashMap<>();
    }


    @Override
    public Object getParameter( final String name ) {
        return this.parameters.get( name );
    }


    @Override
    public void setParameter( final String name, final Object value ) {
        this.parameters.put( name, value );
    }


    @Override
    public Integer getPort() {
        return 8085;
    }


    @Override
    public String getUri() {
        return "/api";
    }


    public static Request build( final Map< String, Object > parameters ) {

        final Request request = new MockRequest();

        parameters.forEach( request::setParameter );

        return request;
    }


    public static Request build() {
        return new MockRequest();
    }
}
