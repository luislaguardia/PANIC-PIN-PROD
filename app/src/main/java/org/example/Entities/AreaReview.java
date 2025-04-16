package org.example.Entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "area_reviews")
public class AreaReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private double latitude;
    private double longitude;
    private String review;

    @Column(name = "apikey")
    private String apikey;

    private LocalDateTime timestamp;

    public AreaReview() {
        this.timestamp = LocalDateTime.now();
    }

    public AreaReview(Long userId, double latitude, double longitude, String review, String apikey) {
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.review = review;
        this.apikey = apikey;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public String getReview() { return review; }
    public void setReview(String review) { this.review = review; }

    public String getApikey() { return apikey; }
    public void setApikey(String apikey) { this.apikey = apikey; }

    public LocalDateTime getTimestamp() { return timestamp; }
}
