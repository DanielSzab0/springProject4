package com.example.springproject4.dto.request.products;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditProductRequest {
    @NotBlank
    private String name;
    @NotNull
    private Double price;
    @Min(0)
    private Integer quantity;
}
