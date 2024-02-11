package com.example.springproject4.dto.request.products;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.sql.Update;

@Data
@Builder
public class ProductRequest { //acest product request va reprezenta un intermediar pe care il trimit din frontend

    @NotBlank
    private String name;
    @NotNull
    private Double price;
    @Min(1)
    private Integer quantity;
}
