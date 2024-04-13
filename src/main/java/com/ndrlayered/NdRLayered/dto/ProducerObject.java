package com.ndrlayered.NdRLayered.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProducerObject {
    private int personInCount;
    private int personOutCount;
    private Long venueId;
}
