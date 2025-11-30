package com.rewardservice.dto;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class RewardPointsDataDto {

    private BigDecimal rewardPointsForBookedHotels;

    private BigDecimal rewardPointsForVisitedRestaurants;

    private BigDecimal rewardPointsForVisitedCasinos;
}
