package com.rewardservice.service.model;

import com.rewardservice.dto.RewardPointsDataDto;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class RewardPointsResponse {

    private String customerId;

    private RewardPointsDataDto rewardPointsData;

}
