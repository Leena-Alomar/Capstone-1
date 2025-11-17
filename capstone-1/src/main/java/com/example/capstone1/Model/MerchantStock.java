package com.example.capstone1.Model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {
    @NotEmpty(message="The ID Cannot Be Empty")
    private String id;
    @NotEmpty(message="The Product ID Cannot Be Empty")
    private String productID;
    @NotEmpty(message="The Merchant ID Cannot Be Empty")
    private String merchantID;
    @NotNull(message="The Stock Cannot Be Null")
    @Min(value = 10, message = "The Stock Must Be at Least a Value of 10")
    private int stock;
}
