package com.rewardservice.client;

import com.rewardservice.client.model.Casino;
import com.rewardservice.config.FeignClientConfig;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "casino-service", url = "${casino.service.url}", configuration = FeignClientConfig.class)
public interface CasinoClient {

    @GetMapping("/customers/{customerId}/visited-casinos?date={date}")
    @CircuitBreaker(name = "casinoClientCircuitBreaker", fallbackMethod = "getVisitedCasinosFallback")
    List<Casino> getVisitedCasinos(@PathVariable("customerId") String customerId,
                                   @RequestParam("date") LocalDate date);

    default List<Casino> getVisitedCasinosFallback(String customerId, LocalDate date, Throwable throwable) {
        // Return an empty list or handle fallback logic
        return List.of();
    }
}
