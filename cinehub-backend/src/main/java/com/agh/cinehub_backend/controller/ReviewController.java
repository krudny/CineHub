package com.agh.cinehub_backend.controller;

import com.agh.cinehub_backend.DTO.ReviewRequest;
import com.agh.cinehub_backend.model.Review;
import com.agh.cinehub_backend.model.User;
import com.agh.cinehub_backend.service.ReviewService;
import com.agh.cinehub_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;

    @GetMapping
    public List<Review> getUserReviews() {
        // TODO: check if user is logged in and get requesting user
        int userId = 1;
        User user = userService.getUserById(userId);
        return reviewService.getReviewsByUser(user);
    }
}
