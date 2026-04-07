package Person;
import User.*;
import java.time.LocalDate;
import java.util.*;

public class Parent extends Person{

    private ArrayList<Child> children;
    private String[] details;
    private final String[] detailsName = {"Maritial Status",
                                    "current occupation",
                                    "highest educational background",
                                    "religion"};

    public Parent() {
        children = new ArrayList<>();
        details = new String[4];
    }

    public Parent(Scanner input){
        children = new ArrayList<>();
        details = new String[4];
        getInput(input);
    }

    public Parent(String firstName, String lastName, int year, int month, int day, String gender, String[] details){
        super(firstName, lastName, year, month, day, gender);
        children = new ArrayList<>();
        this.details = details;
    }

    @Override
    public void getFileInput(Scanner input){
        super.getFileInput(input);
        String temp = input.nextLine().trim();
        details = temp.split(",");
    }
    
    public void addChildren(Child c){
        children.add(c);
    }

    public String getChildrenFullName(int index){
        return children.get(index).getFullName();
    }

    public int getChildrenNum(){
        return children.size();
    }

    public void removeChildren(int index){
        children.remove(index);
    }

    @Override
    public void displayInfo(){
        super.displayInfo();
        System.out.println("Number of children in their care: " + children.size());
        System.out.println("======================");
        System.out.println("Parent details:");

        for (int i = 0; i < 4; i++)
            System.out.println(detailsName[i] + ": " + details[i]);


        if (!children.isEmpty()){
            System.out.println("======================");
            System.out.println("Children adopted: ");

            int i = 1;
            for (Child c : children)
                System.out.printf("%d. %s\n", i++, c.getFullName());
        }

        System.out.println();
        System.out.println();
    }

    public void displayInfoBrief(){
        System.out.printf("Parent Name: %s, age: %d, children in their care: %d\n", getFullName(), age, getChildrenNum());
    }

    @Override
    public final void getInput(Scanner input){
        super.getInput(input);
        while (age < 18){
            int year = LocalDate.now().getYear() - 17;
            System.out.printf("Error: any person younger than 18 years old cannot be a parent(must be dates before %d)\n\n", year);
            super.getInputBirthday(input);
        }
        for (int i = 0; i < 4; i++){
            System.out.print(detailsName[i] + ": ");
            details[i] = input.nextLine().trim();
        }
    }

    @Override
    public String writeToFileString(){
        String temp = super.writeToFileString();
        for (String d : details){
            temp += (d + ",");
        }
        return temp;
    }

    public NormalUser setUser(){
        return new NormalUser(getFullName(), getFullName());
    }
}
