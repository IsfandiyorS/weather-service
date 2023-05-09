package com.weatherservice.project.repository;

import com.weatherservice.project.model.Subscriptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscriptions, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM subscriptions as s WHERE s.user_id = :userId")
    List<Subscriptions> findSubscriptionsByUserId(@Param("userId") Long userId);

    @Query(nativeQuery = true, value = "SELECT * FROM subscriptions as s WHERE user_id = :userId and city_id = :cityId")
    Optional<Subscriptions> findByUserIdAndCityId(@Param("userId") Long userId, @Param("cityId") Long cityId);

}
