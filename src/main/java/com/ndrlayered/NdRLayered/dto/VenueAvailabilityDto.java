package com.ndrlayered.NdRLayered.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueAvailabilityDto {

    private Long venueId;
    private int availableSeats;
    private int totalCapacity;

}
