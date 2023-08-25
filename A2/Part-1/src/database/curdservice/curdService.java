package curdservice;

import balanceParanthesis.BalanceParanthesis;
import curdrepo.curdRepo;
import readandwriteinfile.ReadAndWriteInFile;
import read.ReadClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import curdservice.signup;
import curdservice.update;



class createTableMethods{
    /**
     * This method is used to print the table column values.
     * @param value array of values of Column.
     */
    static void printingIterator(String[] value){

        for (String i: value){
            System.out.println("inside printing function-->"+i);
        }
    }
    /**
     * This method is used to find the column in the SQL query.
     * @param query input SQL query.
     * @return processed query with separated column for create table operations.
     */
    static String[] columnFinder(String query){
        int columnStartIndex =  query.indexOf('(');
        int trimDecrementor = 1;
        String trimedQuery = query.substring(columnStartIndex+1,query.length()-trimDecrementor);
        String[] columnNamesDelimiterSplit = trimedQuery.split(",");
        System.out.println(columnNamesDelimiterSplit.length);
        return columnNamesDelimiterSplit;
    }
    /**
     * This method is used to find the column in the SQL query.
     * @param columns columns in the given SQL query.
     * @return processed query with column and its value.
     */
    static HashMap<Integer,String[]> columnAndTypeSeparator( String[] columns) {
        int columnListLength = columns.length;
        HashMap<Integer, String[]> columnsMap = new HashMap<>();

        for (int i = 0; i < columnListLength; i++) {
            String[] columnDetails ={"",""};
            String column = columns[i];
            String a = column.trim().split(" ")[0];
            String b = column.trim().split(" ")[1];
            System.out.println("column-->"+column.trim().split(" ")[0]);
            System.out.println("data-->"+column.trim().split(" ")[1]);
            columnDetails[0]=a;
            columnDetails[1]=b;
            printingIterator(columnDetails);
            columnsMap.put(i, columnDetails);
        }
        return columnsMap;
    }

}

class deleteTableMethods{
    /**
     * This method is process the column and index number of the row need to be changed;
     * @param file Name of the table to which insert operation need to be done.
     * @param conditionValueIndexInExistingTable Name of the table to which insert operation need to be done.
     */
    static void updateColumnValues( HashMap<String, ArrayList<String>> file, int conditionValueIndexInExistingTable) {

        for (String columnKey : file.keySet()) {
            ArrayList<String> w = file.get(columnKey);
            System.out.println("file-->" + w);
            w.set(conditionValueIndexInExistingTable, "000");
            System.out.println("update value"+w);

        }

    }
    /**
     * This method is used to write the data in the file after deleting the needed rows.
     * Processed sql query will be added to the respective column in the table.
     * @param file read file from the database.
     * @param tableName Name of the table to which insert operation need to be done.
     */
    static void writingDataInTheFile(HashMap<String, ArrayList<String>> file, String tableName) {


        String fileFinal = "";
        for (String i : file.keySet()) {
            String valueConcat = "";
            String columnConcat = i + ":";
            ArrayList<String> value = file.get(i);
            int count = 0;
            for (String item : value) {
                count++;
                if (!item.equals("000")) {
                    valueConcat += item;
                    if (count < value.size()) {
                        valueConcat += ",";
                    } else {
                        valueConcat += ";";
                    }

                }
            }
            fileFinal += columnConcat + valueConcat;
            System.out.println("concat-->" + fileFinal);

        }

        String createContentInFile = tableName + ":" + "[" + fileFinal + "]";
        System.out.println("concat-->" + createContentInFile);
        ReadAndWriteInFile fileWriter_obj = new ReadAndWriteInFile();
        String[] parameters = {tableName,"",createContentInFile,"delete"};
        fileWriter_obj.createFile(parameters,false);

    }
    /**
     * This method is used to find the column  and its value in the SQL query based on insert condition.
     * @param columns columns in the given SQL query.
     * @param tableName Name of the table to which insert operation need to be done.
     * @return processed query with column and its value to insert the value in the table column.
     */
    static HashMap<String, ArrayList<String>> columnAndValueForDeleteOperation(String columns, String tableName) {
        int indexOfTableName = columns.indexOf(tableName);
        //int trimindex = indexOfTableName + tableName.length() + 2;
        int trimindex = columns.indexOf('[')+1;
        String[] columnAndValue = columns.substring(trimindex, columns.indexOf(']')).split(";");
        HashMap<String, ArrayList<String>> columnAndValueMap = new HashMap<>();
        for (String i : columnAndValue) {
            System.out.println(i);
            String readValueSplit = i.split(":")[1];
            ArrayList<String> valueSplit = new ArrayList<>(Arrays.asList(readValueSplit.split(",")));
            columnAndValueMap.put(i.split(":")[0], valueSplit);
            System.out.println(valueSplit.get(0));


        }
        //System.out.println(columnAndValueMap);
        return columnAndValueMap;
    }
}



