package com.weatherservice.project.model;

import lombok.*;

import javax.persistence.*;
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

    @Column(columnDefinition = "boolean default false")
    private boolean visible;

    @ManyToOne
    private Country country;

    @OneToMany(mappedBy = "city")
    private List<Weather> weatherList;

}
