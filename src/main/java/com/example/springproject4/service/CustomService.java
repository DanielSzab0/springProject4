package com.example.springproject4.service;

import com.example.springproject4.dto.request.products.AddProductToUserRequest;
import com.example.springproject4.dto.request.products.EditProductRequest;
import com.example.springproject4.dto.request.products.ProductRequest;
import com.example.springproject4.dto.request.user.AddRoleToUserRequest;
import com.example.springproject4.dto.request.user.EditUserRequest;
import com.example.springproject4.dto.request.user.RoleRequest;
import com.example.springproject4.dto.request.user.UserRequest;
import com.example.springproject4.dto.response.product.ProductResponse;
import com.example.springproject4.dto.response.user.RoleResponse;
import com.example.springproject4.dto.response.user.UserResponse;
import com.example.springproject4.entity.Role;
import com.example.springproject4.entity.User;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface CustomService {

    void addProduct(ProductRequest product);
    User addUser(UserRequest user);

    UserResponse getUser(Integer id);

    ProductResponse getProduct(Integer id);

    List<ProductResponse> getAllProducts();

    List<UserResponse> getAllUsers();

    void addProductToUser(AddProductToUserRequest addProductToUserRequest);

//    void addProductsListToUser(AddProductsListToUserRequest addProductsListToUserRequest);
    void addProductsListToUser(Integer userId, List<Integer> productId);
    
    void deleteProduct(Integer id);
    
    void deleteUser(Integer id) throws AccessDeniedException;

    void editUser(EditUserRequest editUserRequest, Integer id);

    void editProduct(EditProductRequest editProductRequest, Integer id);

    Role addRole(RoleRequest roleRequest);

    RoleResponse getRole(Integer id);

    void addRoleToUser(AddRoleToUserRequest addRoleToUserRequest);
}
