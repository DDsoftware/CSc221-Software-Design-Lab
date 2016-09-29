    /**
     *
     * @author Dramane
     */

    package finalproject;
    
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.List;
    import java.util.ArrayList;
    
    public class PersonQueries
    {
        private static final String URL = "jdbc:derby://localhost:1527/Personels";
        private static final String USERNAME = "Dramane";
        private static final String PASSWORD = "Diak";
        
        private Connection connection = null; // manages connection
        private PreparedStatement selectAllPeople = null;
        private PreparedStatement selectPeopleByLastName = null;
        private PreparedStatement insertNewPerson = null;
        // constructor
        public PersonQueries()
        {
            try
            {
                connection =
                DriverManager.getConnection( URL, USERNAME, PASSWORD );

                // create query that selects all entries in'PEOPLE TABLE'
                selectAllPeople =
                connection.prepareStatement( "SELECT * FROM People" );
                // create query that selects entries with a specific last name
                selectPeopleByLastName = connection.prepareStatement(
                "SELECT * FROM People WHERE LastName = ?" );
                // create insert that adds a new entry into the database
                insertNewPerson = connection.prepareStatement(
                "INSERT INTO People " +
                "( ID, FirstName, LastName, Address, familyNumber ) " +
                "VALUES ( ?, ?, ?, ?, ? )" );
             }//endtry
            catch ( SQLException sqlException ) {
                sqlException.printStackTrace();
                System.exit( 1 );
            }// end catch
        }  //end PersonQueries constructor

        // select all of the addresses in the database
        public List< Person > getAllPeople()
        {
           List< Person > results = null;
           ResultSet resultSet = null;
            try
            {
              // executeQuery returns ResultSet containing matching entries
                resultSet = selectAllPeople.executeQuery();
                results = new ArrayList< Person >();

                while ( resultSet.next() )
                {
                    results.add( new Person(
                    resultSet.getInt( "ID" ),
                    resultSet.getString( "FirstName" ),
                    resultSet.getString( "LastName" ),
                    resultSet.getString( "Address" ),
                    resultSet.getString( "familyNumber" ) ) );
                } // end while
            }  //end try
            catch ( SQLException sqlException )
            {
                sqlException.printStackTrace();
            } // end catch
            finally
            {
                try
                {
                   resultSet.close();
                } // end try

                catch ( SQLException sqlException )
                {  
                    sqlException.printStackTrace();
                    close();
                }// end catch
            } //end finally

           return results;    
        } // end method getAllPeople

        // select person by last name
        public List< Person > getPeopleByLastName( String name )
        {
           List< Person > results = null;
           ResultSet resultSet = null;
            try
            {
                selectPeopleByLastName.setString(1, name ); // specify last name

                // executeQuery returns ResultSet containing matching entries
                resultSet = selectPeopleByLastName.executeQuery();
                results = new ArrayList< Person >();

                while ( resultSet.next() )
                {
                    results.add( new Person( 
                    resultSet.getInt("ID" ),
                    resultSet.getString( "FirstName" ),
                    resultSet.getString( "LastName" ),
                    resultSet.getString( "Address" ),
                    resultSet.getString( "familyNumber" ) ) );
                }  // end while
            }  //end try
            catch (SQLException sqlException)
            {
               sqlException.printStackTrace();
            } // end catch
            finally
            {
                try
                   {
                      resultSet.close();
                   } // end try
                   catch ( SQLException sqlException )
                   {
                        sqlException.printStackTrace();
                        close();
                    }// end catch
                }  //end finally
           return results;
        }//end method getPeopleByName


         // add an entry
        public int addPerson(String ID, String fname, String lname, String Address, String num )
        {
           int result = 0;   
         // set parameters, then execute insertNewPerson
            try
            {
                insertNewPerson.setString( 1, ID );
                insertNewPerson.setString( 2, fname );
                insertNewPerson.setString( 3, lname );
                insertNewPerson.setString( 4, Address );
                insertNewPerson.setString( 5, num );

                // insert the new entry; returns # of rows updated
                result = insertNewPerson.executeUpdate();           
            }//endtry
            catch ( SQLException sqlException )
            {
                sqlException.printStackTrace();
                close();
            } // end catch

            return result;
        } // end method addPerson

         // close the database connection
        public void close()
        {
            try
            {
                connection.close();
            }//endtry
            catch ( SQLException sqlException )
            {
                sqlException.printStackTrace();
            }// end catch
        }  //end method close
}//end class