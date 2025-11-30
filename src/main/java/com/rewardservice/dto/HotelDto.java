package com.rewardservice.dto;

/**
 * Simple DTO for Hotel returned by remote services.
 */
public record HotelDto(
        String id,
        Integer nightsStayed
) {
}