class insertIntoMethods{
    /**
     * This method is used to find the column in the SQL query.
     * @param query input SQL query.
     * @return processed query with separated column for the insert value in the column operations.
     */
    static HashMap<String,String> columnFinder(String query){
        int columnStartIndex =  query.indexOf('(');
        int valueIncrementor = 1;
        HashMap<String,String> insertColumnValue =  new HashMap<>();
        String trimedQuery = query.substring(columnStartIndex+1,query.indexOf(')'));
        String[] columnNamesDelimiterSplit = trimedQuery.split(",");
        String onlyValueQuery = query.replace(query.substring(0,query.indexOf(')')+valueIncrementor),"");
        System.out.println(onlyValueQuery);
        String trimedQueryValue = onlyValueQuery.substring(onlyValueQuery.indexOf('(')+valueIncrementor,onlyValueQuery.indexOf(')'));
        String[] valueDelimiterSplit = trimedQueryValue.split(",");
        if (columnNamesDelimiterSplit.length ==valueDelimiterSplit.length){
            for (int i=0;i<columnNamesDelimiterSplit.length;i++)
            {

                insertColumnValue.put(columnNamesDelimiterSplit[i].trim(),valueDelimiterSplit[i].trim());

            }

        }
        return insertColumnValue;
    }
    /**
     * This method is used to find the column in the SQL query based on insert condition.
     * @param columns columns in the given SQL query.
     * @return processed query with column and its value from the SQL statement.
     */
    static HashMap<Integer,String[]> columnAndTypeSeparator( String[] columns) {
        int columnListLength = columns.length;
        HashMap<Integer, String[]> columnsMap = new HashMap<>();

        for (int i = 0; i < columnListLength; i++) {
            String[] columnDetails ={"",""};
            String column = columns[i];
            String a = column.trim().split(" ")[0];
            String b = column.trim().split(" ")[1];
            System.out.println("column-->"+column.trim().split(" ")[0]);
            System.out.println("data-->"+column.trim().split(" ")[1]);
            columnDetails[0]=a;
            columnDetails[1]=b;
            columnsMap.put(i, columnDetails);
        }
        return columnsMap;
    }
    /**
     * This method is used to find the column  and its value in the SQL query based on insert condition.
     * @param columns columns in the given SQL query.
     * @param tableName Name of the table to which insert operation need to be done.
     * @return processed query with column and its value to insert the value in the table column.
     */
    static  HashMap<String,ArrayList<String>>  columnAndValueForInsertOperation(String columns,String tableName){
        int indexOfTableName =  columns.indexOf(tableName);
        int trimindex = indexOfTableName + tableName.length()+2;
        System.out.println(columns);
        String []columnAndValue = columns.substring(trimindex,columns.indexOf(']')).split(";");
        HashMap<String,ArrayList<String>> columnAndValueMap = new HashMap<>();
        for (String i: columnAndValue) {
            System.out.println(i);
            ArrayList<String> valueSplit = new ArrayList<>(Arrays.asList(i.split(":")[1]));

            columnAndValueMap.put(i.split(":")[0],valueSplit);


        }
        System.out.println(columnAndValueMap);
        return columnAndValueMap;
    }

}
class selectMethods{
    /**
     * This method is used to fetch the tables and print its value.
     * @param trimmedQueryColumSplit fetched column values.
     * @param file content in the tables.
     */
    static void printColumnValues(String [] trimmedQueryColumSplit,HashMap<String, ArrayList<String>> file){

        for(String columnNeed:trimmedQueryColumSplit){
            System.out.println("columnNeed--->"+columnNeed);

            ArrayList a = file.get(columnNeed.trim());
            System.out.println("a--->"+a);
            for (int columnValueIndex =0; columnValueIndex<a.size();columnValueIndex++){

                System.out.println(a.get(columnValueIndex));
            }


        }

    }
    /**
     * This method is used to find the column  and its value in the SQL query based on insert condition.
     * @param columns columns in the given SQL query.
     * @param tableName Name of the table to which insert operation need to be done.
     * @return processed query with column and its value to insert the value in the table column.
     */
    static HashMap<String, ArrayList<String>> columnAndValueForSelectOperation(String columns, String tableName) {
        int indexOfTableName = columns.indexOf(tableName);
        int trimIndexIncrementor =2;
        int trimindex = indexOfTableName + tableName.length() + trimIndexIncrementor;
        String[] columnAndValue = columns.substring(trimindex, columns.indexOf(']')).split(";");
        HashMap<String, ArrayList<String>> columnAndValueMap = new HashMap<>();
        for (String i : columnAndValue) {
            // System.out.println(i);
            String readValueSplit = i.split(":")[1];
            ArrayList<String> valueSplit = new ArrayList<>(Arrays.asList(readValueSplit.split(",")));
            columnAndValueMap.put(i.split(":")[0], valueSplit);
            //System.out.println(valueSplit.get(0));


        }
        //System.out.println(columnAndValueMap);
        return columnAndValueMap;
    }

}


