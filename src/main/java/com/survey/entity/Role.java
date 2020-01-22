package com.survey.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "roles", schema = "public")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "rolename", nullable = false)
    private String rolename;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {
    }

    @Override
    public String getAuthority() {
        return getRolename();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(rolename, role.rolename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rolename);
    }

    public static boolean isRolesContainsByRolename(Set<Role> roles, String rolename) {
        for (Role role : roles) {
            if (role.getRolename().contains(rolename)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", rolename='" + rolename + '\'' + '}';
    }
}
