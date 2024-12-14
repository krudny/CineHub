package com.agh.cinehub_backend.controller;

import com.agh.cinehub_backend.model.Ticket;
import com.agh.cinehub_backend.model.User;
import com.agh.cinehub_backend.service.TicketService;
import com.agh.cinehub_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE', 'USER')")
    public List<Ticket> getUserTickets() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(userEmail);
        return ticketService.getTicketsByUser(user);
    }
}
