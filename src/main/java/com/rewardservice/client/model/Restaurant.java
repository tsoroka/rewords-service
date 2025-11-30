package com.rewardservice.client.model;

/**
 * Simple DTO for Restaurant returned by remote services.
 */
public record Restaurant(
        String id,
        String name,
        String address,
        String phone,
        Integer reservationsCount
) {
}

