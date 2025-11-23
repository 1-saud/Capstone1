package com.example.capstone1.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Category {

    @NotEmpty(message = " must not be empty ")
    @NotBlank(message = " must not be empty ")
    private String id;

    @NotEmpty(message = " must not be empty ")
    @NotBlank(message = " must not be empty ")
    @Size(min = 4 , message = "name must be more than 3 characters")
    private String name;



}
