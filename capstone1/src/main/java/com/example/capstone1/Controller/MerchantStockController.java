package com.example.capstone1.Controller;
import com.example.capstone1.API.ApiResponse;
import com.example.capstone1.model.MerchantStock;
import com.example.capstone1.service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
@Valid
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/ecommerce/merchantstockservice")
public class MerchantStockController {

    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllMerchantStockServiceC (){
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStocks());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMerchantStockServiceC (@Valid @RequestBody MerchantStock merchantStock , Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(200).body("Added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMerchantStockServiceC (@PathVariable String id ,
                                                          @Valid @RequestBody MerchantStock merchantStock , Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isUpdated = merchantStockService.updateMerchantStock(id , merchantStock);
        if (!isUpdated) {
            return ResponseEntity.status(400).body("not updated");
        }
        return ResponseEntity.status(200).body("Updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMerchantStock(@PathVariable String id) {
        boolean deleted = merchantStockService.deleteMerchantStock(id);
        if (!deleted) {
            return ResponseEntity.status(400).body(new ApiResponse("not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("stock deleted"));
    }

    @PostMapping("/add-stock/{productId}/{merchantId}/{amount}")
    public ResponseEntity<?> addMoreStock(@PathVariable String productId, @PathVariable String merchantId, @PathVariable int amount) {
        boolean isAdded = merchantStockService.addMoreStock(productId, merchantId, amount);
        if (!isAdded) {
            return ResponseEntity.status(400).body(new ApiResponse("not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Stock added "));
    }


    // endpoint3
    @GetMapping("/stock/{productId}/{merchantId}")
    public ResponseEntity<?> checkStock(@PathVariable String productId, @PathVariable String merchantId) {
        MerchantStock stock = merchantStockService.get(productId, merchantId);
        if (stock == null) {
            return ResponseEntity.status(400).body(new ApiResponse("Not found"));
        }
        return ResponseEntity.status(200).body(stock.getStock());
    }



}