public class curdService extends curdRepo{


    public void createdb(String query) {
        String[] createQueryCondition = {"CREATE","TABLE"};
        String dataBaseNameQuery = query.substring(query.indexOf("DATABASE"),query.indexOf(";"));
        String [] dataBaseNameSplit = dataBaseNameQuery.split(" ");
        String  dataBaseName = dataBaseNameSplit[1];
        String createContentInFile = "Database";
        ReadAndWriteInFile fileWriter_obj = new ReadAndWriteInFile();
        String[] parameters = {dataBaseName,"",createContentInFile,"database"};
        fileWriter_obj.createFile(parameters,false);

    }


    public void createTable(String query) {
        createTableMethods createTableMethods_obj = new createTableMethods();
        String[] createQueryCondition = {"CREATE","TABLE"};
        //String query ="CREATE TABLE Persons ( PersonID int, LastName varchar(255), FirstName varchar(255), Address varchar(255), City varchar(255))";
        System.out.println(query);
        if (query.toUpperCase().indexOf("CREATE") !=-1 )
        {
            boolean flag =true;
            String[] inputQuery = query.split(" ");
            //System.out.println(inputQuery);
            //System.out.println(inputQuery[0]);
            for (String k: inputQuery){
                //System.out.println(k);
            }
            for (String j:createQueryCondition) {
                flag = false;
                //System.out.println("query-->"+j);
                for (int i = 0; i < inputQuery.length; i++) {
                    //System.out.println("animal-->"+inputQuery[i]);
                    if (j.equals(inputQuery[i])) {
                        flag = true;
                        break;
                    }

                }
            }
            if (flag == true){
                String tableName = inputQuery[createQueryCondition.length];
                System.out.println("tableName-->"+tableName);
                BalanceParanthesis checkbalance =  new BalanceParanthesis();
                boolean isColumsyntax = checkbalance.balancedParenthensies(query);
                String columnListString ="";
                if (isColumsyntax == true){
                    String[] columns = createTableMethods_obj.columnFinder(query);
                    HashMap<Integer,String[]> columnAndTypeSeparator = createTableMethods_obj.columnAndTypeSeparator(columns);
                    System.out.println(columnAndTypeSeparator);
                    for (int i =0;i<columnAndTypeSeparator.size();i++) {
                        System.out.println(columnAndTypeSeparator.get(i)[0]);
                        columnListString += columnAndTypeSeparator.get(i)[0]+":"+i+";\n";

                    }

                    String createContentInFile = tableName+":"+"["+columnListString+"]";
                    ReadAndWriteInFile fileWriter_obj = new ReadAndWriteInFile();
                    String[] parameters = {tableName,"",createContentInFile,"table"};
                    fileWriter_obj.createFile(parameters,false);
                    System.out.println("kova-->"+createContentInFile);
                }

            }

        }

    }




