package Person;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import utils.ChildStatus;

public class Child extends Person{

    private Parent parent;
    private ChildStatus status;
    private String parentName;

    public Child() {}

    public Child(Scanner input) {
        getInput(input);
        status = ChildStatus.AVAILABLE;
    }

    public Child(String firstName, String lastName, int year, int month, int day, String gender, ChildStatus status){
        super(firstName, lastName, year, month, day, gender);
        this.status = status;
    }

    public String getAdoptionStatus(){
        if (status.equals(ChildStatus.AVAILABLE))
            return "Not Adopted";

        return "Adopted";
    }

    public void addParent(Parent p){
        parent = p;
        parentName = parent.getFullName();
        status = ChildStatus.ADOPTED;
        p.addChildren(this);
    }

    public String getParentFullName(){
        return parent.getFullName();
    }

    @Override
    public void displayInfo(){
        super.displayInfo();
        System.out.println("======================");
        System.out.println("Adoption Status: " + getAdoptionStatus());
        if (status.equals(ChildStatus.ADOPTED))
            System.out.println("Parent Name: " + parentName);
        
        System.out.println("======================");
        System.out.println();
        System.out.println();
    }

    public void displayInfoBrief(){
        System.out.printf("Child Name: %s, age: %d, Adoption Status: %s\n", getFullName(), age, status);
    }

    public void getFileInput(Scanner input, ArrayList<Parent> parents){
        super.getFileInput(input);
        status = ChildStatus.valueOf(input.next());
        if (status.equals(ChildStatus.ADOPTED)){
            parentName = input.next() + " " + input.next();
            for (Parent p : parents){
                if (parentName.equals(p.getFullName())){
                    parent = p;
                    p.addChildren(this);
                    break;
                }
            }
        }
        input.nextLine();
    }

    public void addParent(String parentFullName, ArrayList<Parent> parents){
            if (status.equals(ChildStatus.ADOPTED)){
            for (Parent p : parents){
                if (parentFullName.equals(p.getFullName())){
                    parent = p;
                    p.addChildren(this);
                    break;
                }
            }
        }        
    }

    @Override
    public String writeToFileString(){
        String temp = super.writeToFileString() + String.format(" %s", status);
        
        if (status.equals(ChildStatus.ADOPTED)){
            temp += String.format(" %s", parentName);
        }

        return temp;
    }

    @Override
    public final void getInput(Scanner input){
        super.getInput(input);
        while (age > 17){
            int year = LocalDate.now().getYear() - 18;
            System.out.printf("Error: a child's age should not be above 18 years old(must be dates after %d)\n\n", year);
            super.getInputBirthday(input);
        }
    }
}
