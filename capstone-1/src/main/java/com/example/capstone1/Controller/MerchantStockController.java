package com.example.capstone1.Controller;

import com.example.capstone1.API.ApiResponse;
import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Service.MerchantService;
import com.example.capstone1.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchantstock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity<?> getMerchantStock(){
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStock());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(200).body(new ApiResponse("The Merchant Stock Is Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMerchantStock(@PathVariable String id,@RequestBody MerchantStock merchantStock,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated= merchantStockService.updateMerchantStock(id, merchantStock);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("The Merchant Stock Is Updated Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("The Merchant Stock Is Not Found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMerchantStock(@PathVariable String id){

        boolean isDeleted= merchantStockService.deleteMerchantStock(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("The Merchant Stock Is Deleted Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("The Merchant Stock Is Not Found"));
    }


    @PutMapping("/update/{id}/{productID}/{merchantID}/{stock}")
    public ResponseEntity<?> updateStock(@PathVariable  String id,@PathVariable String productID,@PathVariable String merchantID ,@PathVariable int stock){
        boolean isUpateStock= merchantStockService.updateStock(id,productID,merchantID,stock);
        if(isUpateStock){
            return ResponseEntity.status(200).body(new ApiResponse("The Merchant Stock Value Is Updated Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("The Merchant Stock Is Not Found"));
        }

    // Extra End Points ****************
    @GetMapping("/check/{id}/{productID}/{merchantID}/{stock}")
    public ResponseEntity<?> CheckStock(@PathVariable  String id,@PathVariable String productID,@PathVariable String merchantID ,@PathVariable int stock){
        boolean isUpateStock= merchantStockService.updateStock(id,productID,merchantID,stock);
        if(isUpateStock){
            return ResponseEntity.status(200).body(new ApiResponse("The Product Is available"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("The Product Stock Is Out Of Stock"));
    }

}
