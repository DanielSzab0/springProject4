package com.example.springproject4.dto.request.products;

import lombok.Data;

@Data
public class AddProductToUserRequest {

    private Integer productId;
    private Integer userId;

}
