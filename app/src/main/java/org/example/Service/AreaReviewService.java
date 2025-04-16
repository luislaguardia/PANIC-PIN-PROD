package org.example.Service;

import org.example.Entities.AreaReview;
import org.example.Repository.AreaReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaReviewService {

    @Autowired
    private AreaReviewRepository areaReviewRepository;

    public void submitReview(AreaReview review) {
        areaReviewRepository.save(review);
    }

    public List<AreaReview> getReviewsWithinRadius(double latitude, double longitude, double radius) {
        double latDiff = radius / 111.0; // Approximate latitude degrees per km
        double lonDiff = radius / (111.0 * Math.cos(Math.toRadians(latitude))); // Adjust longitude based on latitude

        return areaReviewRepository.findByLatitudeBetweenAndLongitudeBetween(
                latitude - latDiff, latitude + latDiff,
                longitude - lonDiff, longitude + lonDiff
        );
    }
}
