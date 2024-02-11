package com.example.springproject4.controller;

import com.example.springproject4.dto.request.products.AddProductToUserRequest;
import com.example.springproject4.dto.request.products.EditProductRequest;
import com.example.springproject4.dto.request.products.ProductRequest;
import com.example.springproject4.dto.request.user.*;
import com.example.springproject4.dto.response.product.ProductResponse;
import com.example.springproject4.dto.response.user.RoleResponse;
import com.example.springproject4.dto.response.user.UserResponse;
import com.example.springproject4.service.CustomService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
public class CustomController {

    private final CustomService customService;

    public CustomController(CustomService customService) {
        this.customService = customService;
    }

    @PostMapping("/api/product")
    public ResponseEntity<Void> addProduct(@RequestBody @Valid ProductRequest productRequest)
    {
        customService.addProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/api/product/{id}")
    public ResponseEntity<ProductResponse> findProduct(@PathVariable Integer id)
    {
        ProductResponse productResponse = customService.getProduct(id);
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping("/api/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts()
    {
        List<ProductResponse> result = customService.getAllProducts();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<UserResponse>> getAllUsers()
    {
        List<UserResponse> result = customService.getAllUsers();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<UserResponse> findUser(@PathVariable Integer id)
    {
        UserResponse userResponse = customService.getUser(id);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/api/user")
    public ResponseEntity<Void> addUser(@RequestBody @Valid UserRequest userRequest)
    {
        customService.addUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/api/addProductToUser")
    public ResponseEntity<Void> addProductToUser(@RequestBody AddProductToUserRequest addProductToUserRequest)
    {
        customService.addProductToUser(addProductToUserRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/addProductListToUser/{userId}/{productId}")
    public ResponseEntity<Void> addProductsToUser(@PathVariable Integer userId, @PathVariable List<Integer> productId)
    {
        customService.addProductsListToUser(userId, productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id)
    {
        customService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) throws AccessDeniedException {
        customService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/api/user/{id}")
    public ResponseEntity<Void> editUser(@RequestBody @Valid EditUserRequest editUserRequest, @PathVariable Integer id) {
        customService.editUser(editUserRequest, id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/api/product/{id}")
    public ResponseEntity<Void> editProduct(@RequestBody @Valid EditProductRequest editProductRequest, @PathVariable Integer id) {
        customService.editProduct(editProductRequest, id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/role")
    public ResponseEntity<Void> addRole(@RequestBody RoleRequest roleRequest) {
        customService.addRole(roleRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/role/{id}")
    public ResponseEntity<RoleResponse> findRole(@PathVariable Integer id) {
        RoleResponse response = customService.getRole(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/addRoleToUser")
    public ResponseEntity<Void> addRoleToUser(@RequestBody @Valid AddRoleToUserRequest addRoleToUserRequest) {

        customService.addRoleToUser(addRoleToUserRequest);
        return ResponseEntity.ok().build();
    }

}

