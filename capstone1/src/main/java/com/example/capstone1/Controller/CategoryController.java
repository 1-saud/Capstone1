package com.example.capstone1.Controller;

import com.example.capstone1.API.ApiResponse;
import com.example.capstone1.model.Category;
import com.example.capstone1.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Valid
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/ecommerce/categoryservice")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllCategoryService(){
        return ResponseEntity.status(200).body(categoryService.getCategories());
    }


    @PostMapping("/add")
    public ResponseEntity<?> addCategoryService(@RequestBody @Valid Category category , Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        categoryService.addCategories(category);
        return ResponseEntity.status(200).body("Added");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategoryService(@PathVariable String id
            , @RequestBody @Valid Category category,Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isUpdated = categoryService.setCategory(id , category);
        if (!isUpdated){
            return ResponseEntity.status(400).body("not found");
        }
        return ResponseEntity.status(200).body("updated");

    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategoryService(@PathVariable String id){
        boolean isDeleted = categoryService.deleteCategory(id);
        if (!isDeleted){
            return ResponseEntity.status(400).body("not deleted");
        }
        return ResponseEntity.status(200).body("deleted");
    }







}
