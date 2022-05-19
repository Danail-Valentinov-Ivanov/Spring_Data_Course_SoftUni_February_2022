package com.example.gamestore;

import com.example.gamestore.exceptions.ValidationException;
import com.example.gamestore.servicies.ExecuteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class CommandRunner implements CommandLineRunner {
    private final ExecuteService executeService;

    public CommandRunner(ExecuteService executeService) {
        this.executeService = executeService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        String result;
        try {
            result = executeService.execute(command);
        } catch (ValidationException exception){
            result = exception.getMessage();
        }
        System.out.println(result);
    }
}
