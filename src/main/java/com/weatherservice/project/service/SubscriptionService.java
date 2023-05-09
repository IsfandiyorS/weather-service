package com.weatherservice.project.service;

import com.weatherservice.project.common.ResponseData;
import com.weatherservice.project.common.ResultMessage;
import com.weatherservice.project.dto.SubscriptionDto;

import java.util.List;

public interface SubscriptionService {

    ResponseData<ResultMessage> subscribeCity(final Long cityId);

    ResponseData<List<SubscriptionDto>> getAll();

    ResponseData<List<SubscriptionDto>> getByUserId(final Long userId);

}
