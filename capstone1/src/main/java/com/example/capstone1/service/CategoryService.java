package com.example.capstone1.service;

import com.example.capstone1.model.Category;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Data
@Service

public class CategoryService {

    ArrayList<Category> categories = new ArrayList<>();

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void addCategories(Category category){
        categories.add(category);
    }

    public boolean setCategory(String id , Category category){
        for (int i =0; i < categories.size(); i++){
            if (categories.get(i).getId().equals(id)){
                categories.set(i , category);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCategory(String id){
        for (int i = 0; i < categories.size(); i++){
            if (categories.get(i).getId().equals(id)){
                categories.remove(i);
                return true;
            }
        }
        return false;
    }





}
