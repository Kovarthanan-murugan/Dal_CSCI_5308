package read;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import readandwriteinfile.ReadAndWriteInFile;

public class ReadClass {
    /**
     * This method is used to read the existing files with contains tables.
     * @param filename name of the file.
     */
   public static String read(String filename) {
       String resultString = "";
        try {
            File myObj = new File(filename+".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                resultString += data;
                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    return  resultString;
   }


    /**
     * This method is used to fetch and write in to the files.
     * @param append columns in the given SQL query.

     */
    public static void writefunction(String filename,String source, String content,String database,boolean append) {
        ReadAndWriteInFile ReadAndWriteInFile_obj = new ReadAndWriteInFile();
        try {
            File myObj = new File("E:\\java-traning\\Assignment\\assignment12\\" + filename + ".txt");
            if (myObj.createNewFile()) {

            } else {
                if (database.equals("database")) {

                    System.out.println("Database exist already cant create new one");
                }

            }
            FileWriter myWriter = new FileWriter(filename + ".txt", append);
            if (source.equals("login")) {
                content = "\n" + content;
            }
            myWriter.write(content);
            myWriter.close();
            System.out.println("Table saved successfully");

        } catch (IOException e) {
            System.out.println("Syntax error please check your query");
        }

        ReadAndWriteInFile_obj.exit(database);
    }
}