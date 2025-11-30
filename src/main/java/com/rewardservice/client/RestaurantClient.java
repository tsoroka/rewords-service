package com.rewardservice.client;

import com.rewardservice.client.model.Restaurant;
import com.rewardservice.config.FeignClientConfig;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "restaurant-service", url = "${restaurant.service.url}", configuration = FeignClientConfig.class)
public interface RestaurantClient {

    @GetMapping("/customers/{customerId}/restaurants?status=booked&date={date}")
    @CircuitBreaker(name = "casinoClientCircuitBreaker", fallbackMethod = "getBookedRestaurantsFallback")
    List<Restaurant> getBookedRestaurants(@PathVariable("customerId") String customerId,
                                          @RequestParam("date") LocalDate date);

    default List<Restaurant> getBookedRestaurantsFallback(String customerId, LocalDate date, Throwable throwable) {
        // Return an empty list or handle fallback logic
        return List.of();
    }
}
