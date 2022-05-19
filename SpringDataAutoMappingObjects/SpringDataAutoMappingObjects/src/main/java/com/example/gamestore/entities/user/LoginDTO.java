package com.example.gamestore.entities.user;

import com.example.gamestore.exceptions.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginDTO {
    private String email;
    private String password;

    public LoginDTO(String[] commandRow) {
        this.email = commandRow[1];
        this.password = commandRow[2];
        validate();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    private void validate() {
        String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new ValidationException("Incorrect email.");
        }

        String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$";
        Pattern patternPassword = Pattern.compile(regexPassword);
        Matcher matcherPassword = patternPassword.matcher(password);
        if (!matcherPassword.matches()) {
            throw new ValidationException("Password must be at least 6 symbols and must contain" +
                    " at least 1 uppercase, 1 lowercase letter and 1 digit!");
        }
    }
}
