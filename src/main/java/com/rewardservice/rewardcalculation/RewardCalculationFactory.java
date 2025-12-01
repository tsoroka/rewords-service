package com.rewardservice.rewardcalculation;

import com.rewardservice.dto.CasinoDto;
import com.rewardservice.dto.HotelDto;
import com.rewardservice.dto.RestaurantDto;
import com.rewardservice.rewardcalculation.impl.BaseRewordCalculationStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RewardCalculationFactory {

    public RewordCalculationStrategy createStrategy(List<RestaurantDto> bookedRestaurants,
                                                    List<CasinoDto> casinos,
                                                    List<HotelDto> bookedHotels) {
        return new BaseRewordCalculationStrategy(bookedRestaurants, casinos, bookedHotels);
    }
}
