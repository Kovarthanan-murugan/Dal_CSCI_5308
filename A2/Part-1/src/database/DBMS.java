import curdservice.curdService;

/**
 *This program is a databaseManagement System
 *It does all curd operations in a database
 *It implements transaction controls by following ACID properties
 * */


public class DBMS {
    /**
     * This method is a main method to all operations in a database;
     */

    public static void main(String args[]) {

        curdService signInSignup_obj = new curdService();

        if (signInSignup_obj.signInSignup() == true) {


                QueryMatcher operations_obj = new QueryMatcher();
                operations_obj.operations();


            }


        }
    }




