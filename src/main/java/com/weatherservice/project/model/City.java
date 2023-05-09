package com.weatherservice.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
