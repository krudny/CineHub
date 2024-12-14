package com.agh.cinehub_backend.repository;

import com.agh.cinehub_backend.model.Screening;
import com.agh.cinehub_backend.model.Ticket;
import com.agh.cinehub_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findAllByUser(User user);

    List<Ticket> findAllByScreening(Screening screening);
}
