package com.evan.lejo.api.request;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public interface Request {

    /**
     * @param name Parameter name
     * @return Body parameter
     */
    Object getParameter( String name );


    /**
     * Add or overwrite parameter
     *
     * @param name  Parameter name
     * @param value Parameter value
     */
    void setParameter( String name, Object value );

    Integer getPort();

    /**
     * @return Only uri <code>/path</code>
     */
    String getUri();
}
