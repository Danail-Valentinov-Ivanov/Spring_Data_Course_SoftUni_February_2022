package com.example.gamestore.servicies;

import com.example.gamestore.entities.user.LoginDTO;
import com.example.gamestore.entities.user.RegisterDTO;
import com.example.gamestore.entities.user.User;

import java.util.Optional;

public interface UserService {
    User register(RegisterDTO registerDTO);

    Optional<User> login(LoginDTO loginDTO);

    void logout();

    Optional<User> getCurrentUser();
}
