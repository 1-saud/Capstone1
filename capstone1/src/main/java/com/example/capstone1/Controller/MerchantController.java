package com.example.capstone1.Controller;

import com.example.capstone1.API.ApiResponse;
import com.example.capstone1.model.Merchant;
import com.example.capstone1.service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Valid
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/ecommerce/merchantservice")
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllMerchant() {
        return ResponseEntity.status(200).body(merchantService.getMerchants());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMerchantC(@RequestBody @Valid Merchant merchant , Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body(new ApiResponse("merchant added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMerchantC(@PathVariable String id,
                                             @RequestBody @Valid Merchant merchant , Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isUpdated = merchantService.updateMerchant(id, merchant);
        if (!isUpdated){
            return ResponseEntity.status(400).body(new ApiResponse("merchant not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("merchant updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMerchantC (@PathVariable String id){
        boolean isDeleted = merchantService.deleteMerchant(id);
        if (!isDeleted){
            return ResponseEntity.status(400).body(new ApiResponse("merchant not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("merchant deleted"));
    }
}
