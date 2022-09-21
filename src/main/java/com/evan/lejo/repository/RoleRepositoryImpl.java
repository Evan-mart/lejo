package com.evan.lejo.repository;

import com.evan.lejo.entity.Role;
import com.evan.lejo.repository.jpa.RoleJpa;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Service
public class RoleRepositoryImpl extends AbstractRepository< Role > implements RoleRepository {
    protected final RoleJpa roleJpa;


    public RoleRepositoryImpl(
            EntityManager entityManager,
            RoleJpa roleJpa ) {
        super( entityManager, roleJpa );
        this.roleJpa = roleJpa;
    }


    @Override
    protected Class< Role > getClassType() {
        return Role.class;
    }


    @Override
    public Role findByName( String name ) {
        return roleJpa.findByName( name );
    }
}
