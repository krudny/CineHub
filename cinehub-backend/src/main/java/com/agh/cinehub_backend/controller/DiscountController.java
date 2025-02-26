package com.agh.cinehub_backend.controller;

import com.agh.cinehub_backend.DTO.DiscountRequest;
import com.agh.cinehub_backend.model.Discount;
import com.agh.cinehub_backend.service.DiscountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discounts")
public class DiscountController {
    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping
    public ResponseEntity<List<Discount>> getAllDiscounts() {
        return ResponseEntity.ok(discountService.getAllDiscounts());
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllDiscountsNames() {
        return ResponseEntity.ok(discountService.getAllDiscountsNames());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> addDiscount(@Valid @RequestBody DiscountRequest request) {
        discountService.addDiscount(request);
        return ResponseEntity.ok("Discount " + request.getName() + " with value " + request.getValue() + " added successfully");
    }
}
