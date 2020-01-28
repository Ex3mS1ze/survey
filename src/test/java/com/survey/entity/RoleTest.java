package com.survey.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RoleTest {
    private Role role;
    private Set<Role> roleSet;


    @Before
    public void setUp() throws Exception {
        role = new Role();
        role.setRolename("ROLE_ADMIN");
        roleSet = new HashSet<>(Arrays.asList(role));
    }

    @Test
    public void isRolesContainsByRolename() {
        assertTrue(Role.isRolesContainsByRolename(roleSet, "ADMIN"));
        assertTrue(Role.isRolesContainsByRolename(roleSet, "admin"));
        assertFalse(Role.isRolesContainsByRolename(roleSet, ""));
        assertFalse(Role.isRolesContainsByRolename(roleSet, null));
    }
}