package com.rewardservice.rewardcalculation.impl;

import com.rewardservice.dto.CasinoDto;
import com.rewardservice.dto.HotelDto;
import com.rewardservice.dto.RestaurantDto;
import com.rewardservice.dto.RewardPointsDataDto;
import com.rewardservice.rewardcalculation.RewordCalculationStrategy;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
public class BaseRewordCalculationStrategy implements RewordCalculationStrategy {

    private final List<RestaurantDto> bookedRestaurants;

    private final List<CasinoDto> casinos;

    private final List<HotelDto> bookedHotels;

    @Override
    public RewardPointsDataDto calculateRewordsPoints() {
        RewardPointsDataDto rewardPointsData = new RewardPointsDataDto();
        BigDecimal totalRewardPoints = BigDecimal.ZERO;

        // Here you would implement the logic to calculate reward points based on the fetched data
        for (RestaurantDto bookedRestaurant : bookedRestaurants) {
            Integer reservationsCount = bookedRestaurant.reservationsCount();
            if (reservationsCount != null) {
                totalRewardPoints = totalRewardPoints.add(BigDecimal.valueOf(reservationsCount * 10));
            }
        }
        rewardPointsData.setRewardPointsForVisitedRestaurants(totalRewardPoints);

        totalRewardPoints = BigDecimal.ZERO;
        for (HotelDto bookedHotel : bookedHotels) {
            Integer nightsStayed = bookedHotel.nightsStayed();
            if( nightsStayed != null) {
                totalRewardPoints = totalRewardPoints.add(BigDecimal.valueOf(nightsStayed * 20));
            }
        }
        rewardPointsData.setRewardPointsForBookedHotels(totalRewardPoints);

        totalRewardPoints = BigDecimal.ZERO;
        for (CasinoDto casino : casinos) {
            BigDecimal moneySpent = casino.moneySpent();
            if (moneySpent != null) {
                totalRewardPoints = totalRewardPoints.add(moneySpent.multiply(BigDecimal.valueOf(0.1)));
            }
        }
        rewardPointsData.setRewardPointsForVisitedCasinos(totalRewardPoints);
        return rewardPointsData;
    }
}
