package com.agh.cinehub_backend.configurator;

import com.agh.cinehub_backend.model.Discount;
import com.agh.cinehub_backend.repository.DiscountRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscountConfigurator {
    private final DiscountRepository discountRepository;

    public DiscountConfigurator(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @PostConstruct
    public void init() {
        if (discountRepository.count() == 0) {
            createDiscount("Standard", 0);
            createDiscount("Student", 0.25f);
            createDiscount("Child", 0.33f);
            createDiscount("Senior", 0.65f);
        }
    }

    private void createDiscount(String discountName, float discountValue) {
        Discount discount = new Discount();
        discount.setName(discountName);
        discount.setValue(discountValue);
        discountRepository.save(discount);
    }
}
