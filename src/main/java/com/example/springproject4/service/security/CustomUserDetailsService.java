package com.example.springproject4.service.security;

import com.example.springproject4.entity.Role;
import com.example.springproject4.entity.User;
import com.example.springproject4.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByName(username);

        if (userOptional.isPresent())
        {
            User user = userOptional.get();
            List<Role> roles = user.getRoles();
            boolean isSupeAdmin = roles.stream().anyMatch(role -> role.getName().equals("SUPERADMIN"));
            return new UserDetailsImpl(user, isSupeAdmin);
        }
        else {
            throw new UsernameNotFoundException("Invalid user name");
        }
    }
}
