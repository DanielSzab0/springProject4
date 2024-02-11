package com.example.springproject4.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditUserRequest {
    @NotBlank
    private String name;
    @Email
    private String email;
    @Pattern(regexp = "\\d{10}$")
    private String mobile;
    @Min(18)
    private Integer age;
}
