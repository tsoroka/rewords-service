package com.rewardservice.client;

import com.rewardservice.client.model.Hotel;
import com.rewardservice.config.FeignClientConfig;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "hotel-service", url = "${hotel.service.url}", configuration = FeignClientConfig.class)
public interface HotelClient {

    @GetMapping("/customers/{customerId}/hotels?status=booked&date={date}")
    @CircuitBreaker(name = "casinoClientCircuitBreaker", fallbackMethod = "getBookedHotelsFallback")
    List<Hotel> getBookedHotels(@PathVariable("customerId") String customerId,
                                @RequestParam("date") LocalDate date);

    default List<Hotel> getBookedHotelsFallback(String customerId, LocalDate date, Throwable throwable) {
        // Return an empty list or handle fallback logic
        return List.of();
    }
}
