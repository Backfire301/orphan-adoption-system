package utils;
import User.*;
import java.util.*;

public abstract class Checker {
    public static int intChecker(Scanner input, String question, int lowerBound, int upperBound){
        boolean repeat = true;
        int num = 0;
        do { 
            try {
                System.out.print(question);
                    num = input.nextInt();
                    if (num < lowerBound || num > upperBound)
                        throw new IndexOutOfBoundsException();
                    
                    repeat = false;
            } 

            catch (InputMismatchException e) {
                System.out.print("Error: only integer inputs allowed");
                repeat = true;
                input.nextLine();
            }

            catch (IndexOutOfBoundsException e){
                System.out.printf("Error: integer out of bounds(%d-%d)", lowerBound, upperBound);
                repeat = true;
            }

            finally {
                System.out.println();
            }
        } while (repeat);

        return num;
    }

    public static boolean passwordChecker(Scanner input, User user){
        String tempPass;
        for (int i = 4; i >= 0; i--){
            System.out.print("Enter password: ");
            tempPass = input.nextLine().trim();

            if (!user.login(tempPass) && i != 0){
                System.out.printf("Incorrect password, please try again(%d/5 tries remaining).\n", i);
            }
            else if (user.login(tempPass))
                return true;
        }
        return false;
    }

    public static boolean choiceChecker(Scanner input, String question){
        boolean repeat = true;
        String temp = "";
        do { 
                System.out.print(question);
                temp = input.nextLine().trim(); 
                temp = temp.toUpperCase();
                if (!temp.equals("Y") && !temp.equals("N")){
                    System.out.println("Incorrect input, please try again.");
                    repeat = true;
                }

                else
                    repeat = false;
        } while (repeat);

        return temp.equals("Y");
    }
}