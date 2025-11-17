package com.example.capstone1.service;

import com.example.capstone1.model.MerchantStock;
import com.example.capstone1.model.Product;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
@Data
@Service
public class MerchantStockService {

    private final ProductService product;

    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    public ArrayList<MerchantStock> getMerchantStocks() {
        return merchantStocks;
    }

    public void addMerchantStock(@Valid MerchantStock merchantStock){
        merchantStocks.add(merchantStock);
    }

    public boolean updateMerchantStock(String id , MerchantStock merchantStock){
        for (int i = 0; i < merchantStocks.size(); i++){
            if (merchantStocks.get(i).getId().equals(id)){
                merchantStocks.set(i , merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchantStock(String id){
        for (int i = 0; i < merchantStocks.size(); i++ ){
            if (merchantStocks.get(i).getId().equals(id)){
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }

    // سؤال 12

    public MerchantStock get(String productId, String merchantId){
        for (MerchantStock s : merchantStocks){
            if (s.getProductid().equals(productId) &&
                    s.getMerchantid().equals(merchantId)){
                return s;
            }
        }
        return null;
    }

    public boolean addMoreStock(String productId , String merchantId, int amount) {
        MerchantStock stock = get(productId, merchantId);

        if (stock == null) return false;
        if (amount <= 0) return false;

        stock.setStock(stock.getStock() + amount);
        return true;
     }
}


