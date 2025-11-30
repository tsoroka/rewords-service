package com.rewardservice.adapter;

import com.rewardservice.client.CasinoClient;
import com.rewardservice.client.model.Casino;
import com.rewardservice.dto.CasinoDto;
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
public class CasinoAdapter {

    private final CasinoClient casinoClient;

    @Cacheable(value = "casinos", key = "#customerId")
    public List<CasinoDto> getVisitedCasinos(String customerId) {
        List<Casino> visitedCasinos = casinoClient.getVisitedCasinos(customerId, LocalDate.now());

        return visitedCasinos.stream()
                .map(casino -> new CasinoDto(casino.id(), casino.moneySpent()))
                .toList();
    }

    @CacheEvict(value = "casinos", key = "#customerId")
    public void evictCasinoCache(String customerId) {
        // This method will clear the cache for "casinos"
        log.info("Evicted casino cache for customerId: {}", customerId);
    }
}
