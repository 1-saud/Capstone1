package com.example.capstone1.Controller;

import com.example.capstone1.API.ApiResponse;
import com.example.capstone1.model.Product;
import com.example.capstone1.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Valid
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/ecommerce/productservice")
public class ProductController {

    private final ProductService productService;


    @GetMapping("/get")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.status(200).body(productService.getProducts());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody @Valid Product product,
                                        Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        productService.addProduct(product);
        return ResponseEntity.status(200).body("Product added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isUpdated = productService.updateProduct(id, product);
        if (!isUpdated) {
            return ResponseEntity.status(400).body("Product not found");
        }
        return ResponseEntity.status(200).body("Product updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (!isDeleted) {
            return ResponseEntity.status(400).body("Product not found");
        }
        return ResponseEntity.status(200).body("Product deleted");
    }

    // endpoint1
    @GetMapping("/search/{name}")
    public ResponseEntity<?> searchProducts(@PathVariable String name) {
        return ResponseEntity.status(200).body(productService.searchProductsByName(name));
    }

    // endpoint5
    @GetMapping("/last")
    public ResponseEntity<?> getLastProduct() {
        ArrayList<Product> list = productService.getProducts();
        if (list.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse("empty"));
        }
        Product lastProduct = list.get(list.size() - 1);
        return ResponseEntity.ok(lastProduct);
    }

}
