package org.example.Repository;

import org.example.Entities.AreaReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaReviewRepository extends JpaRepository<AreaReview, Long> {
    List<AreaReview> findByLatitudeBetweenAndLongitudeBetween(double minLat, double maxLat, double minLon, double maxLon);
}
