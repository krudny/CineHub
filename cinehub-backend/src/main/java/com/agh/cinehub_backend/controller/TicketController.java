package com.agh.cinehub_backend.controller;

import com.agh.cinehub_backend.DTO.TicketRequest;
import com.agh.cinehub_backend.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ticket")
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/add")
    public ResponseEntity<String> addTicket(@RequestBody TicketRequest ticketRequest) {
        ticketService.addTicket(ticketRequest);
        return ResponseEntity.ok("Ticket added successfully");
    }
}
