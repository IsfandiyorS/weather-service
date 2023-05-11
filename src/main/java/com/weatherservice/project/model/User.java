package com.weatherservice.project.model;

import com.weatherservice.project.type.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
