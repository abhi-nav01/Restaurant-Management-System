package com.thespicetable.restaurant.repository;

import com.thespicetable.restaurant.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    //provides basic methods required to save data in database
}
