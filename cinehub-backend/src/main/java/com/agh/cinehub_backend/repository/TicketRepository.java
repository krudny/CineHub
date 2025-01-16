package com.agh.cinehub_backend.repository;

import com.agh.cinehub_backend.model.Screening;
import com.agh.cinehub_backend.model.Seat;
import com.agh.cinehub_backend.model.Ticket;
import com.agh.cinehub_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findAllByUser(User user);
    List<Ticket> findAllByScreening(Screening screening);
    List<Ticket> findByScreeningAndSeat(Screening screening, Seat seat);

    @Query("SELECT t FROM Ticket t WHERE t.screening.startDate >= :startDate AND t.screening.movie.movieId = :movieId")
    List<Ticket> findTicketsFromLastTwoWeeks(@Param("startDate") LocalDateTime startDate, @Param("movieId") Integer movieId);
}
