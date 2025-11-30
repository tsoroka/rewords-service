package com.rewardservice.dto;

/**
 * Simple DTO for Restaurant returned by remote services.
 */
public record RestaurantDto(
        String id,
        Integer reservationsCount
) {
}

