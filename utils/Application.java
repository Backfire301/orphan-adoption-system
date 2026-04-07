package utils;
import Person.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Application{
    private static int formNum = 1;
    private String appID;
    private Child child;
    private Parent parent;
    private String childName;
    private String parentName;
    private AppStatus status;
    private LocalDateTime applyDate;


    public Application() {}

    public Application(ArrayList<Child> children, ArrayList<Parent> parents, Scanner input, String parentNameTemp) {
        appID = "A" + String.format("%03d", formNum++);
        getInput(children, parents, input, parentNameTemp);
        status = AppStatus.PENDING;
        applyDate = LocalDateTime.now();
    }

    
    public Application(Child c, Parent p){
        appID = "A" + String.format("%03d", formNum++);
        child = c;
        parent = p;
        status = AppStatus.PENDING;
        applyDate = LocalDateTime.now();
    }

    public AppStatus getAppStatus(){
        return status;
    }

    public String getAppID(){
        return appID;
    }

    public String getParentFullName(){
        return parent.getFullName();
    }

    public void approveAdoption(ArrayList<Child> c){
        child.addParent(parent);
        for (Child tempC : c){
            if (child.equals(tempC)){
                tempC = child;
            }
        }
        status = AppStatus.APPROVED;
    }

    public void rejectAdoption(){
        status = AppStatus.REJECTED;
    }

    public void displayInfo(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = applyDate.format(formatter);
        System.out.println("Application ID: " + appID);
        System.out.println("Adoptee: ");

        if (child != null)
            child.displayInfo();

        else
            System.out.println("Information unvailable, data removed");
        System.out.println();
        System.out.println("Adopter name:");

        if (parent != null)
            parent.displayInfo();

        else
            System.out.println("Information unvailable, data removed");

        System.out.println();
        System.out.println("Application current status: " + status);
        System.out.println("Date: " + formattedDate);
    }

    public void displayInfoBrief(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = applyDate.format(formatter);
        System.out.println("Application ID: " + appID);
        System.out.println("Adoptee name: " + childName);
        System.out.println("Adopter name: " + parentName);
        System.out.println("Application status: " + status);
        System.out.println("Date applyed: " + formattedDate);
    }

    public void getFileInput(Scanner input, ArrayList<Application> applications, ArrayList<Child> children, ArrayList<Parent> parents){
        appID = "A" + String.format("%03d", formNum++);
        String temp = input.nextLine().trim();
        String[] tempArray = temp.split(",");
        childName = tempArray[0];

        for (Child c : children){
            if (childName.equals(c.getFullName())){
                child = c;
                break;
            }
        }
        parentName = tempArray[1];

        for (Parent p : parents){
            if (parentName.equals(p.getFullName())){
                parent = p;
                break;
            }
        }
        status = AppStatus.valueOf(tempArray[2]);
        applyDate = LocalDateTime.parse(tempArray[3]);
    }

    public String writeToFileString(){
        return String.format("%s,%s,%s,%s", childName, parentName, status, applyDate);
    }

    public final void getInput(ArrayList<Child> children, ArrayList<Parent> parents, Scanner input, String tempName){
        if (children.isEmpty() || parents.isEmpty()){
            System.out.println("No children/parent in database, unable to create form");
            return;
        }

        System.out.println("Select child:");
        int i = 0;
        for (Child c : children){
            System.out.printf("%d. %s\n", ++i, c.getFullName());
        }
        System.out.println();
        int choice = Checker.intChecker(input, "Your choice: ", 1, children.size());

        child = children.get(choice - 1);
        childName = child.getFullName();

        for (Parent p : parents){
            if (p.getFullName().equals(tempName)){
                parent = p;
                parentName = p.getFullName();
            }
        }
        System.out.println();
    }
}