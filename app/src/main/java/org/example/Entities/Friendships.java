package org.example.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "friendships")
public class Friendships {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long friendId;

    private String status;

    public Friendships() {}

    public Friendships(Long id, Long userId, Long friendId, String status) {
        this.userId= userId;
        this.friendId= friendId;
        this.status= status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // luis pogi

}
