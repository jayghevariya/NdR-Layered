package com.ndrlayered.NdRLayered.controller;

import com.ndrlayered.NdRLayered.dto.TicketRequest;
import com.ndrlayered.NdRLayered.model.Ticket;
import com.ndrlayered.NdRLayered.service.TicketService;
import com.ndrlayered.NdRLayered.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    private final VenueService venueService;

    @PostMapping("/{venueId}/bookings")
    public Ticket bookTickets(@PathVariable Long venueId, @RequestBody TicketRequest request) {
        return venueService.bookTicket(venueId, request.getNumTickets());
    }

    @DeleteMapping("/{venueId}/bookings/{bookingId}")
    public void cancelBooking(@PathVariable Long venueId, @PathVariable Long bookingId) {
        ticketService.cancelBooking(bookingId);
    }
}
