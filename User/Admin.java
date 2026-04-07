package User;
import java.util.Scanner;
import utils.*;

public class Admin extends User implements Input{

    public Admin() {}

    public Admin(String name, String password){
        super(name, password);
    }
    @Override
    public void displayMenu(Scanner input, SystemManager mgr){
        boolean repeat = true;
        while (repeat){
            System.out.println("\n--------------------------------------------------- ADMIN PAGE ---------------------------------------------------");
            System.out.println("1. View All Applications");
            System.out.println("2. Approve / Reject Application");
            System.out.println("3. Edit Children details");
            System.out.println("4. Edit Parent details");
            System.out.println("5. Exit");
            System.out.println("\n------------------------------------------------------------------------------------------------------------------");
            int option = Checker.intChecker(input, "Choose option: ", 1, 5);
            input.nextLine();

            switch (option){

                case 1:
                System.out.println("======================================");
                mgr.viewAllApplicationsBrief();
                System.out.println("======================================");
                System.out.println("Returning to previous page, press enter to continue...");
                input.nextLine();
                break;

                case 2:
                System.out.println("======================================");
                mgr.updateApplicationStatus(input);
                System.out.println("======================================");
                System.out.println("Returning to previous page, press enter to continue...");
                input.nextLine();
                break;

                case 3:
                mgr.updateChildren(input);
                System.out.println("======================================");
                System.out.println("Returning to previous page, press enter to continue...");
                input.nextLine();
                break;

                case 4:
                mgr.updateParent(input);
                System.out.println("======================================");
                System.out.println("Returning to previous page, press enter to continue...");
                input.nextLine();
                break;

                case 5:
                System.out.println("Program terminated. Have a nice day!");
                repeat = false;
            } 
        }       
    }
}
