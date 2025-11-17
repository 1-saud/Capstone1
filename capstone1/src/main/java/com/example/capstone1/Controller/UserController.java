package com.example.capstone1.Controller;
import com.example.capstone1.API.ApiResponse;
import com.example.capstone1.model.User;
import com.example.capstone1.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Valid
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/ecommerce/userservice")
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody @Valid User user,
                                     Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body("User added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isUpdated = userService.updateUser(id, user);
        if (!isUpdated) {
            return ResponseEntity.status(400).body("User not found");
        }
        return ResponseEntity.status(200).body("User updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        boolean isDeleted = userService.deleteUser(id);
        if (!isDeleted) {
            return ResponseEntity.status(400).body("User not found");
        }
        return ResponseEntity.status(200).body("User deleted");
    }

    @PostMapping("/buy/{userId}/{productId}/{merchantId}")
    public ResponseEntity<?> buyProduct(@PathVariable String userId, @PathVariable String productId, @PathVariable String merchantId) {

        boolean success = userService.buyProduct(userId, productId, merchantId);
        if (!success) {
            return ResponseEntity.status(400).body(new ApiResponse("failed"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("completed"));
    }


    // endpoint2
    @GetMapping("/balance/{userId}")
    public ResponseEntity<?> getBalance(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(400).body(new ApiResponse("User not found"));
        }
        return ResponseEntity.status(200).body(user.getBalance());
    }

    // endpoint4
    @GetMapping("/highestBalance")
    public ResponseEntity<?> highestBalance() {

        ArrayList<User> users = userService.getUsers();

        if (users.isEmpty()) return ResponseEntity.badRequest().body(new ApiResponse("no users"));

        User max = users.get(0);

        for (User u : users) {
            if (u.getBalance() > max.getBalance()) max = u;
        }
        return ResponseEntity.status(200).body(max);
    }







}

