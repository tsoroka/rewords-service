package com.rewardservice.client.model;

/**
 * Simple DTO for Hotel returned by remote services.
 */
public record Hotel(
        String id,
        String name,
        String address,
        String phone,
        Integer nightsStayed
) {
}