    public void deleteTable(String query) {
        deleteTableMethods deleteTableMethods_obj = new deleteTableMethods();
        //String query = "UPDATE PERSONS SET Address='Alfred Schmidt', FirstName='Frankfurt',LastName='Frankfurt' WHERE City=city1;";
        //String query = "DELETE FROM PERSONS WHERE City=city1;";
        HashMap<String, String> updateNeedColumnAndValues = new HashMap<String, String>();
        HashMap<String, String> updateColumnCondition = new HashMap<String, String>();
        if (query.indexOf("DELETE") == 0) {

            String[] inputQuery = query.split(" ");
            String tableName = inputQuery[2];

            boolean setIndex = inputQuery[1].equals("FROM");
            int columnStartIndex = 4;
            int indexOFWhere = query.indexOf("WHERE");
            int indexofSemiColon = query.indexOf(";");
            if (setIndex) {
                System.out.println(tableName);
                String trimmedQuery = query.substring(indexOFWhere, query.indexOf(";"));
                if (query.indexOf("WHERE") != -1) {
                    String trimmedQueryConditionColumn = query.substring(indexOFWhere + 6, indexofSemiColon);
                    String[] trimmedQueryConditionColumnSplit = trimmedQueryConditionColumn.split(",");
                    for (String i : trimmedQueryConditionColumnSplit) {

                        updateColumnCondition.put(i.split("=")[0], i.split("=")[1]);
                    }
                    System.out.println(updateColumnCondition);

                    ReadClass read_obj = new ReadClass();
                    HashMap<String, ArrayList<String>> file = deleteTableMethods_obj.columnAndValueForDeleteOperation(read_obj.read(tableName), tableName);
                    //System.out.println(file);
                    String conditionValue  = "";
                    int conditionValueIndexInExistingTable = -1;
                    for (String conditionKey : updateColumnCondition.keySet()) {
                        conditionValue  = updateColumnCondition.get(conditionKey);
                        //System.out.println(conditionValue);
                        for (int indexOfFindValue = 0; indexOfFindValue < file.get(conditionKey).size(); indexOfFindValue++) {
                            ArrayList<String> a =file.get(conditionKey);
                            if (a.get(indexOfFindValue).equals(conditionValue)){
                                conditionValueIndexInExistingTable =indexOfFindValue;
                                System.out.println("index-->"+conditionValueIndexInExistingTable);
                            }
                        }
                    }

                    //Update the column value in the existing table
                    deleteTableMethods_obj.updateColumnValues(file,conditionValueIndexInExistingTable);
                    System.out.println("file-->"+file);
                    deleteTableMethods_obj.writingDataInTheFile(file,tableName);

                }


            }
        }

    }

