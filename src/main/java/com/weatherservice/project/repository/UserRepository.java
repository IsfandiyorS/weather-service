package com.weatherservice.project.repository;

import com.weatherservice.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(final String email);

    void deleteById(final Long userId);

    void deleteByEmail(final String email);

}
