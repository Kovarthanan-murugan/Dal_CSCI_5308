package curdservice;

import readandwriteinfile.ReadAndWriteInFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class update{

    /**
     * This method is process the column and index number of the row need to be changed;
     * @param updateNeedColumnAndValues read file from the database.
     * @param file Name of the table to which insert operation need to be done.
     * @param conditionValueIndexInExistingTable Name of the table to which insert operation need to be done.
     */
    static void updateColumnValues(HashMap<String, String> updateNeedColumnAndValues, HashMap<String, ArrayList<String>> file, int conditionValueIndexInExistingTable){

        for (String columnKey : updateNeedColumnAndValues.keySet()) {
            ArrayList<String> w = file.get(columnKey);
            w.set(conditionValueIndexInExistingTable,updateNeedColumnAndValues.get(columnKey));

        }
    }
    /**
     * This method is used to write the data in the file.
     * Processed sql query will be added to the respective column in the table.
     * @param file read file from the database.
     * @param tableName Name of the table to which insert operation need to be done.
     */
    static void writingDataInTheFile(HashMap<String, ArrayList<String>> file,String tableName ){


        String fileFinal ="";
        for (String i:file.keySet()){
            String valueConcat ="";
            String columnConcat = i+":";
            ArrayList<String> value = file.get(i);
            int count =0;
            for (String item:value){
                count++;
                valueConcat += item;
                if(count < value.size()){
                    valueConcat += ",";
                }
                else{
                    valueConcat += ";";
                }

            }
            fileFinal += columnConcat+valueConcat;
            System.out.println("concat-->"+fileFinal);

        }

        String createContentInFile = tableName+":"+"["+fileFinal+"]";
        System.out.println("concat-->"+createContentInFile);
        ReadAndWriteInFile fileWriter_obj = new ReadAndWriteInFile();
        String[] parameters = {tableName,"",createContentInFile,"update"};
        fileWriter_obj.createFile(parameters,false);

        System.out.println("kova-->"+createContentInFile);
    }

    /**
     * This method is used to find the column  and its value in the SQL query based on update condition.
     * @param columns columns in the given SQL query.
     * @param tableName Name of the table to which update operation need to be done.
     * @return processed query with column and its value to insert the value in the table column.
     */
    static HashMap<String, ArrayList<String>> columnAndValueForUpdateOperation(String columns, String tableName) {
        int indexOfTableName = columns.indexOf(tableName);

        int trimindex = indexOfTableName + tableName.length() + 2;
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