package com.rewardservice.listener;


import com.rewardservice.adapter.HotelAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class HotelListener {

    private final HotelAdapter hotelAdapter;

    @KafkaListener(topics = "hotel-reservations", groupId = "reward-service-group",
            containerFactory = "kafkaListenerContainerFactory")
    public void listenForHotelReservation(@Header(KafkaHeaders.RECEIVED_KEY) String customerId) {
        if (customerId == null || customerId.isEmpty()) {
            return;
        }
        hotelAdapter.evictHotelCache(customerId);
    }
}
