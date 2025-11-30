package com.rewardservice.adapter;

import com.rewardservice.client.RestaurantClient;
import com.rewardservice.client.model.Restaurant;
import com.rewardservice.dto.RestaurantDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class RestaurantAdapter {

    private final RestaurantClient restaurantClient;

    @Cacheable(value = "restaurants", key = "#customerId")
    public List<RestaurantDto> getBookedRestaurants(String customerId) {
        List<Restaurant> bookedRestaurants = restaurantClient.getBookedRestaurants(customerId, LocalDate.now());

        return bookedRestaurants.stream()
                .map(restaurant -> new RestaurantDto(restaurant.id(), restaurant.reservationsCount()))
                .toList();
    }

    @CacheEvict(value = "restaurants", key = "#customerId")
    public void evictRestaurantCache(String customerId) {
        log.info("Evicted restaurant cache for customerId: {}", customerId);
    }
}