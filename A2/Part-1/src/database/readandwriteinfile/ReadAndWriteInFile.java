package readandwriteinfile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import read.ReadClass;

public class ReadAndWriteInFile {



    public void exit(String database){

        if (!database.equals("database") && !database.equals("create")) {
            System.out.println("Want to end the transaction?");
            Scanner transaction = new Scanner(System.in);
            String transactionStatus = transaction.nextLine();

            if (transactionStatus.toUpperCase().equals("END TRANSACTION")) {
                System.exit(0);
            }


        }
    }

    /**
     * This method is used to call by other classes to create a file.
     * @param append columns in the given SQL query.
     * @param createParameters multiple parameters to create and write new file
     */

    public void createFile(String[] createParameters, boolean append) {
        ReadClass ReadClass_obj = new ReadClass();
        String changes = "";
        String filename = createParameters[0];
        String source = createParameters[1];
        String content = createParameters[2];
        String database = createParameters[3];
        String[] parameters = {filename,source,content,database};
        if (database.equals("update") || database.equals("delete") || database.equals("insert")) {
            System.out.println("Want to commit the changes or Rollback");
            Scanner changesNeed = new Scanner(System.in);
            changes = changesNeed.nextLine();
            if (changes.toUpperCase().equals("COMMIT")) {
                ReadClass_obj.writefunction(filename,source,content,database,append);
            } else {

                System.out.println("Rolled Back Successfully");
            }

        } else {

            ReadClass_obj.writefunction(filename,source,content,database,append);

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

        }

    }
}
