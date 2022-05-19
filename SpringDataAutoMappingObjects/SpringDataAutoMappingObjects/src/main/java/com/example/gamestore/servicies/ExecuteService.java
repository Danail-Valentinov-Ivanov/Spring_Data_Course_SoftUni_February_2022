package com.example.gamestore.servicies;

public interface ExecuteService {
     String REGISTER_COMMAND = "RegisterUser";
     String LOGIN_COMMAND = "LoginUser";
     String LOGOUT_COMMAND = "Logout";

    String execute(String commandLine);
}
