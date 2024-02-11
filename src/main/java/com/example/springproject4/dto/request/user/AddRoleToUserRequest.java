package com.example.springproject4.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddRoleToUserRequest {
    @NotBlank
    private String roleName;
    @NotBlank
    private String userName;
}
