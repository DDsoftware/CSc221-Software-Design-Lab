    /**
     *
     * @author Dramane
     */
    package finalproject;

     import java.awt.event.ActionEvent;
     import java.awt.event.ActionListener;
     import java.awt.event.WindowAdapter;
     import java.awt.event.WindowEvent;
     import java.awt.FlowLayout;
     import java.awt.GridLayout;
     import java.util.List;
     import javax.swing.JButton;
     import javax.swing.Box;
     import javax.swing.JFrame;
     import javax.swing.JLabel;
     import javax.swing.JPanel;
     import javax.swing.JTextField;
     import javax.swing.WindowConstants;
     import javax.swing.BoxLayout;
     import javax.swing.BorderFactory;
     import javax.swing.JOptionPane;

public class FinalProject extends JFrame
{
    private Person currentEntry;
    private PersonQueries personQueries;
    private List< Person > results;
    private int numberOfEntries = 0;
    private int currentEntryIndex;
    private JButton browseButton;
    private JLabel AddressLabel;
    private JTextField AddressTextField;
    private JLabel firstNameLabel;
    private JTextField firstNameTextField;
    private JLabel idLabel;
    private JTextField idTextField;
    private JTextField indexTextField;
    private JLabel lastNameLabel;
    private JTextField lastNameTextField;
    private JTextField maxTextField;
    private JButton nextButton;
    private JLabel ofLabel;
    private JLabel familyLabel;
    private JTextField familyTextField;
    private JButton previousButton;
    private JButton queryButton;
    private JLabel queryLabel;
    private JPanel queryPanel;
    private JPanel navigatePanel;
    private JPanel displayPanel;
    private JTextField queryTextField;
    private JButton insertButton;

