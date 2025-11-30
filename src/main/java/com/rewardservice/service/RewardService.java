package com.rewardservice.service;

import com.rewardservice.service.model.RewardPointsResponse;
import jakarta.validation.constraints.NotEmpty;

public interface RewardService {

    RewardPointsResponse calculateRewardPoints(@NotEmpty String customerId);
}
