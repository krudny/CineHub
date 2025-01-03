package com.agh.cinehub_backend.controller;

import com.agh.cinehub_backend.DTO.UpdateTicketRequest;
import com.agh.cinehub_backend.DTO.TicketDto;
import com.agh.cinehub_backend.DTO.TicketRequest;
import com.agh.cinehub_backend.model.Screening;
import com.agh.cinehub_backend.model.Statuses;
import com.agh.cinehub_backend.model.Ticket;
import com.agh.cinehub_backend.model.User;
import com.agh.cinehub_backend.service.ScreeningService;
import com.agh.cinehub_backend.service.TicketService;
import com.agh.cinehub_backend.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;
    private final UserService userService;
    private final ScreeningService screeningService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE') or (hasRole('USER') and #screeningId == null)")
    public List<Ticket> getTickets(@RequestParam(value = "screeningId", required = false) Integer screeningId) {
        if (screeningId != null) {
            Screening screening = screeningService.getScreeningById(screeningId);
            return ticketService.getTicketsByScreening(screening);
        } else {
            String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.getUserByEmail(userEmail);

            return ticketService.getTicketsByUser(user);
        }
    }

    @Transactional
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE', 'USER')")
    public ResponseEntity<?> addTicket(@Valid @RequestBody List<TicketRequest> requests) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(userEmail);

        List<TicketDto> addedTickets = new ArrayList<>();
        requests.forEach(request -> {
            addedTickets.add(ticketService.ticketDtoMapper(ticketService.addTicket(user, request)));
        });

        return ResponseEntity.ok(addedTickets);
    }

    @Transactional
    @PutMapping("/pay")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE', 'USER')")
    public ResponseEntity<?> payTicket(@Valid @RequestBody List<UpdateTicketRequest> requests) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(userEmail);

        List<TicketDto> updatedTickets = new ArrayList<>();
        requests.forEach(request -> {
            updatedTickets.add(ticketService.ticketDtoMapper(ticketService.updateTicket(user, request, Statuses.PAYED.getName())));
        });

        return ResponseEntity.ok(updatedTickets);
    }

    @Transactional
    @PutMapping("/cancel")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE', 'USER')")
    public ResponseEntity<?> cancelTicket(@Valid @RequestBody List<UpdateTicketRequest> requests) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(userEmail);

        List<TicketDto> updatedTickets = new ArrayList<>();
        requests.forEach(request -> {
            updatedTickets.add(ticketService.ticketDtoMapper(ticketService.updateTicket(user, request, Statuses.CANCELED.getName())));
        });

        return ResponseEntity.ok(updatedTickets);
    }


}
