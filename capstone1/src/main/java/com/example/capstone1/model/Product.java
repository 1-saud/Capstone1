package com.example.capstone1.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Product {

    @NotBlank(message = "id is empty")
    private String id;

    @NotBlank(message = "name is empty")
    @Size(min = 4, message = "name must be more than 3 characters long")
    private String name;

    @NotNull(message = "price must not be null")
    @Positive(message = "price must be positive number")
    private Double price;

    @NotBlank(message = "categoryId must not be empty")
    private String categoryId;
}
