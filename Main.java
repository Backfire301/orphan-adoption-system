import User.*;
import java.util.*;
import utils.*;

public class Main {
    public static void main(String[] args) {

        //==================setting up scanner, system manager, and Input interface========================================
        Scanner input = new Scanner(System.in);
        SystemManager mgr = new SystemManager();
        input.useDelimiter("\\s+");
        Input user;

        //===================================================password detection==============================================
        if (!mgr.checkPassword(input)){
            System.out.println("Incorrect password detected, shutting down system...");
            input.close();
            System.exit(1);
        }

        if (mgr.user.getName().equals("admin")){
            user = new Admin(mgr.user.getName(), mgr.user.getPassword());
        }

        else {
            user = new NormalUser(mgr.user.getName(), mgr.user.getPassword());
        }


        //=======================================after successful entering the system===================================
        System.out.printf("Welcome, user %s!", user.getName());

        user.displayMenu(input, mgr);
        
        //=========================================write data back to files==============================================
        mgr.writeToFiles();
        input.close();
    }
}
