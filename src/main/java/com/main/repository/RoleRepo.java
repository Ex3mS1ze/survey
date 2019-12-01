package com.main.repository;

import com.main.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByRolename(String rolename);
}
