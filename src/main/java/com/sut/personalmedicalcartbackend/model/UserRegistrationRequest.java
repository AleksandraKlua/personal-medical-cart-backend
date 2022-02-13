package com.sut.personalmedicalcartbackend.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserRegistrationRequest {
    @NotEmpty(message = "Name can't be empty")
    private String name;

    @NotEmpty(message = "Surname can't be empty")
    private String surname;

    private String patronymic;

    @NotEmpty(message = "Phone number can't be empty")
    @Pattern(regexp = "^(\\+7{1})?[\\d\\- ]{10}$", message = "Enter phone number in format: +79999999999")
    private String phoneNumber;

    @Size(min = 8, max = 32, message = "Password size more than 8 and less than 32 symbols")
    @Pattern(regexp = "(?=.+[a-z])(?=.+[A-Z])(?=.+[0-9])(?=.+[!@#$%^&*()?.,:;]).{8,32}", message = "Password must contain at least one letter in upper and lower case, number and at least one symbol: !@#$%^&*()?.,:;")
    private String password;

    @Size(min = 8, max = 32, message = "Password size more than 8 and less than 32 symbols")
    @Pattern(regexp = "(?=.+[a-z])(?=.+[A-Z])(?=.+[0-9])(?=.+[!@#$%^&*()?.,:;]).{8,32}", message = "Password must contain at least one letter in upper and lower case, number and at least one symbol: !@#$%^&*()?.,:;")
    private String passwordConfirmation;

    @Email
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Wrong format for the e-mail")
    private String email;
}
