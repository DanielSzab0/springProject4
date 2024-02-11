package com.example.springproject4.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponse {

    private String name;
    private String email;
    private Integer age;
    private List<String> roles;
}
