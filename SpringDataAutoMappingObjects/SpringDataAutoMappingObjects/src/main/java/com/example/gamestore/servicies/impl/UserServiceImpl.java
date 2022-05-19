package com.example.gamestore.servicies.impl;

import com.example.gamestore.entities.user.LoginDTO;
import com.example.gamestore.entities.user.RegisterDTO;
import com.example.gamestore.entities.user.User;
import com.example.gamestore.exceptions.ValidationException;
import com.example.gamestore.repository.UserRepository;
import com.example.gamestore.servicies.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private Optional<User> currentUser;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.currentUser = null;
    }

    @Override
    public User register(RegisterDTO registerDTO) {
//        if (currentUser != null){
//            throw new ValidationException("User was logged in.");
//        }
        ModelMapper mapper = new ModelMapper();
        User registrationOfUser = mapper.map(registerDTO, User.class);

        long userCount = userRepository.count();

        if (userCount == 0){
            registrationOfUser.setAdministrator(true);
        }
        return userRepository.save(registrationOfUser);
    }

    @Override
    public Optional<User> login(LoginDTO loginDTO) {
        if (currentUser.isPresent()){
            throw new ValidationException("User was logged in.");
        }
        Optional<User> user = userRepository
                .findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
        if (user.isPresent()){
            currentUser = user;
        }
        return user;
    }

    @Override
    public void logout() {
        if (!currentUser.isPresent()){
            throw new ValidationException("Cannot log out. No user was logged in.");
        }
        currentUser = null;
    }

    public Optional<User> getCurrentUser() {
        if (!currentUser.isPresent()){
            throw new ValidationException("No user was logged in.");
        }
        return currentUser;
    }
}
