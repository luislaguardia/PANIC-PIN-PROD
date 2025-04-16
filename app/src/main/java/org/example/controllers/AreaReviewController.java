package org.example.controllers;

import org.example.Entities.AreaReview;
import org.example.Service.AreaReviewService;
import org.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/area")
public class AreaReviewController {

    @Autowired
    private AreaReviewService areaReviewService;

    @Autowired
    private UserService userService;

    @PostMapping("/review")
    public ResponseEntity<String> submitReview(@RequestBody AreaReview review) {
        String apikey = review.getApikey();
        Long userId = review.getUserId();

        if (!userService.isValidApiKey(apikey, userId)) {
            return ResponseEntity.status(403).body("Invalid API Key");
        }

        areaReviewService.submitReview(review);
        return ResponseEntity.ok("Review submitted successfully");
    }

    @PostMapping("/get")
    public ResponseEntity<?> getReviews(@RequestBody AreaReview request) {
        String apikey = request.getApikey();
        Long userId = request.getUserId();

        if (!userService.isValidApiKey(apikey, userId)) {
            return ResponseEntity.status(403).body("Invalid API Key");
        }

        List<AreaReview> reviews = areaReviewService.getReviewsWithinRadius(
                request.getLatitude(), request.getLongitude(), 1.0
        );

        if (reviews.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return "test";
    }
    
}
