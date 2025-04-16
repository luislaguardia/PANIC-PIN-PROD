package org.example.Repository;

import org.example.Entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByUserId(Long userId);
    void deleteByUserId(Long userId); // changed from String to Long lol mybad
}
