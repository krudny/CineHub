package com.agh.cinehub_backend.controller;

import com.agh.cinehub_backend.DTO.ReviewRequest;
import com.agh.cinehub_backend.model.Review;
import com.agh.cinehub_backend.model.User;
import com.agh.cinehub_backend.service.ReviewService;
import com.agh.cinehub_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE', 'USER')")
    public List<Review> getUserReviews() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(userEmail);
        return reviewService.getReviewsByUser(user);
    }
}
