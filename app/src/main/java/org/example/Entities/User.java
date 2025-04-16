package org.example.Entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String hashedPassword;

    @Column(unique = true, nullable = false)
    private String apiKey;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "phoneno")
    private String phoneno;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birthday")
    private String birthday;

    public User() {
        this.apiKey = UUID.randomUUID().toString();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getHashedPassword() { return hashedPassword; }
    public void setHashedPassword(String hashedPassword) { this.hashedPassword = hashedPassword; }

    public String getApiKey() { return apiKey; }
    public void setApiKey(String apiKey) { this.apiKey = apiKey; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getPhoneno() { return phoneno; }
    public void setPhoneno(String phoneno) { this.phoneno = phoneno; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getBirthday() { return birthday; }
    public void setBirthday(String birthday) { this.birthday = birthday; }
}
