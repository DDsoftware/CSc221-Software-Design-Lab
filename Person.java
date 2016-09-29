    /**
     *
     * @author Dramane
     */

    package finalproject;

    public class Person{

    private int ID;
    private String firstName;
    private String lastName;
    private String Address;
    private String familyNumber;

     // no-argument constructor
    public Person()
    {
    } // end no-argument Person constructor

    // constructor
    public Person( int id, String first, String last,String Address, String family )
    {
        setID( id );
        setFirstName( first );
        setLastName( last );
        setAddress( Address );
        setfamilyNumber( family );
    } // end five-argument Person constructor

    // sets the addressID
    public void setID( int id )
    {
        ID = id;
    } // end method setAddressID

    // returns the addressID
    public int getID()
    {
        return ID;
    } // end method getAddressID

    // sets the firstName
    public void setFirstName( String first )
    {
        firstName = first;
    } // end method setFirstName

    // returns the first name
    public String getFirstName()
    {
        return firstName;
    } // end method getFirstName

    // sets the lastName
    public void setLastName( String last )
    {
        lastName = last;
    } // end method setLastName

    // returns the last name
    public String getLastName()
    {
        return lastName;
    } // end method getLastName

    // sets the email address
    public void setAddress( String Address )
    {
        Address = Address;
    } // end method setEmail
    
    // returns the email address
    public String getAddress()
    {
        return Address;
    } // end method getEmail

    // sets the phone number
    public void setfamilyNumber( String family )
    {
        familyNumber = family;
    } // end method setPhoneNumber

    // returns the phone number
    public String getfamilyNumber()
    {
        return familyNumber;
    } // end method getPhoneNumber
 } // end class Person