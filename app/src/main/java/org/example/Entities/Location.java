package org.example.Entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "locations")
public class Location {

    @Id
    private Long userId;

    private double latitude;
    private double longitude;

    @Column(name = "apikey")
    private String apikey;

    @Column(name = "timestamp", columnDefinition = "TIMESTAMP")
    private LocalDateTime timestamp;

    public Location() {}

    public Location(Long userId, double latitude, double longitude, LocalDateTime timestamp, String apikey) {
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
        this.apikey = apikey;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getApikey() { return apikey; }
    public void setApikey(String apikey) { this.apikey = apikey; }
}
