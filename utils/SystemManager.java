package utils;
import Person.*;
import User.*;
import java.util.*;

public class SystemManager {
    public ArrayList<Child> children = new ArrayList<>();
    public ArrayList<Parent> parents = new ArrayList<>();
    public ArrayList<Application> applications = new ArrayList<>();
    public ArrayList<User> users = new ArrayList<>();
    public User user;





    //===================================FUNCTIONS INSIDE SYSTEMMANAGER=====================================================

    public SystemManager(){
        FileFunctions.getFiles(children, parents, applications, users);
    }

    public void writeToFiles(){
        FileFunctions.writeFiles(children, parents, applications, users);
    }

    public boolean checkPassword(Scanner input){
        System.out.println("Welcome to our Child Adoption System! Enter your username to start...");
        String username;
        boolean exit = false;
        do { 
            System.out.print("Username: ");
            username = input.nextLine().trim();
            for (User u : users){
                if (u.getName().equals(username)){
                    exit = true;
                    user = u;
                    break;
                }
            }  
            if (!exit)
                System.out.println("No username of this type found, please re-enter another username.");

        } while (!exit);

        return (Checker.passwordChecker(input, user));     
    }

    //===================================FUNCTIONS INSIDE SYSTEMMANAGER=====================================================








    

    //===================================FUNCTIONS REGARDING VIEWING CHILDREN=====================================================
    public void viewAllChildren(){
        if (!children.isEmpty()){
            int i = 1;
            for (Child c : children){
                System.out.println("======================");
                System.out.printf("Child %d:\n", i++);
                c.displayInfo();
                System.out.println();
            } 
        }
        else
            System.out.println("No children in database");
    }

    public void viewAllChildrenBrief(){
        if (!children.isEmpty()){
            int i = 1;
            System.out.println("======================");
            for (Child c : children){
                System.out.printf("%d:", i++);
                c.displayInfoBrief();
            } 
        }
        else
            System.out.println("No children in database");
    }

    //===================================FUNCTIONS REGARDING VIEWING CHILDREN=====================================================










    //===================================FUNCTIONS REGARDING VIEWING PARENTS=====================================================


    public void viewAllParents(){
        if (!parents.isEmpty()){
            int i = 1;
            System.out.println("======================");
            for (Parent p : parents){
                System.out.printf("Parent %d:\n", i++);
                p.displayInfo();
                System.out.println();
            } 
        }
        else
            System.out.println("No parents in database");
    }

    public void viewAllParentsBrief(){
        if (!parents.isEmpty()){
            int i = 1;
            System.out.println("======================");
            for (Parent p : parents){
                System.out.printf("%d.", i++);
                p.displayInfoBrief();
                System.out.println();
            } 
        }
        else
            System.out.println("No parents in database");
    }


    //===================================FUNCTIONS REGARDING VIEWING PARENTS=====================================================








    //===================================FUNCTIONS REGARDING VIEWING APPLICATIONS=====================================================

    public void viewAllApplicationsBrief(){
        if (!applications.isEmpty()){
            int i = 1;
            System.out.println("======================");
            for (Application a : applications){
                System.out.printf("Application %d:\n", i++);
                a.displayInfoBrief();
                System.out.println();
            } 
        }
        else
            System.out.println("No applications in database");

    }

    public void viewAllApplications(){
        if (!applications.isEmpty()){
            int i = 1;
            System.out.println("======================");
            for (Application a : applications){
                System.out.printf("Application %d:\n", i);
                a.displayInfo();
                System.out.println();
            } 
        }
        else
            System.out.println("No applications in database");
    }

    
//===================================FUNCTIONS REGARDING VIEWING APPLICATIONS=====================================================









//===================================FUNCTIONS REGARDING VIEWING ALL PAGES=====================================================


    
    public void viewAllPage(int type, Scanner input){
                if (type == 0 && !children.isEmpty()){
                viewAllChildrenBrief();
                System.out.println();
                System.out.println("======================================");
                int choice = Checker.intChecker(input, "Enter an index to view more info on the child, or press 0 to return to previous page: ", 0, children.size());
                System.out.println();
                System.out.println("======================================");              
                if (choice != 0){
                    children.get(choice - 1).displayInfo();
                    input.nextLine();
                    System.out.println("Returning to previous page, press enter to continue...");
                }

            }

            else if (type == 1 && !parents.isEmpty()){
                viewAllParentsBrief();
                System.out.println();
                System.out.println("======================================");
                int choice = Checker.intChecker(input, "Enter an index to view more info on the parent, or press 0 to return to previous page: ", 0, parents.size());
                System.out.println();
                System.out.println("======================================");              
                if (choice != 0){
                    parents.get(choice - 1).displayInfo();
                    System.out.println("Returning to previous page, press enter to continue...");
                    input.nextLine();
                }
            }

            else {
                System.out.println("Database currently empty, add some values and they'll show up here!");             
                System.out.println("======================================");  
                System.out.println("Returning to previous page, press enter to continue...");
                input.nextLine();
            }
        }

//===================================FUNCTIONS REGARDING VIEWING ALL PAGES=====================================================









//===================================FUNCTIONS REGARDING UPDATING APPLICATION STATUS(APPROVE/REJECT)=====================================================

