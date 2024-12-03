package com.agh.cinehub_backend.controller;

import com.agh.cinehub_backend.DTO.TicketRequest;
import com.agh.cinehub_backend.model.Ticket;
import com.agh.cinehub_backend.model.User;
import com.agh.cinehub_backend.service.TicketService;
import com.agh.cinehub_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;
    private final UserService userService;

    @GetMapping
    public List<Ticket> getUserTickets() {
        // TODO: check if user is logged in and get requesting user
        int userId = 1;
        User user = userService.getUserById(userId);
        return ticketService.getTicketsByUser(user);
    }
}
