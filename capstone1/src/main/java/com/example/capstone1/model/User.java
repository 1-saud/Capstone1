package com.example.capstone1.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class User {


    @NotEmpty(message = " must not be empty ")
    @NotBlank(message = "must not be blank")
    private String id;

    @NotEmpty(message = " must not be empty ")
    @NotBlank(message = "must not be blank")
    private String username;

    @NotEmpty(message = " must not be empty ")
    @NotBlank(message = "must not be blank")
    @Size(min = 7 , message = " have to be more than 6 length long")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$", message = "password must contain letters and digits")
    private String password;

    @NotEmpty(message = " must not be empty ")
    @NotBlank(message = "must not be blank")
    @Email(message = "must be a valid email")
    private String email;

    @NotEmpty(message = " must not be empty ")
    @NotBlank(message = "must not be blank")
    @Pattern(regexp = "^(Admin|Customer)$", message = "role must be either Admin or Customer")
    private String role;

    @NotNull(message = " shouldnt be emtpy")
    @Positive(message = " have to be positive")
    private double balance;


}
