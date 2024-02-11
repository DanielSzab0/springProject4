package com.example.springproject4.util;

import com.example.springproject4.dto.request.user.AddRoleToUserRequest;
import com.example.springproject4.dto.request.user.RoleRequest;
import com.example.springproject4.dto.request.user.UserRequest;
import com.example.springproject4.entity.Role;
import com.example.springproject4.entity.User;
import com.example.springproject4.repository.RoleRepository;
import com.example.springproject4.repository.UserRepository;
import com.example.springproject4.service.CustomServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AdminUserCommandLineRunner implements CommandLineRunner {

    private final CustomServiceImpl customServiceImpl;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        Optional<Role> optionalRole = roleRepository.findRoleByName("SUPERADMIN");


        RoleRequest roleRequest = new RoleRequest();


        if (optionalRole.isEmpty()) {

            roleRequest.setRoleName("SUPERADMIN");


            Role role = customServiceImpl.addRole(roleRequest);


            addRoleToUser(customServiceImpl, role.getId());

        }


        Optional<User> optionalUser = userRepository.findUserByName("super_admin");


        if (optionalUser.isEmpty()) {

            UserRequest userRequest = new UserRequest();


            userRequest.setName("super_admin");

            userRequest.setEmail("super_admin@yahoo.com");

            userRequest.setAge(30);

            userRequest.setPassword("12345");

            userRequest.setMobile("0722245643");


            User user = customServiceImpl.addUser(userRequest);


            customServiceImpl.addRoleToUser(new AddRoleToUserRequest(user.getName(), "SUPERADMIN"));


            System.out.println("Admin user created successfully.");

        }

    }


    private void addRoleToUser(CustomServiceImpl customServiceImpl, Integer roleId) {

        customServiceImpl.addRoleToUser(new AddRoleToUserRequest("super_admin", roleId.toString()));

    }
}