    public void insertInto(String query) {
        insertIntoMethods insertIntoMethods_obj = new insertIntoMethods();
        //String query = "INSERT INTO Persons (PersonID, LastName, FirstName,Address,City) VALUES (7, 8, 9,10,11);";
        String[] insertQueryCondition = {"INSERT", "INTO"};
        //String query ="CREATE TABLE Persons ( PersonID int, LastName varchar(255), FirstName varchar(255), Address varchar(255), City varchar(255))";
        System.out.println(query);
        boolean flag = true;
        String[] inputQuery = query.split(" ");
        //System.out.println(inputQuery);
        //System.out.println(inputQuery[0]);
        for (String k : inputQuery) {
            //System.out.println(k);
        }
        for (String j : insertQueryCondition) {
            flag = false;
            //System.out.println("query-->"+j);
            for (int i = 0; i < inputQuery.length; i++) {
                //System.out.println("animal-->"+inputQuery[i]);
                if (j.equals(inputQuery[i])) {
                    flag = true;
                    break;
                }

            }
        }
        if (flag == true) {
            String tableName = inputQuery[insertQueryCondition.length];
            System.out.println("tableName-->" + tableName);
            BalanceParanthesis checkbalance = new BalanceParanthesis();
            boolean isColumsyntax = checkbalance.balancedParenthensies(query);
            String columnListString = "";
            if (isColumsyntax == true) {

                HashMap<String, String> columns = insertIntoMethods_obj.columnFinder(query);
//                HashMap<Integer,String[]> columnAndTypeSeparator = columnAndTypeSeparator(columns);
                System.out.println(columns);
                ReadClass read_obj = new ReadClass();

                HashMap<String, ArrayList<String>> file = insertIntoMethods_obj.columnAndValueForInsertOperation(read_obj.read(tableName), tableName);
                System.out.println(read_obj.read(tableName));
                for (String i : columns.keySet()) {
                    System.out.println(i);
                    ArrayList<String> appendnewvalues = file.get(i);
                    appendnewvalues.add(columns.get(i));

                    System.out.println(appendnewvalues);
                    System.out.println(file);
                }
                String fileFinal = "";
                for (String i : columns.keySet()) {
                    String valueConcat = "";
                    String columnConcat = i + ":";
                    ArrayList<String> value = file.get(i);
                    int count = 0;
                    for (String item : value) {
                        count++;
                        valueConcat += item;
                        if (count < value.size()) {
                            valueConcat += ",";
                        } else {
                            valueConcat += ";";
                        }

                        selectMethods selectMethods_obj = new selectMethods();
                        // String query = "SELECT FirstName,LastName FROM Persons;";
                        int selectIndex = query.indexOf("SELECT");
                        int selectLength = 7;
                        int fromIndex = query.indexOf("FROM");
                        int whereIndex = query.indexOf("WHERE");
                        String trimmedtableNameQuery="";
                        String[] trimmedQueryConditionColumnSplit;
                        HashMap<String, String> selectColumnCondition = new HashMap<String, String>();
                        if (selectIndex == 0) {

                            String trimmedColumnQuery = query.substring(selectIndex+selectLength, fromIndex);
                            if(whereIndex==-1)
                            {
                                trimmedtableNameQuery = query.substring(fromIndex, query.indexOf(";"));
                            }
                            else{
                                System.out.println("kk");

                            }

                            String[] trimmedQueryColumSplit = trimmedColumnQuery.split(",");
                            String tableName1 = trimmedtableNameQuery.split(" ")[1];
                            ReadClass read_obj1 = new ReadClass();
                            HashMap<String, ArrayList<String>> file1 = selectMethods_obj.columnAndValueForSelectOperation(read_obj1.read(tableName1), tableName1);
                            int indexofAsterick = trimmedColumnQuery.indexOf("*");
                            if (indexofAsterick==-1){
                                selectMethods_obj.printColumnValues(trimmedQueryColumSplit,file1);

                            }
                            else{

                                selectMethods_obj.printColumnValues(file1.keySet().toArray(new String[0]), file1);
                            }
                        }
                    }
                    fileFinal += columnConcat + valueConcat;
                    System.out.println("concat-->" + fileFinal);

                }

                String createContentInFile = tableName + ":" + "[" + fileFinal + "]";
                System.out.println("concat-->" + createContentInFile);
                ReadAndWriteInFile fileWriter_obj = new ReadAndWriteInFile();
                String[] parameters = {tableName, "", createContentInFile, "insert"};
                fileWriter_obj.createFile(parameters, false);

                System.out.println("kova-->" + createContentInFile);


            }

        }
    }


