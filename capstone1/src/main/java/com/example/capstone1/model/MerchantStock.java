package com.example.capstone1.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MerchantStock {

    @NotBlank(message = "id must not be empty")
    private String id;

    @NotBlank(message = "productid must not be empty")
    private String productid;

    @NotBlank(message = "merchantid must not be empty")
    private String merchantid;

    @NotNull(message = "stock must not be null")
    @Min(value = 11, message = "stock must be more than 10 at start")
    private Integer stock;
}