    public void updateApplicationStatus(Scanner input){
        ArrayList<Application> pendingApps = new ArrayList<>();
        if (!applications.isEmpty()){
            int i = 1;
            for (Application a : applications){
                if (a.getAppStatus() == AppStatus.PENDING){
                    System.out.println("==================");
                    System.out.printf("Application %d:\n", i++);
                    a.displayInfoBrief();
                    pendingApps.add(a);
                    System.out.println();
                }
            }
            if (pendingApps.isEmpty()){
                System.out.println("No currently pending applications available.");
                return;
            }
        }
        else{
            System.out.println("No applications in database.");

            return;
        }


        int choice = Checker.intChecker(input, 
                               "Choose the index number of the application that you would like to review, or type 0 to exit to previous menu: ", 
                            0, pendingApps.size());
        if (choice != 0){
            System.out.println("==================");
            pendingApps.get(choice-1).displayInfo();
            System.out.println();
            System.out.println("==================");
            input.nextLine();
            if (Checker.choiceChecker(input, "Would you like to approve this application?(Y/N) ")){
                for (Application a : applications){
                    if (a.equals(pendingApps.get(choice-1))){
                        a.approveAdoption(children);
                        System.out.println("Application successfully approved");
                    }
                }
            }

            else {
                for (Application a : applications){
                    if (a.equals(pendingApps.get(choice-1))){
                        a.rejectAdoption();
                        System.out.println("Application successfully rejected");
                    }
                }
            }
        }
    }

    //===================================FUNCTIONS REGARDING UPDATING APPLICATION STATUS(APPROVE/REJECT)=====================================================







    //===================================FUNCTION REGARDING CHOICES TO UPDATE(ADD, VIEW, REMOVE)=====================================================

    public int updateChoice(Scanner input, String type){
        System.out.println("======================================");
        System.out.println("Edit page for " + type);
        System.out.println("======================================");
        System.out.println("What would you like to do?");
        System.out.println("1. View database");
        System.out.println("2. Add");
        System.out.println("3. remove");
        System.out.println("0. return to previous menu");
        System.out.println("======================================");
        return Checker.intChecker(input, "Your choice: ", 0, 3);
    }

    //===================================FUNCTION REGARDING CHOICES TO UPDATE(ADD, VIEW, REMOVE)=====================================================









//===================================FUNCTION REGARDING REMOVING ITEMS=====================================================


