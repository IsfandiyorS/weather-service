package com.weatherservice.project.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subscriptions extends Auditable{

    @ManyToOne
    private User user;

    @ManyToOne
    private City city;

    private LocalDateTime subscribedAt;
}
