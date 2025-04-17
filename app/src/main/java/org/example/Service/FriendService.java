package org.example.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.example.DTO.FriendDTO;
import org.example.Entities.Friendships;
import org.example.Repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class FriendService {
    
    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserService userService;
    

    public boolean isFriendRequestPending(Long friendId, Long userId) {
        Friendships friendship = friendRepository.findByUserIdAndFriendId(friendId, userId);
        return friendship != null && "pending".equals(friendship.getStatus());
    }

    public void updateFriendRequestStatus(Long friendId, Long userId, String status) {
        Friendships friendship = friendRepository.findByUserIdAndFriendId(friendId, userId);
        if (friendship != null) {
            friendship.setStatus(status);
            friendRepository.save(friendship);
        }
    }

    public void addFriend(Long userId, Long friendId, String status) {
        Friendships friendship = new Friendships(userId, userId, friendId, status);
        friendRepository.save(friendship);
    }
    

    public void sendFriendRequest(Long userId, Long friendId) {
        Friendships friendship = new Friendships(friendId, userId, friendId, "pending");
        friendRepository.save(friendship);
    }

    @Transactional
    public void removeFriend(Long userId, Long friendId) {
        friendRepository.deleteByUserIdAndFriendId(userId, friendId);
    }

    public List<FriendDTO> getFriendList(Long userId) {
        List<Friendships> friends = friendRepository.findFriendsByUserId(userId);
        
        return friends.stream().map(friend -> {
            Long friendId = (friend.getUserId().equals(userId)) ? friend.getFriendId() : friend.getUserId();
            String friendName = userService.getUserNameById(friendId);
            return new FriendDTO(friendId, friendName, friend.getStatus());
        }).collect(Collectors.toList());
    }
    
    
}