    public void select(String query) {
        selectMethods selectMethods_obj = new selectMethods();
        // String query = "SELECT FirstName,LastName FROM Persons;";
        int selectIndex = query.indexOf("SELECT");
        int selectLength = 7;
        int fromIndex = query.indexOf("FROM");
        int whereIndex = query.indexOf("WHERE");
        String trimmedtableNameQuery="";
        String[] trimmedQueryConditionColumnSplit;
        HashMap<String, String> selectColumnCondition = new HashMap<String, String>();
        if (selectIndex == 0) {

            String trimmedColumnQuery = query.substring(selectIndex+selectLength, fromIndex);
            if(whereIndex==-1)
            {
                trimmedtableNameQuery = query.substring(fromIndex, query.indexOf(";"));
            }
            else{
                System.out.println("kk");

            }

            String[] trimmedQueryColumSplit = trimmedColumnQuery.split(",");
            String tableName = trimmedtableNameQuery.split(" ")[1];
            ReadClass read_obj = new ReadClass();
            HashMap<String, ArrayList<String>> file = selectMethods_obj.columnAndValueForSelectOperation(read_obj.read(tableName), tableName);
            int indexofAsterick = trimmedColumnQuery.indexOf("*");
            if (indexofAsterick==-1){
                selectMethods_obj.printColumnValues(trimmedQueryColumSplit,file);

            }
            else{

                selectMethods_obj.printColumnValues(file.keySet().toArray(new String[0]), file);
            }
        }
    }


    public void update(String query) {

        update updateMethods_obj = new update();
        // String query = "UPDATE PERSONS SET Address='Alfred Schmidt', FirstName='Frankfurt',LastName='Frankfurt' WHERE City=4;";
        HashMap<String, String> updateNeedColumnAndValues = new HashMap<String, String>();
        HashMap<String, String> updateColumnCondition = new HashMap<String, String>();
        if (query.indexOf("UPDATE") == 0) {

            String[] inputQuery = query.split(" ");
            String tableName = inputQuery[1];

            int setIndex = query.indexOf("SET");
            int columnStartIndex = 4;
            int subStringStarter = 6;
            int indexOFWhere = query.indexOf("WHERE");
            int indexofSemiColon = query.indexOf(";");
            if (setIndex != -1) {
                System.out.println(tableName);
                String trimmedQuery = query.substring(setIndex + columnStartIndex, indexOFWhere);
                if (query.indexOf("WHERE") != -1) {
                    String trimmedQueryConditionColumn = query.substring(indexOFWhere + subStringStarter,indexofSemiColon);
                    String[] trimmedQueryConditionColumnSplit = trimmedQueryConditionColumn.split(",");
                    String[] trimmedQueryColumSplit = trimmedQuery.split(",");
                    for (String i : trimmedQueryConditionColumnSplit) {

                        updateColumnCondition.put(i.split("=")[0], i.split("=")[1]);
                    }
                    for (String i : trimmedQueryColumSplit) {
                        updateNeedColumnAndValues.put(i.split("=")[0].trim(), i.split("=")[1].trim());
                    }

                }
                ReadClass read_obj = new ReadClass();
                HashMap<String, ArrayList<String>> file = updateMethods_obj.columnAndValueForUpdateOperation(read_obj.read(tableName), tableName);
                String conditionValue  = "";
                int conditionValueIndexInExistingTable = -1;
                for (String conditionKey : updateColumnCondition.keySet()) {
                    conditionValue  = updateColumnCondition.get(conditionKey);
                    for (int indexOfFindValue = 0; indexOfFindValue < file.get(conditionKey).size(); indexOfFindValue++) {
                        ArrayList<String> a =file.get(conditionKey);
                        if (a.get(indexOfFindValue).equals(conditionValue)){
                            conditionValueIndexInExistingTable =indexOfFindValue;
                        }
                    }
                }

                //Update the column value in the existing table
                updateMethods_obj.updateColumnValues(updateNeedColumnAndValues,file,conditionValueIndexInExistingTable);
                System.out.println("file-->"+file);
                updateMethods_obj.writingDataInTheFile(file,tableName);

            }


        }
    }

