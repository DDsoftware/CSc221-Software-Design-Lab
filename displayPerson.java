/**
 *
 * @author Dramane
 */

package finalproject;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class displayPerson
{
    // database URL
    static final String DATABASE_URL = "jdbc:derby://localhost:1527/Personels";

    // launch the application
    public static void main( String args[] )
    {

    Connection connection = null; // manages connection
    Statement statement = null; // query statement
    ResultSet resultSet = null; // manages results

    // connect to database books and query database
    try
    {
        // establish connection to database
        connection = DriverManager.getConnection(
        DATABASE_URL, "Dramane", "Diak" );
 
        // create Statement for querying database
        statement = connection.createStatement();
        
        // query database
        resultSet = statement.executeQuery(
        "SELECT ID, FirstName, LastName FROM People" );
        
        // process query results
        ResultSetMetaData metaData = resultSet.getMetaData();
        int numberOfColumns = metaData.getColumnCount();
        System.out.println( "People Table of Persels Database:\n" );
        
        for ( int i = 1; i <= numberOfColumns; i++ )
           System.out.printf( "%-8s\t", metaData.getColumnName( i ) );
        System.out.println();
        
        while ( resultSet.next())
        {
           for(int i=1;i<= numberOfColumns; i++)
                System.out.printf("%-8s\t", resultSet.getObject(i));
            System.out.println();  
            } // end while
    }// end try 

    catch ( SQLException sqlException )
    {
        sqlException.printStackTrace();
    } // end catch
 } // end main
} // end class DisplayAuthors
