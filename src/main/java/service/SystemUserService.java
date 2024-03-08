package service;

import repository.SystemUserRepository;

import java.util.Scanner;

public class SystemUserService {
    private final SystemUserRepository systemUserRepository;

    public SystemUserService(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }

    public boolean authenticateUser(Scanner input){
        String username;
        String password;
        System.out.print("Enter your username: ");
        username = input.nextLine();
        System.out.print("Enter your password: ");
        password = input.nextLine();
        if(systemUserRepository.authenticateUser(username,password)){
            System.out.println("\nLogin successful!\n");
        }else{
            System.out.println("\nIncorrect username or password. Please try again.\n");
        }
        return systemUserRepository.authenticateUser(username,password);
    }
}
