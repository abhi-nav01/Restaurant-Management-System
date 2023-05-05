package com.thespicetable.restaurant.repository;

import com.thespicetable.restaurant.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
