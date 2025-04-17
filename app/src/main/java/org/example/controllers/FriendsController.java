package org.example.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.DTO.FriendDTO;
import org.example.Service.FriendService;
import org.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public Map<String, String> addFriend(@RequestBody Map<String, String> requestBody) {
        String apikey = requestBody.get("apikey");
        Long userId = Long.parseLong(requestBody.get("userId"));
        Long friendId = Long.parseLong(requestBody.get("friendId"));

        if (!userService.isValidApiKey(apikey, userId)) {
            throw new RuntimeException("Invalid API Key");
        }

        boolean isFriendRequestPending = friendService.isFriendRequestPending(friendId, userId);

        if (isFriendRequestPending) {
            friendService.updateFriendRequestStatus(friendId, userId, "Accepted");
            friendService.addFriend(userId, friendId, "Accepted");

            Map<String, String> response = new HashMap<>();
            response.put("message", "Friend request accepted");
            response.put("status", "accepted");
            return response;
        } else {
            friendService.sendFriendRequest(userId, friendId);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Friend request sent");
            response.put("status", "pending");
            return response;
        }
    }

    @PostMapping("/remove")
    public Map<String, String> removeFriend(@RequestBody Map<String, String> requestBody) {
        String apikey = requestBody.get("apikey");
        Long userId = Long.parseLong(requestBody.get("userId"));
        Long friendId = Long.parseLong(requestBody.get("friendId"));

        if (!userService.isValidApiKey(apikey, userId)) {
            throw new RuntimeException("Invalid API Key");
        }

        friendService.removeFriend(userId, friendId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Friend removed successfully");
        return response;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getFriends(
            @RequestParam(name = "apikey") String apikey,
            @RequestParam(name = "userId") Long userId) {

        if (!userService.isValidApiKey(apikey, userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message", "Invalid API Key"));
        }

        List<FriendDTO> friends = friendService.getFriendList(userId);
        Map<String, Object> response = new HashMap<>();
        response.put("friends", friends);
        return ResponseEntity.ok(response);
    }
}
