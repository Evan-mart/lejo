package com.evan.lejo.repository.jpa;

import com.evan.lejo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public interface CategoryJpa extends JpaRepository< Category, Long > {
}
