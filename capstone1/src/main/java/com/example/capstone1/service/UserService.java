package com.example.capstone1.service;
import com.example.capstone1.model.MerchantStock;
import com.example.capstone1.model.Product;
import com.example.capstone1.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
@Data
@Service
@AllArgsConstructor
public class UserService {
    private final ProductService productService;
    private final MerchantStockService stockService;
    ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users;
    }


    public void addUser(User user) {
        users.add(user);
    }

    public boolean updateUser(String id, User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    public User getUserById(String id) {
        for (User u : users) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }
    public boolean buyProduct(String userId, String productId, String merchantId){

        User user = getUserById(userId);
        Product product = productService.getProductById(productId);
        MerchantStock stock = stockService.get(productId, merchantId);

        if (user == null || product == null || stock == null) {
            return false;
        }

        if (stock.getStock() <= 0) {
            return false;
        }

        if (user.getBalance() < product.getPrice()) {
            return false;
        }

        user.setBalance(user.getBalance() - product.getPrice());
        stock.setStock(stock.getStock() - 1);

        return true;
    }
}



