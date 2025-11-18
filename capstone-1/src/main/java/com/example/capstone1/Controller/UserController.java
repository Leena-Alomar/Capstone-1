package com.example.capstone1.Controller;

import com.example.capstone1.API.ApiResponse;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import com.example.capstone1.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity<?> getUser(){
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("The User Is Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id,@RequestBody User user,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated= userService.updateUser(id,user);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("The User Is Updated Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("The User Is Not Found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        boolean isDeleted= userService.deleteUser(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("The User Is Deleted Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("The User Is Not Found"));
    }


    @PutMapping("/buy/{id}/{productID}/{merchantID}")
    public ResponseEntity<?> BuyProduct(@PathVariable String id,@PathVariable String productID,@PathVariable String merchantID) {
        User purchaesd=userService.BuyProduct(id,productID,merchantID);
       if(purchaesd!=null){
           return ResponseEntity.status(200).body(purchaesd);
       }
        return ResponseEntity.status(400).body(new ApiResponse("The User Is Not Found"));
    }

    // Extra End Points ****************
    @GetMapping("/get/{id}/{productID}")
    public ResponseEntity<?> addProcductToWishlist(@PathVariable String id ,@PathVariable String productID){
        if(!userService.addProductToWishlist(id,productID).isEmpty()){
            return ResponseEntity.status(200).body(userService.addProductToWishlist(id,productID));
        }
        return ResponseEntity.status(400).body(new ApiResponse("The Product Is Not Found"));
    }

    // Extra End Points ****************
    @PutMapping("/transfer/{id}/{email}/{amount}")
    public ResponseEntity<?> TransferBalanceByUserId(@PathVariable String id, @PathVariable String email,@PathVariable double amount) {
        boolean isTransferd=userService.transferToUserById(id,email,amount);
        if(isTransferd){
            return ResponseEntity.status(200).body(new ApiResponse("The Balance Is Transfered To Given User Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("The User Is Not Found"));
    }

    // Extra End Points ****************
    @GetMapping("/get/recomended/{id}")
    public ResponseEntity<?> getRecomendedProduct(@PathVariable String id){
        if(!userService.getRecommenedProdcut(id).isEmpty()){
            return ResponseEntity.status(200).body(userService.getRecommenedProdcut(id));
        }
        return ResponseEntity.status(400).body(new ApiResponse("The Product Is Not Found"));
    }

}
