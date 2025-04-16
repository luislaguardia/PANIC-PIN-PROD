package org.example.DTO;

public class FriendDTO {
    
    private Long friendId;
    private String name;
    private String status;

    public FriendDTO(Long friendId, String name, String status) {
        this.friendId = friendId;
        this.name = name;
        this.status = status;
    }

    

    public Long getFriendId() {
        return friendId;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }
    
}
