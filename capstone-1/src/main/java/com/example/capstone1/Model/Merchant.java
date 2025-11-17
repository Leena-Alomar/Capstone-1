package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {
    @NotEmpty(message="The Merchant ID Cannot Be Empty")
    private String id;
    @NotEmpty(message="The Merchant Name Cannot Be Empty")
    @Size(min=3,message="The Merchant Name Must Be At Least 3 Length")
    private String name;
}
