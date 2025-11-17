package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
    @NotEmpty(message="The Category ID Cannot Be Empty")
    private String id;
    @NotEmpty(message="The Category Name Cannot Be Empty")
    @Size(min=3,message="The Category Name Must Be At Least 3 Length")
    private String name;

}
