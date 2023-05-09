package com.weatherservice.project.repository;

import com.weatherservice.project.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

//    @Query("SELECT w FROM Weather AS w WHERE cities.id = :cityId and w.id = :weatherId")
//    Optional<Weather> findByIdAndCityId(@Param("weatherId") Long weatherId, @Param("cityId") Long cityId);

    Optional<Weather> findWeatherByCityIdAndId(final Long cityId, final Long weatherId);

    @Query(nativeQuery = true, value = "SELECT * FROM weather as w WHERE city_id IN (SELECT s.city_id from subscriptions as s where user_id = :userId)")
    List<Weather> findWeatherByUserSubscriptions(@Param("userId") Long userId);
}
