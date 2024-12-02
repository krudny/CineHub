package com.agh.cinehub_backend.controller;

import com.agh.cinehub_backend.model.Discount;
import com.agh.cinehub_backend.service.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {
    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Discount>> getAllDiscounts(){
        return ResponseEntity.ok(discountService.getAllDiscounts());
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllDiscountsNames() {
        return ResponseEntity.ok(discountService.getAllDiscountsNames());
    }

    @GetMapping("/add")
    public ResponseEntity<String> addDiscount(@RequestParam String name, @RequestParam Float value) {
        discountService.addDiscount(name, value);
        return ResponseEntity.ok("Discount " + name + " with value " + value + " added successfully");
    }


}
