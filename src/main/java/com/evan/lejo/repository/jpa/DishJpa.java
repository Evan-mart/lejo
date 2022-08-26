package com.evan.lejo.repository.jpa;

import com.evan.lejo.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public interface DishJpa extends JpaRepository< Dish, Long > {
}
