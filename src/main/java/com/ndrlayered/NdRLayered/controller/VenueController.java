package com.ndrlayered.NdRLayered.controller;

import com.ndrlayered.NdRLayered.dto.ProducerObject;
import lombok.RequiredArgsConstructor;
import com.ndrlayered.NdRLayered.dto.BookingRequestDto;
import com.ndrlayered.NdRLayered.dto.VenueAvailabilityDto;
import com.ndrlayered.NdRLayered.service.VenueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venues")
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;

    @PostMapping
    public String produce(@RequestBody ProducerObject producerObject) {
        venueService.updateVenueOccupancy(producerObject);
        return "Event produced";
    }

    @GetMapping("/{venueId}/availability")
    public VenueAvailabilityDto getVenueAvailability(@PathVariable Long venueId) {
        return venueService.getVenueAvailability(venueId);
    }

    @GetMapping("/availability")
    public List<VenueAvailabilityDto> getAllVenuesAvailability() {
        return venueService.getAllVenuesAvailability();
    }

    // book tickets
    @PostMapping("/{venueId}/bookings")
    public String bookTickets(@PathVariable Long venueId, @RequestBody BookingRequestDto request) {
        return venueService.bookTickets(venueId, request.getNumTickets());
    }
}
