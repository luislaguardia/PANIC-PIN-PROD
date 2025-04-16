package org.example.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.example.Entities.Distress;
import org.example.Repository.DistressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistressService {
    
    @Autowired
    private DistressRepository distressRepository;

    public Distress sendDistress(Long userId, double latitude, double longitude) {
        Distress distress = new Distress(userId, latitude, longitude, LocalDateTime.now());
        return distressRepository.save(distress);
    }

    public Optional<Distress> getLatestDistress(Long userId) {
        return distressRepository.findTopByUserIdOrderByTimestampDesc(userId);
    }

    public Optional<Distress> getDistressById(Long distressId) {
        return distressRepository.findById(distressId);
    }
    
}
