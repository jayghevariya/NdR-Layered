package com.ndrlayered.NdRLayered.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import com.ndrlayered.NdRLayered.repository.TicketRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository bookingRepository;


    public void cancelBooking(Long bookingId) {
        // Implement cancellation logic here
        // Retrieve booking data from the database
        // Cancel the booking and release reserved tickets
        // Update the database
    }
}
