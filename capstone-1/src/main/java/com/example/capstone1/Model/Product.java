package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    @NotEmpty(message="The Product ID Cannot Be Empty")
    private String id;
    @NotEmpty(message="The Product Name Cannot Be Empty")
    @Size(min=3,message="The Product Name Must Be At Least 3 Length")
    private String name;
    @Positive(message = "The Product Price Must Be Positive Value")
    private double price;
    @NotEmpty(message="The Category ID Cannot Be Empty")
    private String categoryID;
}
