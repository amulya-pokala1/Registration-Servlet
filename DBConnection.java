import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;  
public class DBConnection {
	private static DBConnection singleInstance;
	  private static java.sql.Connection dbConn;
	  private DBConnection() throws ClassNotFoundException, SQLException
	  {
		  Class.forName("com.mysql.jdbc.Driver");  
		  dbConn=DriverManager.getConnection(  
				  "jdbc:mysql://localhost:3306/registration","root","root");  
	  }
	  public static Connection getInstance() throws ClassNotFoundException, SQLException
	  {
	    if(singleInstance == null)
	    {
	      synchronized (DBConnection.class)
	      {
	        if(singleInstance == null)
	        {
	          singleInstance = new DBConnection();
	        }
	      }
	    }
	 
	    return singleInstance.dbConn;
	  }
}
