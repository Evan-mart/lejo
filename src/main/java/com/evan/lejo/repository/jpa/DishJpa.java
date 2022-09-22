package com.evan.lejo.repository.jpa;

import com.evan.lejo.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Repository
public interface DishJpa extends JpaRepository< Dish, Long > {
    List< Dish > findAll();
}
