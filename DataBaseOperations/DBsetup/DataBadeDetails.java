package JavaPizzaria.DataBaseOperations.DBsetup;

public class DataBadeDetails {
    // Database connection details
    protected final static String DB_BASE_URL = "jdbc:mysql://127.0.0.1:3306";
    protected final static String USER = "root";
    protected final static String PASSWORD = "password";
    protected final static String DB_NAME = "pizzariarenato";
    protected final static String TABLE_NAME1 = "Customers";
    protected final static String TABLE_NAME2 = "Sales";
    protected final static String DB_URL = DB_BASE_URL + "/" + DB_NAME;
}
