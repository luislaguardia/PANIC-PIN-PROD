package org.example.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class Distress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long distressId;

    private Long userId;
    private double latitude;
    private double longitude;
    private LocalDateTime timestamp;

    private String status;
    private String finalReport;

    public Distress() {}

    
    public Distress(Long userId, double latitude, double longitude, LocalDateTime timestamp) {
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
        this.status= "pending";
    }

    

    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getFinalReport() {
        return finalReport;
    }


    public void setFinalReport(String finalReport) {
        this.finalReport = finalReport;
    }


    public Long getDistressId() {
        return distressId;
    }

    public void setDistressId(Long distressId) {
        this.distressId = distressId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    

}
