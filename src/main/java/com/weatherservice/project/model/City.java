package com.weatherservice.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "cities")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class City extends Auditable {

    private String name;

    private String postalCode;

    @ManyToOne
    private Country country;

    @OneToMany(mappedBy = "city")
    private List<Weather> weatherList;

}
