package com.sut.personalmedicalcartbackend.controller;

import com.sut.personalmedicalcartbackend.model.UserRegistrationRequest;
import com.sut.personalmedicalcartbackend.dao.UserDAO;
import com.sut.personalmedicalcartbackend.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {
    private final UserValidator userValidator;
    private final UserDAO userDAO;

    @Autowired
    public AuthController(UserValidator userValidator, UserDAO userDAO) {
        this.userValidator = userValidator;
        this.userDAO = userDAO;
    }

    @PostMapping(value = "/users/registration", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> registerUser(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest, BindingResult result) {
        HttpStatus responseStatus = HttpStatus.CREATED;

        userValidator.validate(userRegistrationRequest, result);

        HashMap<String, Object> map = new HashMap<>();

        if (result.hasErrors()) {
            map.put("errorCode", result.getAllErrors().get(0).getCode());
            map.put("userMessage", result.getAllErrors().get(0).getDefaultMessage());
            responseStatus = HttpStatus.BAD_REQUEST;
        } else {
            userDAO.addUser(userRegistrationRequest);
        }

        return new ResponseEntity<>(map, responseStatus);
    }

    @PostMapping(value = "/users/login")
    public void login() {
        //TODO: signing in
    }
}
