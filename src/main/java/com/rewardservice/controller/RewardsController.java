package com.rewardservice.controller;

import com.rewardservice.service.RewardService;
import com.rewardservice.service.model.RewardPointsResponse;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RewardsController {

    private final RewardService rewardService;

    @GetMapping("/customers/{customerId}/reword-points")
    public RewardPointsResponse getRewardPoints(@NotEmpty @PathVariable("customerId") String customerId) {
        return rewardService.calculateRewardPoints(customerId);
    }
}