    public boolean signInSignup() {
            signup signupMethod_obj = new signup();
            boolean loginFlag = false;
            System.out.println("-------------------");
            System.out.println("Type create to \"Create  New Account\"");
            System.out.println("Type open to \"Open existing schema\"");

            System.out.println("--------------------");
            Scanner schemaChoiceScanner = new Scanner(System.in);
            String schemaChoice = schemaChoiceScanner.nextLine();
            System.out.println(schemaChoice);
            if (schemaChoice.toUpperCase().equals("CREATE")) {
                System.out.println("Enter the user name");
                String userName = schemaChoiceScanner.nextLine();
                System.out.println("Enter the password");
                String password = schemaChoiceScanner.nextLine();
                System.out.println("Please type your Security question");
                String securityQuestion = schemaChoiceScanner.nextLine();
                System.out.println("Set answer for this security question" + "/n" + securityQuestion);
                String securityQuestionAnswer = schemaChoiceScanner.nextLine();
                System.out.println(securityQuestionAnswer);
                String str = "[\n" + userName + "." + "UserName: " + userName + ";\n"
                        + userName + "." + "Password: " + password + ";\n"
                        + userName + "." + "SecurityQuestion: " + securityQuestion + ";\n"
                        + userName + "." + "securityQuestionAnswer: " + securityQuestionAnswer + ";\n]\n";
                ReadAndWriteInFile fileOperation = new ReadAndWriteInFile();
                String[] parameters = {"userDetails","login",str,"sign"};
                fileOperation.createFile(parameters,true);

            } else if (schemaChoice.toUpperCase().equals("OPEN")) {
                signupMethod_obj.signin(schemaChoiceScanner);
                HashMap<String, String> userKeyValue = new HashMap<String, String>();
                userKeyValue = signupMethod_obj.getUserKeyValue();
                String userCredentialName = userKeyValue.get(signupMethod_obj.getUserName_input() + ".UserName");
                String userCredentialpass = userKeyValue.get(signupMethod_obj.getPassword_input() + ".Password");
                if (userCredentialName != null) {
                    loginFlag = true;
                    System.out.println("correct user name");
                    if (userCredentialpass.equals(signupMethod_obj.getPassword_input())) {
                        System.out.println("correct password");
                        System.out.println("Please answer your security question to login");
                        String security_input = schemaChoiceScanner.nextLine();
                        String userCredentialSecurity = userKeyValue.get(userCredentialName + ".securityQuestionAnswer");
                        if (userCredentialSecurity.equals(security_input)) {
                            System.out.println("login successfully");
                        } else {
                            loginFlag = false;
                            System.out.println("Wrong Security answer");
                        }
                    } else {
                        loginFlag =false;
                        System.out.println("Wrong credentias");
                    }

                } else {
                    loginFlag = false;
                    System.out.println("Wrong credentias");
                }

            }
            return loginFlag;
        }
    }


