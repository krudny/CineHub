package com.agh.cinehub_backend.controller;

import com.agh.cinehub_backend.DTO.DiscountRequest;
import com.agh.cinehub_backend.model.Discount;
import com.agh.cinehub_backend.service.DiscountService;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {
    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping
    public ResponseEntity<List<Discount>> getAllDiscounts(){
        return ResponseEntity.ok(discountService.getAllDiscounts());
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllDiscountsNames() {
        return ResponseEntity.ok(discountService.getAllDiscountsNames());
    }

    @PostMapping
    public ResponseEntity<?> addDiscount(@Valid @RequestBody DiscountRequest request, BindingResult bindingResult) {
        // TODO: check if user have permissions

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }

        discountService.addDiscount(request);
        return ResponseEntity.ok("Discount " + request.getName() + " with value " + request.getValue() + " added successfully");
    }


}
