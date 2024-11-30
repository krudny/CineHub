package com.agh.cinehub_backend.service;

import com.agh.cinehub_backend.model.Discount;
import com.agh.cinehub_backend.repository.DiscountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService {
    private final DiscountRepository discountRepository;

    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    public List<String> getAllDiscountsNames() {
        return discountRepository.getAllDiscountsNames();
    }
}
