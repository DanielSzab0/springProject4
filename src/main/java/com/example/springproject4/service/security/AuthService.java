package com.example.springproject4.service.security;

import com.example.springproject4.dto.request.user.SignInRequest;
import com.example.springproject4.dto.request.user.UserRequest;
import com.example.springproject4.dto.response.user.SignInResponse;
import com.example.springproject4.entity.Role;
import com.example.springproject4.entity.User;
import com.example.springproject4.exception.role.RoleNotFoundException;
import com.example.springproject4.exception.user.UserAlreadyTakenException;
import com.example.springproject4.mapper.UserMapper;
import com.example.springproject4.repository.RoleRepository;
import com.example.springproject4.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AuthenticationManager authenticationManager;

//    public AuthService(UserRepository userRepository, RoleRepository roleRepository, AuthenticationManager authenticationManager) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.authenticationManager = authenticationManager;
//    }

    public User getUserByEmail(String email)
    {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);

        if (optionalUser.isPresent()){
            throw new UserAlreadyTakenException("Email is already in use");
        }
        return null;
    }

    public void registerUser(UserRequest userRequest) {
        User user = UserMapper.fromUserRequest(userRequest);
        Optional<Role> optionalRole = roleRepository.findRoleByName("USER");
        if (optionalRole.isPresent()){
            user.addRole(optionalRole.get());
        } else {
            throw new RoleNotFoundException("Role with name " + userRequest.getName() + " is not in the database");
        }
        userRepository.save(user);
    }

    public SignInResponse singIn(SignInRequest signInRequest) {
        String userName = signInRequest.getUserName();
        String password = signInRequest.getPassword();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return UserMapper.fromUserDetailsImpl(userDetails);
    }
}