    // no-argument constructor
    public FinalProject()
    {
       super( "Personel Info" );
       
        // establish database connection and set up PreparedStatements
        personQueries = new PersonQueries();

        // create GUI
        navigatePanel = new JPanel();
        previousButton = new JButton();
        indexTextField = new JTextField( 2 );
        ofLabel = new JLabel();
        maxTextField = new JTextField( 2 );
        nextButton = new JButton();
        displayPanel = new JPanel();
        idLabel = new JLabel();
        idTextField = new JTextField( 10 );
        firstNameLabel = new JLabel();
        firstNameTextField = new JTextField( 10 );
        lastNameLabel = new JLabel();
        lastNameTextField = new JTextField( 10 );
        AddressLabel = new JLabel();
        AddressTextField = new JTextField( 10 );
        familyLabel = new JLabel();
        familyTextField = new JTextField( 10 );
        queryPanel = new JPanel();
        queryLabel = new JLabel();
        queryTextField = new JTextField( 10 );
        queryButton = new JButton();
        browseButton = new JButton();
        insertButton = new JButton();

        setLayout( new FlowLayout( FlowLayout.CENTER, 10, 10 ) );
        setSize( 400, 400 );
        setResizable( false );

        navigatePanel.setLayout(
            new BoxLayout( navigatePanel, BoxLayout.X_AXIS ) );

        previousButton.setText( "Previous entry" );
        previousButton.setEnabled( false );
        previousButton.addActionListener(
         new ActionListener()
         {
             public void actionPerformed(ActionEvent evt)
            {
                 previousButtonActionPerformed( evt );
              } // end method actionPerformed
           } // end anonymous inner class
        ); // end call to addActionListener

        navigatePanel.add( previousButton );
        navigatePanel.add( Box.createHorizontalStrut( 10 ) );

        indexTextField.setHorizontalAlignment(
           JTextField.CENTER );
        indexTextField.setEditable( false );
        indexTextField.addActionListener(
           new ActionListener()
           {
              public void actionPerformed( ActionEvent evt )
              {
                 indexTextFieldActionPerformed( evt );
              } // end method actionPerformed
           } // end anonymous inner class
        ); // end call to addActionListener
        navigatePanel.add( indexTextField );
        navigatePanel.add( Box.createHorizontalStrut( 10 ) );
        ofLabel.setText( "of" );
        navigatePanel.add( ofLabel );
        navigatePanel.add( Box.createHorizontalStrut( 10 ) );

         maxTextField.setHorizontalAlignment(
            JTextField.CENTER );
         maxTextField.setEditable( false );
         navigatePanel.add( maxTextField );
         navigatePanel.add( Box.createHorizontalStrut( 10 ) );

         nextButton.setText( "Next entry" );
         nextButton.setEnabled( false );
         nextButton.addActionListener(
        new ActionListener(){
            public void actionPerformed( ActionEvent evt )
            {
            nextButtonActionPerformed( evt );
            } // end method actionPerformed
         } // end anonymous inner class
        ); // end call to addActionListener144
        navigatePanel.add( nextButton );
        add( navigatePanel );

        displayPanel.setLayout( new GridLayout( 5, 2, 4, 4 ) );
        idLabel.setText( " ID (SS#):" );
        displayPanel.add( idLabel );

        //idTextField.setEditable( false );
        displayPanel.add( idTextField );

        firstNameLabel.setText( "First Name:" );
        displayPanel.add( firstNameLabel );
        displayPanel.add( firstNameTextField );

        lastNameLabel.setText( "Last Name:" );
        displayPanel.add( lastNameLabel );
        displayPanel.add( lastNameTextField );

        AddressLabel.setText( "Address:" );
        displayPanel.add( AddressLabel );
        displayPanel.add( AddressTextField );

        familyLabel.setText( "Family Number:" );
        displayPanel.add( familyLabel );
        displayPanel.add( familyTextField );
        add( displayPanel );

        queryPanel.setLayout(
            new BoxLayout( queryPanel, BoxLayout.X_AXIS) );

        queryPanel.setBorder( BorderFactory.createTitledBorder(
        "Find an entry by last name" ) );
        queryLabel.setText( "Last Name:" );
        queryPanel.add( Box.createHorizontalStrut( 5 ) );
        queryPanel.add( queryLabel );
        queryPanel.add( Box.createHorizontalStrut( 10 ) );
        queryPanel.add( queryTextField );
        queryPanel.add( Box.createHorizontalStrut( 10 ) );

        queryButton.setText( "Find" );
        queryButton.addActionListener(
           new ActionListener()
           {
              public void actionPerformed( ActionEvent evt )
              {
                 queryButtonActionPerformed( evt );
              } // end method actionPerformed
           } //end anonymous inner class
        ); // end call to addActionListener

        queryPanel.add( queryButton );
        queryPanel.add( Box.createHorizontalStrut( 5 ) );
        add( queryPanel );

        browseButton.setText( "Browse All Entries" );
        browseButton.addActionListener(
           new ActionListener()
           {
              public void actionPerformed( ActionEvent evt )
              {
                 browseButtonActionPerformed( evt );
              } // end method actionPerformed
           } // end anonymous inner class
        ); // end call to addActionListener

        add( browseButton );

        insertButton.setText( "Insert New Entry" );
        insertButton.addActionListener(
           new ActionListener()
           {
              public void actionPerformed( ActionEvent evt )
              {
                 insertButtonActionPerformed( evt );
              } // end method actionPerformed
           } //  end anonymous inner class
        ); // end call to addActionListener

        add( insertButton );
        addWindowListener(
           new WindowAdapter()
           {
              public void windowClosing( WindowEvent evt )
              {
                 personQueries.close(); // close database connection

                System.exit( 0 );
            } // end method windowClosing
          } // end anonymous inner class
        ); // end call to addWindowListener

            setVisible( true );
    } // end no-argument constructor

     // handles call when previousButton is clicked
    private void previousButtonActionPerformed( ActionEvent evt )
    {
        currentEntryIndex--;

        if ( currentEntryIndex < 0 )
            currentEntryIndex = numberOfEntries - 1;

        indexTextField.setText( "" + ( currentEntryIndex + 1 ) );
        indexTextFieldActionPerformed( evt );
    } // end method previousButtonActionPerformed

