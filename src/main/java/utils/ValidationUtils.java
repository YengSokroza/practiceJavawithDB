package utils;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ValidationUtils {
    public static String validateInput(Scanner input,String message, String regex){
        while (true){
            System.out.print(message);

            String userInput = input.nextLine();

            Pattern pattern = Pattern.compile(regex);
            if(pattern.matcher(userInput).matches()){
                return userInput;
            }else {
                System.out.println( "Invalid Format!" );
            }
        }
    }
}
