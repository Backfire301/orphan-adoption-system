package utils;
import Person.*;
import User.*;
import java.io.*;
import java.util.*;

public abstract class FileFunctions {
    public static void getFiles(ArrayList<Child> children, ArrayList<Parent> parents, ArrayList<Application> applications, ArrayList<User> users){
        File childrenFiles = new File("data/children.txt");
        File parentFiles = new File("data/parents.txt");
        File appFiles = new File("data/applications.txt");
        File userFiles = new File("data/users.txt");
        try {
                Scanner input = new Scanner(parentFiles);

                for (int i = 0; input.hasNextLine(); i++){
                    parents.add(new Parent());
                    parents.get(i).getFileInput(input);
                }
                input.close();

                input = new Scanner(childrenFiles);
                for (int i = 0; input.hasNextLine(); i++){
                    children.add(new Child());
                    children.get(i).getFileInput(input, parents);
                }
                input.close();

                input = new Scanner(appFiles);
                for (int i = 0; input.hasNextLine(); i++){
                    applications.add(new Application());
                    applications.get(i).getFileInput(input, applications, children, parents);
                }
                input.close();

                input = new Scanner(userFiles);
                for (int i = 0; input.hasNextLine(); i++){
                    if (i == 0)
                        users.add(new Admin());
                    
                    else
                        users.add(new NormalUser());

                    users.get(i).getFileInput(input);
                }
                input.close();
        } 
        
        catch (IOException e) {
            System.out.println("Error while reading files");
        }
    }

    public static void writeFiles(ArrayList<Child> children, ArrayList<Parent> parents, ArrayList<Application> applications, ArrayList<User> users){
        try {
            FileWriter writeToChildren = new FileWriter("data/children.txt");
            FileWriter writeToParent = new FileWriter("data/parents.txt");
            FileWriter writeToApplication = new FileWriter("data/applications.txt");
            FileWriter writeToUser = new FileWriter("data/users.txt");
            
            if (!children.isEmpty()){
                for (Child c : children)
                    writeToChildren.write(c.writeToFileString() + System.lineSeparator());
            }

            else{
                writeToChildren.write("");
            }

            if(!parents.isEmpty()){
                for (Parent p : parents)
                    writeToParent.write(p.writeToFileString() + System.lineSeparator());
            }
            else{
                writeToParent.write("");
            }

            if(!applications.isEmpty()){
                for (Application a : applications)
                    writeToApplication.write(a.writeToFileString() + System.lineSeparator());
            }

            else{
                writeToApplication.write("");
            }
            if (!users.isEmpty()){
                for (User u : users){
                    writeToUser.write(u.writeToFileString() + System.lineSeparator());
                }
            }
            else{
                writeToUser.write("");
            }

            writeToUser.close();
            writeToChildren.close();
            writeToParent.close();
            writeToApplication.close();
        } 
        
        catch (IOException e) {
            System.out.println("Error when writing into file");
        }

    }
}
