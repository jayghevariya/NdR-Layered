package com.ndrlayered.NdRLayered.repository;

import com.ndrlayered.NdRLayered.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
