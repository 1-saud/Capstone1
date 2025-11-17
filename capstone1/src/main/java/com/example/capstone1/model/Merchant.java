package com.example.capstone1.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Merchant {

    @NotEmpty(message = " must not be empty ")
    @NotBlank(message = " must not be empty ")
    private String id;

    @NotEmpty(message = " must not be empty ")
    @NotBlank(message = " must not be empty ")
    @Size(min = 4 , message = " have to be more than 3 length long")
    private String name;

}
