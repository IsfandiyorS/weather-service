package com.weatherservice.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "countries")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Country extends Auditable {

    private String name;

    private String postalCode;

    @OneToMany(mappedBy = "country")
    private List<City> cities;
}
