package com.example.springproject4.dto.request.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddProductsListToUserRequest {
    private Integer userId;
    private List<Integer> productsId;
}
