package com.example.capstone1.service;

import com.example.capstone1.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
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

    //endpoint
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


}


