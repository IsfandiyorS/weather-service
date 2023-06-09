package com.weatherservice.project.service.impl;

import com.weatherservice.project.common.ResponseData;
import com.weatherservice.project.common.ResultMessage;
import com.weatherservice.project.dto.SubscriptionDto;
import com.weatherservice.project.exception.BadRequestException;
import com.weatherservice.project.exception.ObjectNotFoundException;
import com.weatherservice.project.mapper.SubscriptionMapper;
import com.weatherservice.project.model.City;
import com.weatherservice.project.model.Subscriptions;
import com.weatherservice.project.model.User;
import com.weatherservice.project.repository.CityRepository;
import com.weatherservice.project.repository.SubscriptionRepository;
import com.weatherservice.project.repository.UserRepository;
import com.weatherservice.project.security.SecurityUtil;
import com.weatherservice.project.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.weatherservice.project.common.FieldNames.CITY;
import static com.weatherservice.project.common.FieldNames.ID;
import static com.weatherservice.project.common.FieldNames.SUBSCRIPTION;
import static com.weatherservice.project.common.FieldNames.USER;
import static com.weatherservice.project.common.ResponseMessages.OBJECT_NOT_FOUND_BY_FIELD;
import static com.weatherservice.project.common.ResponseMessages.OBJECT_SUCCESSFULLY_CREATED;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final CityRepository cityRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseData<ResultMessage> subscribeCity(Long cityId) {

        Long currentUserId = SecurityUtil.getCurrentUser().get().getId();

        Optional<User> user = userRepository.findById(currentUserId);

        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new ObjectNotFoundException(format(OBJECT_NOT_FOUND_BY_FIELD, CITY, ID)));

        if (subscriptionRepository.findByUserIdAndCityId(currentUserId, city.getId()).isPresent()){
                throw  new BadRequestException("You already subscribed to this city.");
        }

        subscriptionRepository.save(new Subscriptions(user.get(), city));

        return ResponseData.<ResultMessage>builder()
                .data(
                        ResultMessage
                                .builder()
                                .message(format(OBJECT_SUCCESSFULLY_CREATED, SUBSCRIPTION))
                                .build())
                .build();
    }

    @Override
    public ResponseData<List<SubscriptionDto>> getAll() {
        return ResponseData.<List<SubscriptionDto>>builder()
                .data(
                        subscriptionRepository.findAll()
                                .stream()
                                .map(subscriptionMapper::entityToDto)
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public ResponseData<List<SubscriptionDto>> getByUserId(Long userId) {

        userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException(format(OBJECT_NOT_FOUND_BY_FIELD, USER, ID)));

        return ResponseData.<List<SubscriptionDto>>builder()
                .data(
                        subscriptionRepository.findSubscriptionsByUserId(userId)
                                .stream()
                                .map(subscriptionMapper::entityToDto)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
