package com.rewardservice.listener;


import com.rewardservice.adapter.CasinoAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CasinoListener {

    private final CasinoAdapter casinoAdapter;

    @KafkaListener(topics = "casino-operation", groupId = "reward-service-group",
            containerFactory = "kafkaListenerContainerFactory")
    public void listenForCasinoOperations(@Header(KafkaHeaders.RECEIVED_KEY) String customerId) {
        if (customerId == null || customerId.isEmpty()) {
            return;
        }
        casinoAdapter.evictCasinoCache(customerId);
    }
}
