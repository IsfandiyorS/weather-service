package com.weatherservice.project.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
