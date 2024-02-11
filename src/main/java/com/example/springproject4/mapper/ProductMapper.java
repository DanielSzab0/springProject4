package com.example.springproject4.mapper;

import com.example.springproject4.dto.request.products.EditProductRequest;
import com.example.springproject4.dto.request.products.ProductRequest;
import com.example.springproject4.dto.response.product.ProductResponse;
import com.example.springproject4.entity.Product;

public class ProductMapper {

    public static Product fromProductRequest(ProductRequest productRequest)
    {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());

        return product;
    }

    public static ProductResponse fromProduct(Product product)
    {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setQuantity(product.getQuantity());

        if (product.getUser() != null) {
            productResponse.setUser(product.getUser().getName());
        }

        return productResponse;
    }

    public static void updateFromProductRequest(Product product, EditProductRequest editProductRequest)
    {
        product.setName(editProductRequest.getName());
        product.setPrice(editProductRequest.getPrice());
        product.setQuantity(editProductRequest.getQuantity());
    }
}

