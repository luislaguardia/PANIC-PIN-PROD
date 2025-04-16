package org.example.Repository;

import java.util.Optional;

import org.example.Entities.Distress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistressRepository extends JpaRepository<Distress, Long>{
    Optional<Distress> findTopByUserIdOrderByTimestampDesc(Long userId); //get latest distress report
}
