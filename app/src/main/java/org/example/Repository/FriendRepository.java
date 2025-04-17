package org.example.Repository;

import java.util.List;

import org.example.Entities.Friendships;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface FriendRepository extends JpaRepository<Friendships, Long> {
    List<Friendships> findByUserId(Long userId);
    void deleteByUserIdAndFriendId(Long userId, Long friendId);
    List<Friendships> findFriendsByUserId(@Param("userId") Long userId);
    Friendships findByUserIdAndFriendId(Long userId, Long friendId);

}
