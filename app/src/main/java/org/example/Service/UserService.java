package org.example.Service;

import org.example.DTO.AuthResponseDTO;
import org.example.DTO.loginAuthResponseDTO;
import org.example.Entities.User;
import org.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;  

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public AuthResponseDTO signup(User user) {
        String email = user.getEmail();
        String username = user.getUsername();

        if (getUserByEmail(email) != null) {
            return new AuthResponseDTO(false, "Email already exists", user);
        }
        if (getUserByUsername(username) != null) {
            return new AuthResponseDTO(false, "Username already exists", user);
        }

        user.setHashedPassword(passwordEncoder.encode(user.getHashedPassword()));
        userRepository.save(user);

        return new AuthResponseDTO(true, "signup successful", user);
    }

    public AuthResponseDTO verify(User user) {
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getHashedPassword()));

        if (auth.isAuthenticated()) {
            User user1 = getUserByUsername(user.getUsername());
            return new loginAuthResponseDTO(
                true, 
                "signin successful", 
                Map.of("username", user1.getUsername(), "id", user1.getId(), "email", user1.getEmail(), "apiKey", user1.getApiKey(), "fullname", user1.getFullname(), "phoneno", user1.getPhoneno(), "gender", user1.getGender(), "birthday", user1.getBirthday()), 
                jwtService.generateToken(user1.getUsername())
            );
        }
            
        return new AuthResponseDTO(
            false, 
            "login failed", 
            Map.of("username", user.getUsername())
        );
    }

    public User getUserbyId(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean isValidApiKey(String apiKey, Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return user != null && user.getApiKey().equals(apiKey);
    }

    
    public String getUserNameById(Long userId) {
        return userRepository.findById(userId)
            .map(User::getFullname)  // Assuming the User entity has a "fullname" field
            .orElse("Unknown User");
    }
    
}
