package org.example.Service;

import org.example.Entities.User;
import org.example.Repository.UserRepository;
import org.example.security.UserDetailsPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsPrincipalService implements UserDetailsService{

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);

        if (user == null) {
            System.out.println("rawr");
            throw new UsernameNotFoundException("User not found");
        }

        return new UserDetailsPrincipal(user);
    }
    
}
 