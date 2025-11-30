package com.rewardservice.service.impl;

import com.rewardservice.adapter.CasinoAdapter;
import com.rewardservice.adapter.HotelAdapter;
import com.rewardservice.adapter.RestaurantAdapter;
import com.rewardservice.dto.CasinoDto;
import com.rewardservice.dto.HotelDto;
import com.rewardservice.dto.RestaurantDto;
import com.rewardservice.dto.RewardPointsDataDto;
import com.rewardservice.rewardcalculation.RewordCalculationStrategy;
import com.rewardservice.rewardcalculation.impl.BaseRewordCalculationStrategy;
import com.rewardservice.service.RewardService;
import com.rewardservice.service.model.exception.DataFetchException;
import com.rewardservice.service.model.RewardPointsResponse;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Validated
@RequiredArgsConstructor
@Slf4j
public class RewardServiceImpl implements RewardService {

    private final RestaurantAdapter restaurantAdapter;

    private final HotelAdapter hotelAdapter;

    private final CasinoAdapter casinoAdapter;

    @Override
    public RewardPointsResponse calculateRewardPoints(@NotEmpty String customerId) {
        CompletableFuture<List<RestaurantDto>> bookedRestaurantsFuture = CompletableFuture.supplyAsync(()
                -> restaurantAdapter.getBookedRestaurants(customerId));
        CompletableFuture<List<HotelDto>> bookedHotelsFuture = CompletableFuture.supplyAsync(()
                -> hotelAdapter.getHotelReservations(customerId));
        CompletableFuture<List<CasinoDto>> casinosFuture = CompletableFuture.supplyAsync(()
                -> casinoAdapter.getVisitedCasinos(customerId));

        // Wait for all futures to complete
        CompletableFuture.allOf(bookedRestaurantsFuture, bookedHotelsFuture, casinosFuture).join();

        try {
            // Collect results
            List<RestaurantDto> bookedRestaurants = bookedRestaurantsFuture.get();
            List<HotelDto> bookedHotels = bookedHotelsFuture.get();
            List<CasinoDto> casinos = casinosFuture.get();

            log.info("Fetched {} restaurants, {} hotels, and {} casinos for customerId: {}",
                    bookedRestaurants.size(), bookedHotels.size(), casinos.size(), customerId);

            // Calculate total reward points
            RewordCalculationStrategy calculationStrategy =
                    new BaseRewordCalculationStrategy(bookedRestaurants, casinos, bookedHotels);
            RewardPointsDataDto rewardPointsData = calculationStrategy.calculateRewordsPoints();

            return new RewardPointsResponse(customerId, rewardPointsData);
        } catch (InterruptedException | ExecutionException e) {
            throw new DataFetchException(e.getMessage(), e);
        }
    }
}
