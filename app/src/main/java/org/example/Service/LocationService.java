package org.example.Service;

import org.example.Entities.Location;
import org.example.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public void saveLocation(Location location) {
        location.setTimestamp(LocalDateTime.now());
        locationRepository.save(location);
    }

    public Optional<Location> getLocation(Long userId) {
        return locationRepository.findByUserId(userId);
    }

    public void updateLocation(Location location) {
        location.setTimestamp(LocalDateTime.now());
        locationRepository.save(location);
    }

    public void deleteLocation(Long userId) {
        locationRepository.deleteByUserId(userId);
    }
}
