package Person;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Person{
    protected String firstName, lastName;
    protected int age;
    protected LocalDate dateOfBirth;
    protected String gender;

    public Person() {}

    public Person(String firstName, String lastName, int year, int month, int day, String gender) {
        this.firstName = firstName;
        this.lastName = lastName; 
        this.gender = gender;
        calcAge(year, month, day);
    }

    public final void calcAge(int year, int month, int day){
        dateOfBirth = LocalDate.of(year, month, day);
        age = LocalDate.now().getYear() - dateOfBirth.getYear();
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public void displayInfo(){
        System.out.println("Full name: " + getFullName());
        System.out.println("Age: " + age);
        System.out.println("Date of birth: " + dateOfBirth);
        System.out.println("Gender: " + gender);
    }

    public void getFileInput(Scanner input){
        firstName = input.next();
        lastName = input.next();
        gender = input.next();
        int year = input.nextInt();
        int month = input.nextInt();
        int day = input.nextInt();
        calcAge(year, month, day);
    }

    public String writeToFileString(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy MM dd");
        return String.format("%s %s %s %s ", firstName, lastName, gender, dateOfBirth.format(format));
    }

    public void getInputBirthday(Scanner input){
        boolean repeat = true;
        while (repeat){
            try {

                System.out.print("Enter the birthday of this person(yyyy mm dd): ");
                
                int year = 0, month = 0, day = 0;
                
                if (input.hasNextInt())
                    year = input.nextInt();
                else{
                    input.nextLine();
                    throw new Exception();
                }

                if (input.hasNextInt())
                    month = input.nextInt();

                else{
                    input.nextLine();
                    throw new Exception();
                }

                if (input.hasNextInt())
                    day = input.nextInt();

                else{
                    input.nextLine();
                    throw new Exception();
                }

                input.nextLine();

                calcAge(year, month, day);
                repeat = false;          

            } catch (Exception e) {
                System.out.println("Error: date invalid. Please re-enter a valid date.\n");
            }
        }

    }

    public void getInput(Scanner input){
        System.out.print("Enter first name followed by last name(e.g. john doe): ");
        firstName = input.next();
        lastName = input.next();
        input.nextLine();
        boolean repeat = false;
        do { 
                System.out.print("Enter gender(M/F): ");
                gender = input.next();
                input.nextLine();
                switch (gender){
                    case "M":
                    case "m":
                    gender = "Male";
                    repeat = false;
                    break;

                    case "F":
                    case "f":
                    gender = "Female";
                    repeat = false;
                    break;

                    default:
                    System.out.println("Incorrect input detected, please try again.");
                    repeat = true;
                    break;
                }
        } while (repeat);

        getInputBirthday(input);
    }
}