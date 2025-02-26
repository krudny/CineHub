package com.agh.cinehub_backend.service;

import com.agh.cinehub_backend.DTO.DiscountRequest;
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

    public void addDiscount(DiscountRequest request) {
        if (request.getValue() < 0 || request.getValue() > 100) {
            throw new IllegalArgumentException("Invalid discount value.");
        }

        Discount newDiscount = Discount.builder()
                        .name(request.getName())
                        .value(request.getValue())
                        .build();
        try {
            discountRepository.save(newDiscount);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Discount with name '" + request.getName() + "' already exists.");
        }

    }
}
