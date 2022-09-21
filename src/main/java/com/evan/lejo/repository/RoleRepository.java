package com.evan.lejo.repository;

import com.evan.lejo.entity.Role;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public interface RoleRepository extends DefaultRepository< Role > {
    Role findByName( String name );
}
