package org.example.controllers;

import org.example.Entities.Location;
import org.example.Service.LocationService;
import org.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private UserService userService;

    @PostMapping("/send")
    public ResponseEntity<String> sendLocation(@RequestBody Location location) {
        String apikey = location.getApikey();
        Long userId = location.getUserId();

        if (!userService.isValidApiKey(apikey, userId)) {
            return ResponseEntity.status(403).body("Invalid API Key");
        }

        locationService.saveLocation(location);
        return ResponseEntity.ok("Location updated successfully");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Location> getLocation(@PathVariable Long userId) {
        Optional<Location> location = locationService.getLocation(userId);
        return location.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateLocation(@RequestBody Location location) {
        String apikey = location.getApikey();
        Long userId = location.getUserId();

        if (!userService.isValidApiKey(apikey, userId)) {
            return ResponseEntity.status(403).body("Invalid API Key");
        }

        locationService.updateLocation(location);
        return ResponseEntity.ok("Location updated successfully");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteLocation(@PathVariable Long userId) {
        locationService.deleteLocation(userId);
        return ResponseEntity.ok("Location data deleted successfully");
    }
}
