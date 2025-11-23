package com.example.capstone1.service;

import com.example.capstone1.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Data
@Service
@AllArgsConstructor
public class ProductService {


    ArrayList<Product> products = new ArrayList<>();

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public boolean updateProduct(String id, Product product){
        for (int i =0; i < products.size(); i++){
            if (products.get(i).getId().equals(id)){
                products.set(i , product);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(String id){
        for (int i =0; i < products.size(); i++){
            if (products.get(i).getId().equals(id)){
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public Product getProductById(String id){
        for (Product p : products){
            if (p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }

    //endpoint ProductServes
    public ArrayList<Product> searchProductsByName(String name) {
        ArrayList<Product> result = new ArrayList<>();
        String search = name.toLowerCase();

        for (Product p : products) {
            if (p.getName().toLowerCase().contains(search)) {
                result.add(p);
            }
        }
        return result;
    }

    // change the price of a proudct
    public boolean changePrice(String productId, double newPrice) {
        if (newPrice <= 0) return false;
        for (Product p : products) {
            if (p.getId().equals(productId)) {
                p.setPrice(newPrice);
                return true;
            }
        }
        return false;
    }

    // Discount
    public boolean applyDiscount(String productId, double percentage) {
        for (Product p : products) {
            if (p.getId().equals(productId)) {
                double discount = p.getPrice() * (percentage / 100.0);
                double newPrice = p.getPrice() - discount;
                if (newPrice <= 0) return false;
                p.setPrice(newPrice);
                return true;
            }
        }
        return false;
    }


}


