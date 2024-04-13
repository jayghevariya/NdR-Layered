package com.ndrlayered.NdRLayered.service;

import com.ndrlayered.NdRLayered.dto.ProducerObject;
import com.ndrlayered.NdRLayered.model.Ticket;
import com.ndrlayered.NdRLayered.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import com.ndrlayered.NdRLayered.dto.VenueAvailabilityDto;
import com.ndrlayered.NdRLayered.model.Venue;
import com.ndrlayered.NdRLayered.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;

    private final TicketRepository bookingRepository;

    public Ticket bookTicket(Long venueId, int numTickets) {
        VenueAvailabilityDto venueAvailabilityDto = getVenueAvailability(venueId);

        if (venueAvailabilityDto.getAvailableSeats() < numTickets) {
            throw new RuntimeException("Not enough tickets available");
        }

        String booking = bookTickets(venueId, numTickets);

        if (booking == null) {
            throw new RuntimeException("Booking failed");
        }

        Ticket ticket = new Ticket();
        ticket.setVenueId(venueId);
        ticket.setNumTickets(numTickets);

        return bookingRepository.save(ticket);
    }

    public void updateVenueOccupancy(ProducerObject message) {

        Venue venue = venueRepository.findById(message.getVenueId())
                .orElseThrow(() -> new RuntimeException("Venue not found"));

        int netCount = message.getPersonInCount() - message.getPersonOutCount();

        if (venue.getCurrentOccupancy() + netCount < 0 || venue.getCurrentOccupancy() + netCount > venue.getCapacity()) {
            throw new RuntimeException("Invalid occupancy count");
        }

        venue.setCurrentOccupancy(venue.getCurrentOccupancy() + netCount);

        venueRepository.save(venue);
    }

    public VenueAvailabilityDto getVenueAvailability(Long venueId) {
        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new RuntimeException("Venue not found"));

        int totalCapacity = venue.getCapacity();
        int currentOccupancy = venue.getCurrentOccupancy();

        int availableSeats = totalCapacity - currentOccupancy;

        return new VenueAvailabilityDto(venueId, availableSeats, totalCapacity);
    }

    public List<VenueAvailabilityDto> getAllVenuesAvailability() {
        List<Venue> venues = venueRepository.findAll();
        return venues.stream()
                .map(venue -> new VenueAvailabilityDto(venue.getId(), venue.getCapacity() - venue.getCurrentOccupancy(), venue.getCapacity()))
                .collect(Collectors.toList());
    }

    public String bookTickets(Long venueId, int numTickets) {
        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new RuntimeException("Venue not found"));

        int availableSeats = venue.getCapacity() - venue.getCurrentOccupancy();
        if (availableSeats < numTickets) {
            throw new RuntimeException("Not enough tickets available");
        }

        venue.setCurrentOccupancy(venue.getCurrentOccupancy() + numTickets);
        venueRepository.save(venue);

        return "Tickets booked successfully";
    }
}