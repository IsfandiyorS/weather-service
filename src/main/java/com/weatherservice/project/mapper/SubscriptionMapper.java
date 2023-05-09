package com.weatherservice.project.mapper;

import com.weatherservice.project.dto.SubscriptionDto;
import com.weatherservice.project.model.Subscriptions;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionMapper {

    public SubscriptionDto entityToDto(Subscriptions subscription) {
        return SubscriptionDto.builder()
                .cityId(subscription.getCity().getId())
                .userId(subscription.getUser().getId())
                .subscribedAt(subscription.getCreatedDate())
                .build();
    }

}
