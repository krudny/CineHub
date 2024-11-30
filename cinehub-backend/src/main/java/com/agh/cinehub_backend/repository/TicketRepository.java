package com.agh.cinehub_backend.repository;

import com.agh.cinehub_backend.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
