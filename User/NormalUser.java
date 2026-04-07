package User;
import java.util.Scanner;
import utils.*;


public class NormalUser extends User implements Input{

    public NormalUser() {}

    public NormalUser(String name, String password){
        super(name, password);
    }

    public void changePassword(Scanner input, SystemManager mgr){
        System.out.println("Current password: " + password);
        if (Checker.choiceChecker(input, "Would you like to change your password? (Y/N) ")){
            System.out.print("Enter your new password here: ");
            password = input.nextLine().trim();
            System.out.println(password);
            for (User u : mgr.users){
                if (name.equals(u.getName())){
                    u.setPassword(password);
                }
            }
            System.out.println("New Password Set!");
        }
    }

    @Override
    public void displayMenu(Scanner input, SystemManager mgr){
    boolean repeat = true;
        while (repeat){
            System.out.println("\n--------------------------------------------------- USER PAGE ---------------------------------------------------");
            System.out.println("1. View Submitted Applications");
            System.out.println("2. Create Application");
            System.out.println("3. Remove application");
            System.out.println("4. Edit Password");
            System.out.println("5. Exit");
            System.out.println("\n------------------------------------------------------------------------------------------------------------------");
            int option = Checker.intChecker(input, "Choose option: ", 1, 5);
            input.nextLine();

            switch (option){

                case 1:
                System.out.println("======================================");
                mgr.viewUserApplications(name);
                System.out.println("======================================");
                System.out.println("Returning to previous page, press enter to continue...");
                input.nextLine();
                break;

                case 2:
                System.out.println("======================================");
                mgr.addApplication(input, this);
                System.out.println("======================================");
                input.nextLine();
                break;

                case 3:
                System.out.println("======================================");
                mgr.removeApplication(name, input);
                System.out.println("Returning to previous page, press enter to continue...");
                input.nextLine();
                break;

                case 4:
                changePassword(input, mgr);
                System.out.println("Returning to previous page, press enter to continue...");
                input.nextLine();
                break;

                case 5:
                repeat = false;
            } 
        }       
    }
}
