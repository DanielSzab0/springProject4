package com.example.springproject4.dto.request.user;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.sql.Update;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank(groups = Update.class)
    private String name;
    @Email
    private String email;
    @Pattern(regexp = "\\d{10}$")
    private String mobile;
    @Min(18)
    private Integer age;
    @NotEmpty(message = "Password should not be empty")
    private String password;

}
