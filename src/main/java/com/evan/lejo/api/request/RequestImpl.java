package com.evan.lejo.api.request;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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


    public RequestImpl() {
        this.parameters   = new HashMap<>();
        this.queryStrings = new HashMap<>();
        this.request      = (( ServletRequestAttributes ) RequestContextHolder.getRequestAttributes()).getRequest();
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
}
