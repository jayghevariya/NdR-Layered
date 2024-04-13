package com.ndrlayered.NdRLayered.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumeObject {

        private int personInCount;
        private int personOutCount;
        private Long venueId;
}
