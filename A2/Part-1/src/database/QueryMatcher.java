import java.util.*;
import curdservice.*;
public class QueryMatcher {
    /**
     * This method is used to handle the query to process curd operation commands;
     */
    public void operations(){

        System.out.println("Write your SQL Query here ");

        Scanner queryInputScanner = new Scanner(System.in);
        String  queryInput = queryInputScanner.nextLine();
        System.out.println(queryInput);
        String upperCased = queryInput.toUpperCase();
        if (upperCased.indexOf("CREATE") !=-1 && upperCased.indexOf("DATABASE") !=-1)
        {
            curdService curdService_obj = new curdService();
            curdService_obj.createdb(queryInput);
        }
        if (upperCased.indexOf("CREATE") == 0 && upperCased.indexOf("DATABASE") ==-1){
            curdService curdService_obj = new curdService();
            curdService_obj.createTable(queryInput);
        } else if (upperCased.indexOf("SELECT") == 0) {
            curdService curdService_obj = new curdService();
            curdService_obj.select(queryInput);

        }else if (upperCased.indexOf("UPDATE") == 0) {
            System.out.println("BeginTransaction");
            curdService curdService_obj = new curdService();
            curdService_obj.update(queryInput);
        }else if (upperCased.indexOf("DELETE") == 0) {
            System.out.println("BeginTransaction");
            curdService curdService_obj = new curdService();
            curdService_obj.deleteTable(queryInput);
        }else if (upperCased.indexOf("INSERT") == 0) {
            System.out.println("BeginTransaction");
            curdService curdService_obj = new curdService();
            curdService_obj.insertInto(queryInput);

        }



    }
}