     // handles call when nextButton is clicked
    private void nextButtonActionPerformed( ActionEvent evt )
    {
        currentEntryIndex++;

        if ( currentEntryIndex >= numberOfEntries )
            currentEntryIndex = 0;

        indexTextField.setText( "" + ( currentEntryIndex + 1 ) );
        indexTextFieldActionPerformed( evt );
    } // end method nextButtonActionPerformed

    // handles call when queryButton is clicked
    private void queryButtonActionPerformed( ActionEvent evt )
    {
        results =
        personQueries.getPeopleByLastName( queryTextField.getText() );
        numberOfEntries = results.size();
        if( numberOfEntries != 0 )
        {
            currentEntryIndex = 0;
            currentEntry = results.get( currentEntryIndex ); 
            idTextField.setText( "" + currentEntry.getID() );
            firstNameTextField.setText( currentEntry.getFirstName() ); 
            lastNameTextField.setText( currentEntry.getLastName() ); 
            AddressTextField.setText( currentEntry.getAddress() ); 
            familyTextField.setText( currentEntry.getfamilyNumber() );
            maxTextField.setText( "" + numberOfEntries ); 
            indexTextField.setText( "" + ( currentEntryIndex + 1 ) ); 
            nextButton.setEnabled( true );
            previousButton.setEnabled( true );
        }//endif

        else
            browseButtonActionPerformed( evt );
    } // end method queryButtonActionPerformed
    
    // handles call when a new value is entered in indexTextField
private void indexTextFieldActionPerformed( ActionEvent evt )
{
    currentEntryIndex =
       ( Integer.parseInt( indexTextField.getText() ) - 1 );
        if( numberOfEntries != 0 && currentEntryIndex < numberOfEntries)
        {
            currentEntry = results.get( currentEntryIndex ); 
            idTextField.setText("" + currentEntry.getID() ); 
            firstNameTextField.setText( currentEntry.getFirstName() ); 
            lastNameTextField.setText( currentEntry.getLastName() ); 
            AddressTextField.setText( currentEntry.getAddress() ); 
            familyTextField.setText( currentEntry.getfamilyNumber() ); 
            maxTextField.setText( "" + numberOfEntries ); 
            indexTextField.setText( "" + ( currentEntryIndex + 1 ) );
        } // end if
    }// end method indexTextFieldActionPerformed
    
    //handles call when browseButton is clicked
private void browseButtonActionPerformed( ActionEvent evt )
{
    try
    {
        results = personQueries.getAllPeople();
        numberOfEntries = results.size();
    
        if( numberOfEntries != 0 )
        {
            currentEntryIndex = 0;
            currentEntry = results.get( currentEntryIndex );
            idTextField.setText( "" + currentEntry.getID() );
            firstNameTextField.setText( currentEntry.getFirstName() );
            lastNameTextField.setText( currentEntry.getLastName() );
            AddressTextField.setText( currentEntry.getAddress() );
            familyTextField.setText( currentEntry.getfamilyNumber() );
            maxTextField.setText( "" + numberOfEntries );
            indexTextField.setText( "" + ( currentEntryIndex + 1 ) );
            nextButton.setEnabled( true );
            previousButton.setEnabled( true );
        } // end if
    }//end try
    catch(Exception e)
    {
        e.printStackTrace();
    } // end catch
    }//end method browseButtonActionPerformed

    // handles call when insertButton is clicked
    private void insertButtonActionPerformed( ActionEvent evt )
    {
        int result = personQueries.addPerson( idTextField.getText(), 
            firstNameTextField.getText(),lastNameTextField.getText(), 
            AddressTextField.getText(),familyTextField.getText() );
        if ( result == 1 )
            JOptionPane.showMessageDialog( this, "Person added!",
             "Person added", JOptionPane.PLAIN_MESSAGE );
        else
            JOptionPane.showMessageDialog( this, "Person not added!",
             "Error", JOptionPane.PLAIN_MESSAGE );
    
       browseButtonActionPerformed( evt );
    } // end method insertButtonActionPerformed


    // main method
    public static void main( String args[] )
    {
        
        new FinalProject();
        
    } // end method main
} // end class AddressBookDisplay