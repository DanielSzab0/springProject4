package com.example.springproject4.mapper;

import com.example.springproject4.dto.request.user.EditUserRequest;
import com.example.springproject4.dto.request.user.UserRequest;
import com.example.springproject4.dto.response.user.SignInResponse;
import com.example.springproject4.dto.response.user.UserResponse;
import com.example.springproject4.entity.Product;
import com.example.springproject4.entity.Role;
import com.example.springproject4.entity.User;
import com.example.springproject4.service.security.UserDetailsImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static User fromUserRequest(UserRequest userRequest)
    {
        User user = new User();
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        user.setEmail(userRequest.getEmail());
        user.setMobile(userRequest.getMobile());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRoles(new ArrayList<>());
        return user;
    }

    public static UserResponse fromUser(User user)
    {
        UserResponse userResponse = new UserResponse();

        userResponse.setName(user.getName());
        userResponse.setAge(user.getAge());
        userResponse.setEmail(user.getEmail());
        userResponse.setMobile(user.getMobile());


        if (!user.getProducts().isEmpty())
        {
            List<Product> products = user.getProducts();
            List<String> nameProducts = products.stream().map(product -> product.getName()).toList();
            userResponse.setProducts(nameProducts);
        }
        if (!user.getRoles().isEmpty())
        {
            List<Role> roles = user.getRoles();
            List<String> nameRoles = roles.stream().map(role -> role.getName()).toList();
            userResponse.setRoles(nameRoles);
        }
        return userResponse;
    }

    public static void updateFromUserRequest(User user, EditUserRequest editUserRequest) {
        user.setName(editUserRequest.getName());
        user.setAge(editUserRequest.getAge());
        user.setEmail(editUserRequest.getEmail());
        user.setMobile(editUserRequest.getMobile());
    }

    public static SignInResponse fromUserDetailsImpl(UserDetailsImpl userDetails) {
        SignInResponse response = new SignInResponse();

        response.setName(userDetails.getUsername());
        response.setEmail(userDetails.getEmail());
        response.setAge(userDetails.getAge());
        List<String> roles = userDetails.getAuthorities().stream().map(a-> a.getAuthority()).toList();
        response.setRoles(roles);

        return response;
    }

}
