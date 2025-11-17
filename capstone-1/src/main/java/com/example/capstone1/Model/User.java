package com.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

@Data
@AllArgsConstructor
public class User {
    @NotEmpty(message="The User ID Cannot Be Empty")
    private String id;
    @NotEmpty(message="The User Name Cannot Be Empty")
    @Size(min=5,message="The User Name Must Be At Least Length of 5")
    private String userName;
    @NotEmpty(message="The Password Cannot Be Empty")
    @Size(min=6,message="The Password  Must Be At Least Length of 6")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$",message = "The Password Must Contain Both Digits and Characters")
    private String password;
    @NotEmpty(message = "The Email Cannot Be Empty")
    @Email(message = "The Email Must Be a Valid Format: name@gmail.com")
    private String email;
    @NotEmpty(message="The User Role Cannot Be Empty")
    @Pattern(regexp = "^(Admin|Customer)$",message = "The User Role Must Be Admin or Customer")
    private String role;
    @NotNull(message = "The User Balance Cannot Be Null")
    @Positive(message = "The Balance Must Be a Positive Value")
    private double balance;
}
