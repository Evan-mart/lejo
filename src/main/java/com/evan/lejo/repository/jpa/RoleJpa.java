package com.evan.lejo.repository.jpa;

import com.evan.lejo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Repository
public interface RoleJpa extends JpaRepository< Role, Long > {
    Role findByName( String name );
}
