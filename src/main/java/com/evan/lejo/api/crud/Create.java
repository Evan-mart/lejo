package com.evan.lejo.api.crud;

import com.evan.lejo.api.request.Request;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public interface Create< E > {
    void create( Request request, E entity );
}
