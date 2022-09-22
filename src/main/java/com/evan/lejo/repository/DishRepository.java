package com.evan.lejo.repository;

import com.evan.lejo.entity.Dish;

import java.util.List;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public interface DishRepository extends DefaultRepository< Dish > {
    List< Dish > findAll();
}
