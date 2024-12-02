package com.agh.cinehub_backend.service;

import com.agh.cinehub_backend.model.Discount;
import com.agh.cinehub_backend.repository.DiscountRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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

    public void addDiscount(String name, Float value) {
        if (value < 0 || value > 100) {
            throw new IllegalArgumentException("Invalid discount value.");
        }

        Discount newDiscount = Discount.builder()
                        .name(name)
                        .value(value)
                        .build();
        try {
            discountRepository.save(newDiscount);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Discount with name '" + name + "' already exists.");
        }

    }
}
