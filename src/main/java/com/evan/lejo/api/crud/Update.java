package com.evan.lejo.api.crud;

import com.evan.lejo.api.request.Request;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public interface Update< E > {
    void update( Request request, E entity );
}
