package com.example.gamestore.servicies.impl;

import com.example.gamestore.entities.user.LoginDTO;
import com.example.gamestore.entities.user.RegisterDTO;
import com.example.gamestore.entities.user.User;
import com.example.gamestore.servicies.ExecuteService;
import com.example.gamestore.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExecuteServiceImpl implements ExecuteService {

    private final UserService userService;

    @Autowired
    public ExecuteServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public String execute(String commandLine) {
        String[] commandRow = commandLine.split("\\|");
        String commandName = commandRow[0];

        String output = switch (commandName) {
            case REGISTER_COMMAND -> {
                RegisterDTO registerDTO = new RegisterDTO(commandRow);
                User user = userService.register(registerDTO);
                yield String.format("%s was registered\n", user.getFullName());
            }
            case LOGIN_COMMAND -> {
                LoginDTO loginDTO = new LoginDTO(commandRow);
                Optional<User> user = userService.login(loginDTO);
                if (user.isPresent()){
                    yield String.format("Successfully logged in %s\n", user.get().getFullName());
                } else {
                    yield "Incorrect username / password!";
                }
            }
            case LOGOUT_COMMAND -> {
                Optional<User> loggedUser = userService.getCurrentUser();
                if (loggedUser.isPresent()){
                    userService.logout();
                    yield String.format("User %s successfully logged out\n", loggedUser.get().getFullName());
                } else {
                    yield "Cannot log out. No user was logged in.";
                }
            }
            default -> "unknown command";
        };
        return output;
    }
}
