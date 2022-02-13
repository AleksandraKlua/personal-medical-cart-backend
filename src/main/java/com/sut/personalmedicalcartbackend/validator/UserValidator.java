package com.sut.personalmedicalcartbackend.validator;

import com.sut.personalmedicalcartbackend.model.UserRegistrationRequest;
import com.sut.personalmedicalcartbackend.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private final UserDAO userDAO;

    @Autowired
    public UserValidator(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean supports(Class<?> clazz) {
        return UserRegistrationRequest.class.equals(clazz);
    }

    public void validate(Object o, Errors errors) {
        UserRegistrationRequest user = (UserRegistrationRequest) o;
        if(userDAO.getUser(user.getPhoneNumber()) != null) {
           errors.reject("The user with this phone number already exists");
        }
        if (!user.getPassword().equals(user.getPasswordConfirmation())) {
            errors.reject("Passwords don't match");
        }
    }
}
