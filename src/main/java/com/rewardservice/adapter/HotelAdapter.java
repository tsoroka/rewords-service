package com.rewardservice.adapter;

import com.rewardservice.client.HotelClient;
import com.rewardservice.client.model.Hotel;
import com.rewardservice.dto.HotelDto;
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
public class HotelAdapter {

    private final HotelClient hotelClient;

    @Cacheable(value = "hotels", key = "#customerId")
    public List<HotelDto> getHotelReservations(String customerId) {
        List<Hotel> bookedHotels = hotelClient.getBookedHotels(customerId, LocalDate.now());

        return bookedHotels.stream()
                .map(hotel -> new HotelDto(hotel.id(), hotel.nightsStayed()))
                .toList();
    }

    @CacheEvict(value = "hotels", key = "#customerId")
    public void evictHotelCache(String customerId) {
        log.info("Evicted hotel cache for customerId: {}", customerId);
    }
}