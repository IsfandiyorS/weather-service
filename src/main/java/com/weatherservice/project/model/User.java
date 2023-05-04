package com.weatherservice.project.model;

import com.weatherservice.project.type.UserType;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends Auditable {

    private String firstname;

    private String lastname;

    @Column(unique = true)
    private String email;

    private Integer age;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(columnDefinition = "boolean default false")
    private boolean enabled;

    @Column(columnDefinition = "boolean default false")
    private boolean locked;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "auth_users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", enabled=" + enabled +
                ", locked=" + locked +
                ", roles=" + roles +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
