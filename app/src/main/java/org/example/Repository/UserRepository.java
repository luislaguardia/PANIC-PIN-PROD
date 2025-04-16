package org.example.Repository;

import org.example.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailIgnoreCase(String email);   
    User findByApiKey(String apiKey);
    User findByUsername(String username);
}
