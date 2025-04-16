package org.example.controllers;

import java.util.Map;
import java.util.Optional;

import org.example.Entities.Distress;
import org.example.Service.DistressService;
import org.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/distress")
public class DistressController {

    @Autowired
    private DistressService distressService;

    @Autowired
    private UserService userService;

    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendDistress(@RequestBody Map<String, Object> requestBody) {
        String apikey = (String) requestBody.get("apikey");
        Long userId = Long.parseLong(requestBody.get("userId").toString());

        if (!userService.isValidApiKey(apikey, userId)) {
            return ResponseEntity.status(403).body(Map.of("message", "Invalid API Key"));
        }

        Object locationObj = requestBody.get("location");
        if (!(locationObj instanceof Map)) {
            return ResponseEntity.badRequest().body(Map.of("message", "Invalid location format"));
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> location = (Map<String, Object>) locationObj;

        double latitude, longitude;
        try {
            latitude = Double.parseDouble(location.get("latitude").toString());
            longitude = Double.parseDouble(location.get("longitude").toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Invalid latitude or longitude"));
        }

        Distress distress = distressService.sendDistress(userId, latitude, longitude);

        return ResponseEntity.ok(Map.of(
                "message", "Distress signal sent successfully",
                "dashboardID", "DASH-" + distress.getDistressId(),
                "distressReportID", distress.getDistressId().toString()
        ));
    }

    @PostMapping("/status")
    public ResponseEntity<Map<String, String>> getDistressStatus(@RequestBody Map<String, Object> requestBody) {
        String apikey = (String) requestBody.get("apikey");
        Long distressId = Long.parseLong(requestBody.get("distressID").toString());
        // Long userId = Long.parseLong(requestBody.get("userID").toString());
        Long userId = Long.parseLong(requestBody.get("userId").toString()); // new to


        if (!userService.isValidApiKey(apikey, userId)) {
            return ResponseEntity.status(403).body(Map.of("message", "Invalid API Key"));
        }

        Optional<Distress> distressOpt = distressService.getDistressById(distressId);
        if (distressOpt.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("message", "No distress report found"));
        }

        String status = distressOpt.get().getStatus();
        return ResponseEntity.ok(Map.of(
                "message", "Distress status retrieved",
                "status", status
        ));
    }

    @PostMapping("/finalreport")
    public ResponseEntity<Map<String, String>> getFinalReport(@RequestBody Map<String, Object> requestBody) {
        String apikey = (String) requestBody.get("apikey");
        Long userId = Long.parseLong(requestBody.get("userId").toString());
        Long distressId = Long.parseLong(requestBody.get("distressID").toString());
    
        if (!userService.isValidApiKey(apikey, userId)) {
            return ResponseEntity.status(403).body(Map.of("message", "Invalid API Key"));
        }
    
        Optional<Distress> distressOpt = distressService.getDistressById(distressId);
        if (distressOpt.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("message", "No distress report found"));
        }
    
        String report = distressOpt.get().getFinalReport();
        return ResponseEntity.ok(Map.of(
                "message", (report == null || report.isEmpty()) ? "Final report not available" : "Final report retrieved",
                "report", report != null ? report : ""
        ));
    }
    

    @GetMapping("/getid")
    public ResponseEntity<Map<String, String>> getDistressId(
            @RequestParam(name = "apikey") String apikey,
            @RequestParam(name = "userId") Long userId) {

        if (!userService.isValidApiKey(apikey, userId)) {
            return ResponseEntity.status(403).body(Map.of("message", "Invalid API Key"));
        }

        Optional<Distress> distress = distressService.getLatestDistress(userId);
        if (distress.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("message", "No distress report found"));
        }

        return ResponseEntity.ok(Map.of(
                "message", "Distress ID retrieved",
                "distressID", distress.get().getDistressId().toString()
        ));
    }
}
