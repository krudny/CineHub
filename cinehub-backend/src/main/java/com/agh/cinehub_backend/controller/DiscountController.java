package com.agh.cinehub_backend.controller;

import com.agh.cinehub_backend.model.Discount;
import com.agh.cinehub_backend.service.DiscountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<Discount> getAllDiscounts(){
        return discountService.getAllDiscounts();
    }

    @GetMapping("/names")
    public List<String> getAllDiscountsNames() {
        return discountService.getAllDiscountsNames();
    }


}
