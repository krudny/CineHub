package com.agh.cinehub_backend.service;

import com.agh.cinehub_backend.model.Discount;
import com.agh.cinehub_backend.repository.DiscountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DiscountService {
    private final DiscountRepository discountRepository;

    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    public List<String> getAllDiscountsNames() {
        return discountRepository.getAllDiscountsNames();
    }
}
