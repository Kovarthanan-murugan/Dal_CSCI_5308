package curdservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class signup {

    private boolean loginFlag =false;
    private HashMap<String, String> userKeyValue = new HashMap<String, String>();

    public String userName_input;
    public String password_input;

    public boolean isLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(boolean loginFlag) {
        this.loginFlag = loginFlag;
    }

    public HashMap<String, String> getUserKeyValue() {
        return userKeyValue;
    }

    public void setUserKeyValue(HashMap<String, String> userKeyValue) {
        this.userKeyValue = userKeyValue;
    }

    public String getUserName_input() {
        return userName_input;
    }

    public void setUserName_input(String userName_input) {
        this.userName_input = userName_input;
    }

    public String getPassword_input() {
        return password_input;
    }

    public void setPassword_input(String password_input) {
        this.password_input = password_input;
    }



    void signin(Scanner schemaChoiceScanner){

        System.out.println("!!!LoginPage!!");
        System.out.println("Enter the user name");
        String userName_input = schemaChoiceScanner.nextLine();
        System.out.println("Enter the password");
        String password_input = schemaChoiceScanner.nextLine();

        try {

            int splitIndex;
            int endIndex;
            String key = "";
            String value = "";
            String userName = "";
            int valueSubStringIncrementor =2;
            int keySubStringStarter = 0;
            File myObj = new File("userDetails.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //System.out.println(data);
                if (!data.equals("[") && !data.equals("]") && data.length() != 0) {
                    splitIndex = data.indexOf(":");
                    endIndex = data.indexOf(";");
                    key = data.substring(keySubStringStarter, splitIndex);
                    value = data.substring(splitIndex + valueSubStringIncrementor, endIndex);
                    userKeyValue.put(key, value);

                }


            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
