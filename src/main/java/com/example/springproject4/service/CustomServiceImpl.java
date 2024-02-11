package com.example.springproject4.service;

import com.example.springproject4.dto.request.products.AddProductToUserRequest;
import com.example.springproject4.dto.request.products.AddProductsListToUserRequest;
import com.example.springproject4.dto.request.products.EditProductRequest;
import com.example.springproject4.dto.request.products.ProductRequest;
import com.example.springproject4.dto.request.user.AddRoleToUserRequest;
import com.example.springproject4.dto.request.user.EditUserRequest;
import com.example.springproject4.dto.request.user.RoleRequest;
import com.example.springproject4.dto.request.user.UserRequest;
import com.example.springproject4.dto.response.product.ProductResponse;
import com.example.springproject4.dto.response.user.RoleResponse;
import com.example.springproject4.dto.response.user.UserResponse;
import com.example.springproject4.entity.Product;
import com.example.springproject4.entity.Role;
import com.example.springproject4.entity.User;
import com.example.springproject4.exception.product.ProductNotFoundException;
import com.example.springproject4.exception.UserNotFoundException;
import com.example.springproject4.mapper.ProductMapper;
import com.example.springproject4.mapper.RoleMapper;
import com.example.springproject4.mapper.UserMapper;
import com.example.springproject4.repository.ProductRepository;
import com.example.springproject4.repository.RoleRepository;
import com.example.springproject4.repository.UserRepository;
import com.example.springproject4.service.security.UserDetailsImpl;
import org.hibernate.Hibernate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomServiceImpl implements CustomService{

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public CustomServiceImpl(ProductRepository productRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void addProduct(ProductRequest productRequest) {

        Product product = ProductMapper.fromProductRequest(productRequest);
        productRepository.save(product);
    }

    @Override
    public User addUser(UserRequest userRequest) {

        User user = UserMapper.fromUserRequest(userRequest);
        userRepository.save(user);
        return user;
    }

    @Override
    public UserResponse getUser(Integer id)
    {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent())
        {
            User user = userOptional.get();
            return UserMapper.fromUser(user);

        } else
        {
            throw new UserNotFoundException("User with id " + id + " not found!");
        }
    }

    @Override
    public ProductResponse getProduct(Integer id)
    {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent())
        {
            Product product = productOptional.get();
            return ProductMapper.fromProduct(product);

        } else
        {
            throw new ProductNotFoundException("Product with id " + id + " not found!");
        }

    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(product -> ProductMapper.fromProduct(product)).toList();
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(user -> UserMapper.fromUser(user)).toList();
    }

    @Override
    public void addProductToUser(AddProductToUserRequest addProductToUserRequest) {
        Integer productId = addProductToUserRequest.getProductId();
        Integer userId = addProductToUserRequest.getUserId();

        Optional<Product> productFound = productRepository.findById(productId);
        Optional<User> userFound = userRepository.findById(userId);

        if (productFound.isPresent() && userFound.isPresent())
        {
            Product product = productFound.get();
            User user = userFound.get();

            user.addProduct(product);
            userRepository.save(user); //save-ul se comporta ca un update (persist(), commit(), update())
        }
    }

    @Override
    public void addProductsListToUser(Integer userId, List<Integer> productId) {
        AddProductsListToUserRequest addProductsListToUserRequest = new AddProductsListToUserRequest();
        addProductsListToUserRequest.setUserId(userId);
        addProductsListToUserRequest.setProductsId(productId);
        Integer userIdToBeFound = addProductsListToUserRequest.getUserId();
        List<Integer> productsIdToBeFound = addProductsListToUserRequest.getProductsId();

        Optional<User> userFound = userRepository.findById(userIdToBeFound);
        List<Product> productsFound = productRepository.findAll().stream().filter(product -> productsIdToBeFound.contains(product.getId())).toList();
        if (userFound.isPresent() && !productsFound.isEmpty()) {
            User user = userFound.get();
            user.addProducts(productsFound);
            userRepository.save(user);
        } else {
            throw new RuntimeException("User or Product not found!");
        }
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public void deleteUser(Integer id) throws AccessDeniedException {
        Optional<User> userToBeDeleted = userRepository.findById(id);
        if (userToBeDeleted.isPresent())
        {
            User user = userToBeDeleted.get();
            List<Product> productsToBeRemoved = user.getProducts();
            if (!productsToBeRemoved.isEmpty()) {
                productsToBeRemoved.forEach(product -> product.setUser(null));
                user.deleteProducts(productsToBeRemoved);
            }
            UserDetailsImpl userDetailsImpl = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            boolean isSuperAdmin = userDetailsImpl.isSuperAdmin();
            if (!isSuperAdmin && !user.getRoles().stream().allMatch(role -> role.getName().equals("SUPERADMIN"))) {
                SecurityContextHolder.getContext().getAuthentication().getName();
                throw new AccessDeniedException("Access denied: you do not have permission to delete this user");
            }

            userRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("User not found");
        }

    }

    @Override
    public void editUser(EditUserRequest editUserRequest, Integer id) {
        Optional<User> userToBePatched = userRepository.findById(id);
        if (userToBePatched.isPresent()) {
           User user = userToBePatched.get();
           UserMapper.updateFromUserRequest(user, editUserRequest);
           userRepository.save(user);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public void editProduct(EditProductRequest editProductRequest, Integer id) {
        Optional<Product> productToBePatched = productRepository.findById(id);
        if (productToBePatched.isPresent()) {
            Product product = productToBePatched.get();
            ProductMapper.updateFromProductRequest(product, editProductRequest);
            productRepository.save(product);
        }
        else
        {
            throw new ProductNotFoundException("Product not found");
        }
    }

    @Override
    public Role addRole(RoleRequest roleRequest) {
        Role role = RoleMapper.fromRoleRequest(roleRequest);
                roleRepository.save(role);
                return role;
    }

    @Override
    public RoleResponse getRole(Integer id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            Hibernate.initialize(role.getUsers());
            return RoleMapper.fromRole(role);
        } else {
            throw new RuntimeException("No role found");
        }
    }

    @Override
    public void addRoleToUser(AddRoleToUserRequest addRoleToUserRequest) {
        String userName = addRoleToUserRequest.getUserName();
        String roleName = addRoleToUserRequest.getRoleName();

        Optional<User> userOptional = userRepository.findUserByName(userName);
        Optional<Role> roleOptional = roleRepository.findRoleByName(roleName);

        if (userOptional.isPresent() && roleOptional.isPresent()) {
            User user = userOptional.get();
            Role role = roleOptional.get();

            user.addRole(role);
            userRepository.save(user);
        }
    }
}