    public void removeItem(Scanner input, int type){
        if (type == 0 && !children.isEmpty()){
            System.out.println();
            System.out.println();
            viewAllChildren();
            int choice = Checker.intChecker(input, "select the index of the child that you would like to remove, or type 0 to return: ",
                         0, children.size());
            
            if (choice != 0){
                System.out.printf("Removing child from index %d with full name of %s\n", choice, children.get(choice - 1).getFullName());
                input.nextLine();
                boolean check = Checker.choiceChecker(input, "Confirm? Y/N ");
                if (check){
                    children.remove(choice - 1);
                    System.out.println("======================================");
                    System.out.println("Child Successfully Removed!");
                    System.out.println("======================================");
                }
            }
        }
        else if (type == 1 && !parents.isEmpty()){
            System.out.println();
            System.out.println();
            viewAllParents();
            int choice = Checker.intChecker(input, "select the index of the parent that you would like to remove, or type 0 to return: ",
                         0, parents.size());

            if (choice != 0){
                System.out.printf("Removing parent from index %d with full name of %s\n", choice, parents.get(choice - 1).getFullName());
                input.nextLine();
                boolean check = Checker.choiceChecker(input, "Confirm? Y/N ");
                if (check){
                    parents.remove(choice - 1);
                    System.out.println("======================================");
                    System.out.println("Parent Successfully Removed!");
                    System.out.println("======================================");
                }
            }
        }
        else if (type == 2 && !applications.isEmpty()){
            viewAllApplicationsBrief();
            int choice = Checker.intChecker(input, "select the index of the application that you would like to remove, or type 0 to return: ",
                         0, applications.size());

            if (choice != 0){
                System.out.printf("Removing application from index %d with ID of %s\n", choice, applications.get(choice - 1).getAppID());
                input.nextLine();
                boolean check = Checker.choiceChecker(input, "Confirm? Y/N ");
                if (check){
                    applications.remove(choice - 1);
                }
            }
        }

        else{
            input.nextLine();
            System.out.println("Database empty, nothing to remove");
            System.out.println("======================================"); 
            System.out.println("Returning to previous page, press enter to continue...");
            input.nextLine();

        }
    }
//===================================FUNCTION REGARDING REMOVING ITEMS=====================================================










//===================================FUNCTIONS REGARDING CHILDREN & PARENT EDIT PAGES=====================================================


    public void updateChildren(Scanner input){
        int choice;
        do{
            choice = updateChoice(input, "Child");
            switch (choice){

                case 1:
                viewAllPage(0, input);
                input.nextLine();
                break;

                case 2:
                children.add(new Child(input));
                System.out.println("======================================");
                System.out.println("Child Successfully Added!");
                System.out.println("======================================");
                System.out.println();
                break;

                case 3:
                removeItem(input, 0);
                System.out.println();

                break;
            }
        } while (choice != 0);
    }

    public void updateParent(Scanner input){
        int choice;
        do { 
            choice = updateChoice(input, "Parent");
            switch(choice){

                case 1:
                viewAllPage(1, input);
                input.nextLine();
                break;

                case 2:
                parents.add(new Parent(input));
                users.add(parents.get(parents.size() - 1).setUser());
                System.out.println("======================================");
                System.out.println("Parent Successfully Added!");
                System.out.println("======================================");
                System.out.println();
                break;

                case 3:
                removeItem(input, 1);
                System.out.println();
                break;
            }            
        } while (choice != 0);
        
    }
//===================================FUNCTIONS REGARDING CHILDREN & PARENT EDIT PAGES=====================================================








    //===================================FUNCTIONS REGARDING USER PAGES=====================================================


    public void viewUserApplications(String name){
         if (!applications.isEmpty()){
            int i = 1;
            for (Application a : applications){
                if (a.getParentFullName().equals(name)){
                    System.out.printf("Application %d:\n", i++);
                    a.displayInfoBrief();
                    System.out.println();
                } 
            }
        }
        else
            System.out.println("No applications in database");
    }

    public void addApplication(Scanner input, User user){
        applications.add(new Application(children, parents, input, user.getName()));
        System.out.println("Application successfully created!");
    }

    public void removeApplication(String name, Scanner input){
         if (!applications.isEmpty()){
            int i = 1;
            ArrayList<Application> tempApp = new ArrayList<>();
            for (Application a : applications){
                if (a.getParentFullName().equals(name)){
                    System.out.printf("Application %d:\n", i++);
                    a.displayInfoBrief();
                    tempApp.add(a);
                    System.out.println();
                }
            }

            if (!tempApp.isEmpty()){
                int choice = Checker.intChecker(input, "select the index of the Application that you would like to remove, or type 0 to return: ",
                            0, tempApp.size());
                if (choice != 0){
                    System.out.printf("Removing application from index %d with ID of %s\n", choice, tempApp.get(choice - 1).getAppID());
                    input.nextLine();
                    boolean check = Checker.choiceChecker(input, "Confirm? Y/N ");
                    if (check){
                        
                        applications.remove(tempApp.get(choice - 1));
                        System.out.println("Application Successfully removed!");
                        System.out.println("======================================");
                    }
                }
            }

            else
                System.out.println("No pending applications in database");
            
        }
                
        else
            System.out.println("No applications in database");
                
    }

//===================================FUNCTIONS REGARDING USER PAGES=====================================================

}
