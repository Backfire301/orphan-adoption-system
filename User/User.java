package User;

import java.util.Scanner;

public abstract class User {
    protected String name, password;

    public User() {}

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    
    public boolean login(String password){
        return this.password.equals(password);
    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String p){
        password = p;
    }

    public void getFileInput(Scanner input){
        String temp = input.nextLine().trim();
        String[] temp2 = temp.split(",");
        name = temp2[0];
        password = temp2[1];
    }

    public String writeToFileString(){
        return String.format("%s,%s", name, password);
    }
}
