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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AdminUserCommandLineRunner implements CommandLineRunner {


    private final CustomServiceImpl customServiceImpl;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public AdminUserCommandLineRunner(CustomServiceImpl customServiceImpl, RoleRepository roleRepository, UserRepository userRepository) {
        this.customServiceImpl = customServiceImpl;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

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
