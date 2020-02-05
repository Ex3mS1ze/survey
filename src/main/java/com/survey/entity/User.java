package com.survey.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@Entity
@Table(name = "users", schema = "public")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "email", nullable = false)
    @Email(message = "Введите email")
    private String email;
    @Column(name = "password", nullable = false)
    @Length(min = 2, message = "Минимальная длина пароля 2")
    private String password;
    @Transient
    @Length(min = 2, message = "Минимальная длина пароля 2")
    private String passwordConfirm;
    @Transient
    @Length(min = 2, message = "Минимальная длина пароля 2")
    private String oldPassword;
    @Column(name = "first_name", nullable = false)
    @NotEmpty(message = "Введите имя")
    @Length(min = 2, message = "Введите имя")
    private String firstName;
    @Column(name = "second_name", nullable = false)
    @Length(min = 2, message = "Введите фамилию")
    private String secondName;
    @Column(name = "activated", nullable = false)
    private Boolean activated;
    @Column(name = "phone_number", nullable = true)
    @Length(min = 8, message = "Минимальная длина номера 8")
    private String phoneNumber;
    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate;
    @Column(name = "gender", nullable = false)
    @NotEmpty(message = "Выберите пол")
    private String gender;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @OneToMany(mappedBy = "user")
    private List<Questionnaire> questionnaires;
    @Column(name = "last_visit_date", nullable = false)
    private LocalDateTime lastVisitDate;

    public User() {
    }

    public String getUsername() {
        return getEmail();
    }

    public boolean checkMandatoryFields() {
        if (this.firstName == null || this.firstName.isEmpty() || this.secondName == null ||
            this.secondName.isEmpty() || this.email == null || this.email.isEmpty() || this.password == null ||
            this.password.isEmpty() || this.registrationDate == null || this.gender == null || this.gender.isEmpty() ||
            this.phoneNumber == null || this.phoneNumber.isEmpty() || this.roles == null || this.roles.isEmpty()) {
            return false;
        }
        return true;
    }

    public String getFullName() {
        return this.firstName + ' ' + this.secondName;
    }

    public boolean isAdmin() {
        long role = roles.stream().map(Role::getRolename).filter(s -> s.equals("ROLE_ADMIN")).count();
        return role > 0;
    }

    public boolean isDoctor() {
        long role = roles.stream().map(Role::getRolename).filter(s -> s.equals("ROLE_DOCTOR")).count();
        return role > 0;
    }

    public boolean isPatient() {
        long role = roles.stream().map(Role::getRolename).filter(s -> s.equals("ROLE_PATIENT")).count();
        return role > 0;
    }

    public Set<String> getRolesNames() {
        return this.roles.stream().map(Role::getRolename).collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return activated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) &&
               Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) &&
               Objects.equals(secondName, user.secondName) && Objects.equals(registrationDate, user.registrationDate) &&
               Objects.equals(gender, user.gender) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, firstName, secondName, registrationDate, gender, roles);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email='" + email + '\'' + ", firstName='" + firstName + '\'' +
               ", secondName='" + secondName + '\'' + ", activated=" + activated + ", gender='" + gender + '\'' +
               ", roles=" + roles + '}';
    }

}
