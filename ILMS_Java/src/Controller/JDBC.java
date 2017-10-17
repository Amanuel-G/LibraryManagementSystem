/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Administrator;
import Entity.Book;
import Entity.Librarian;
import Entity.Patron;
import Entity.Transaction;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.JOptionPane;


/**
 *
 * @author Helina
 */
public class JDBC {
    
    private Connection con;
    private Statement st;
    ResultSet rs;
    String result;
    
    private static String dbName = "ilms";
        
  
    public JDBC()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName + "?zeroDateTimeBehavior=convertToNull", "root", "");
            st = con.createStatement();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.out.print("Error");
        }
    }
    
    //Register Book into the database
    public boolean addBook(Book book)
    {
        try
        {
            PreparedStatement stmt = con.prepareStatement("Insert Into Book Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, book.getBookID());
            stmt.setString(2, book.getISBN());
            stmt.setString(3, book.getTitle());
            stmt.setBytes(4,  book.getBookCover());
            stmt.setString(5, book.getAuthor());
            stmt.setString(6, book.getPublisher());
            stmt.setString(7, book.getEdition());
            stmt.setString(8, book.getCourse());
            stmt.setString(9, book.getDepartment());
            stmt.setString(10, book.getCategory());
            stmt.setString(11, book.getDescription());
            stmt.setInt(12, book.getNoOfPage());
            stmt.setDouble(13, book.getRate());
            stmt.setBoolean(14, false);
            stmt.setBoolean(15, false);
            
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Book Registration Successfull", "Confirm", JOptionPane.INFORMATION_MESSAGE);
            
            return true;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Exceptions.getErrorMessage(e, "Register", book), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    //Update Book record
    public void updateBook(Book book, String title, byte[] BookCover, String author, 
            String publisher, String edition, String category, int no_of_page, String Course, String Department, String description)
    {
        try
        {
            PreparedStatement stmt = con.prepareStatement("Update Book set Title = ?, BookCover = ?, Author = ?, Publisher = ?, Edition = ?, Category = ?, NO_of_Page = ?, Course = ?, Department = ?, Description = ? where BookID = ?");
            stmt.setString(1, title);
            stmt.setBytes(2, BookCover);
            stmt.setString(3, author);
            stmt.setString(4, publisher);
            stmt.setString(5, edition);
            stmt.setString(6, category);
            stmt.setInt(7, no_of_page);
            stmt.setString(8, Course);
            stmt.setString(9, Department);
            stmt.setString(10, description);
            stmt.setString(11, book.getBookID());
            
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Book Updated Successfully!", "Update", JOptionPane.INFORMATION_MESSAGE);
            
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Exceptions.getErrorMessage(e, "Update", new Book()), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Remove a book record from the database
    public boolean removeBook(String BookID, String BookISBN)
    {
        try
        {
            PreparedStatement stmt = con.prepareStatement("Delete from Book Where BookID = ?");
            stmt.setString(1, BookID);
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Book Removed Successfully!", "Confirm", JOptionPane.INFORMATION_MESSAGE);
            
            return true;
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    

    public void setBookRented(String BookID)
    {
        try
        {
            PreparedStatement stmt = con.prepareStatement("Update Book set is_Rented = ? where BookID = ?");
            stmt.setBoolean(1, true);
            stmt.setString(2, BookID);
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Book Rented Successfully!", "Confirm", JOptionPane.INFORMATION_MESSAGE);
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setBookReserved(String BookID)
    {
        try
        {
            PreparedStatement stmt = con.prepareStatement("Update Book set is_Reserved = ? where BookID = ?");
            stmt.setBoolean(1, true);
            stmt.setString(2, BookID);
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Book Reserved Successfully!", "Confirm", JOptionPane.INFORMATION_MESSAGE);
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
       
    public void setBookReturned(String BookID)
    {
        try
        {
            PreparedStatement stmt = con.prepareStatement("Update Book set is_Rented = ? where BookID = ?");
            stmt.setBoolean(1, false);
            stmt.setString(2, BookID);
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Book Returned Successfully!", "Confirm", JOptionPane.INFORMATION_MESSAGE);
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void removeBookReservation(String BookID)
    {
        try
        {
            PreparedStatement stmt = con.prepareStatement("Update Book set is_Reserved = ? where BookID = ?");
            stmt.setBoolean(1, false);
            stmt.setString(2, BookID);
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Book Reservation Removed Successfully!", "Confirm", JOptionPane.INFORMATION_MESSAGE);
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Add a librarian to the database
    public boolean addLibrarian(Librarian librarian)
    {
        try
        {
            PreparedStatement stmt = con.prepareStatement("Insert Into librarian Values(?,?,?,?,?)");
            stmt.setString(1, librarian.getID());
            stmt.setString(2, librarian.getName());
            stmt.setString(3, SHA1(librarian.getPassword()));
            stmt.setString(4, librarian.getEmail());
            stmt.setBytes(5, null);
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Librarian Registration Successfull", "Confirm", JOptionPane.INFORMATION_MESSAGE);
            
            return true;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Exceptions.getErrorMessage(e, "Register", librarian), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    //Update a librarian record
    public void updateLibrarian(Librarian librarian, String name, String email)
    {
        try
        {
            PreparedStatement stmt = con.prepareStatement("Update Librarian set Name = ?, Email = ? where EmployeeID  = ?");
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, librarian.getID());
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Librarian Updated Successfully!", "Confirm", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Exceptions.getErrorMessage(e, "Update", new Librarian()), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //remove a librarian record from the database
    public boolean removeLibrarian(String ID)
    {
        try
        {
            PreparedStatement stmt = con.prepareStatement("Delete From Librarian Where EmployeeID = ?");
            stmt.setString(1, ID);
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Librarians Removed Successfully!", "Confirm", JOptionPane.INFORMATION_MESSAGE);
            
            return true;
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    //add a patron(Member) to the database
    public boolean addPatron(Patron patron)
    {
        try
        {
            PreparedStatement stmt = con.prepareStatement("Insert Into Patron Values(?,?,?,?,?,?)");
            stmt.setString(1, patron.getId());
            stmt.setString(2, patron.getName());
            stmt.setString(3, SHA1(patron.getPassword()));
            stmt.setBytes(4, patron.getPatronImage());
            stmt.setString(5, patron.getDepartment());
            stmt.setString(6, patron.getEmail());
            
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Patron Registered Successfully!", "Confirm", JOptionPane.INFORMATION_MESSAGE);
            
            return true;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Exceptions.getErrorMessage(e, "Register", patron), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        
    }
    
    //update a patron record
    public void updatePatron(Patron patron, String name, String email, String department)
    {
        try
        {
            PreparedStatement stmt = con.prepareStatement("Update Patron set Name = ?, Email = ?, Department = ? where ID = ?");
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, department);
            stmt.setString(4, patron.getId());
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Patron Updated Successfully!", "Confirm", JOptionPane.INFORMATION_MESSAGE);
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Exceptions.getErrorMessage(e, "Update", new Patron()), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //remove a patron record from the database
    public boolean removePatron(String ID)
    {
        try
        {
            PreparedStatement stmt = con.prepareStatement("Delete From Patron Where ID = ?");
            stmt.setString(1, ID);
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Patron Removed Successfully!", "Confirm", JOptionPane.INFORMATION_MESSAGE);
            
            return true;
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    
    public boolean addAdministrator(Administrator admin)
    {
        try
        {
            PreparedStatement stmt = con.prepareStatement("Insert Into Administrator Values(?,?,?,?)");
            stmt.setString(1, admin.getID());
            stmt.setString(2, admin.getName());
            stmt.setString(3, SHA1(admin.getPassword()));
            stmt.setString(4, admin.getEmail());
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Administrator Registration Successfull", "Confirm", JOptionPane.INFORMATION_MESSAGE);
            
            return true;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Exceptions.getErrorMessage(e, "Register", admin), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public void updateAdministrator(Administrator admin, String name, String email)
    {
        try
        {
            PreparedStatement stmt = con.prepareStatement("Update Administrator set Name = ?, Email = ? where EmployeeID  = ?");
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, admin.getID());
            
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Administrator Updated Successfully!", "Confirm", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Exceptions.getErrorMessage(e, "Update", new Administrator()), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean removeAdministrator(String ID)
    {
        try
        {
            PreparedStatement stmt = con.prepareStatement("Delete From Administrator Where EmployeeID = ?");
            stmt.setString(1, ID);
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Administrator Removed Successfully!", "Confirm", JOptionPane.INFORMATION_MESSAGE);
            
            return true;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
        
    public Book returnBook(String Book_ID)
    {
        Book book = null;
        try
        {
            PreparedStatement stmt = con.prepareStatement("Select BookID, ISBN, Title, BookCover, Author, Publisher, Edition, Category, NO_of_Page, Rate, is_Rented, is_Reserved, Course, Department, Description from Book where BookID = ?");
            stmt.setString(1, Book_ID);
            
            rs = stmt.executeQuery();
            
            while (rs.next())
            {
                book = new Book(
                        rs.getString("Title"), 
                        rs.getString("ISBN"),
                        rs.getString("BookID"), 
                        rs.getBytes("BookCover"),
                        rs.getString("Publisher"),
                        rs.getString("Author"),
                        rs.getString("Edition"),
                        rs.getString("Category"),
                        rs.getInt("NO_of_Page"),
                        rs.getBoolean("is_Rented"),
                        rs.getBoolean("is_Reserved"),
                        rs.getFloat("Rate"),
                        rs.getString("Course"),
                        rs.getString("Department"),
                rs.getString("Description"));
            }
            
            return book;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
    }
    
    public Book returnBookRentWindow(String Book_ID)
    {
        Book book = null;
        try
        {
            PreparedStatement stmt = con.prepareStatement("Select BookID, ISBN, Title, Author, Edition from Book where BookID = ?");
            stmt.setString(1, Book_ID);
            
            rs = stmt.executeQuery();
            
            while (rs.next())
            {
                book = new Book(
                        rs.getString("ISBN"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getString("Edition"));
            }
            
            return book;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public Patron returnPatron(String ID)
    {
        Patron patron = null;
        try
        {
            PreparedStatement stmt = con.prepareStatement("Select * from Patron where ID = ?");
            stmt.setString(1, ID);
            
            rs = stmt.executeQuery();
            
            while (rs.next())
            {
                patron = new Patron(
                        rs.getString("Name"),
                        rs.getString("ID"), 
                        rs.getString("Password"),
                        rs.getBytes("PatronImage"),
                        rs.getString("Email"),
                        rs.getString("Department"));
            }
            
            return patron;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public Administrator returnAdministrator(String EmployeeID)
    {
        Administrator admin = null;
        
        try
        {
            PreparedStatement stmt = con.prepareStatement("Select * from Administrator where EmployeeID = ?");
            stmt.setString(1, EmployeeID);
            
            rs = stmt.executeQuery();
            
            while (rs.next())
            {
                admin = new Administrator(
                        rs.getString("EmployeeID"),
                        rs.getString("Name"), 
                        rs.getString("Password"),
                        rs.getString("Email"));
            }
            
            return admin;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
    }
    
    public Librarian returnLibrarian(String EmployeeID)
    {
        Librarian librarian = null;
        
        try
        {
            PreparedStatement stmt = con.prepareStatement("Select * from Librarian where EmployeeID = ?");
            stmt.setString(1, EmployeeID);
            
            rs = stmt.executeQuery();
            
            while (rs.next())
            {
                librarian = new Librarian(
                        rs.getString("Name"),
                        rs.getString("EmployeeID"),
                        rs.getString("Password"),
                        rs.getString("Email"));
            }
            
            return librarian;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public void registerRentTransaction(Librarian librarian, Book book, Patron patron)
    {
        try
        {   
            String currentTime = getCurrentDate();
            String transactionID = librarian.getID() + book.getBookID() + patron.getId() + currentTime;
            
            PreparedStatement stmt = con.prepareStatement("Insert Into Rent_Transaction(TransactionNumber, TransactionDate, LibrarianID, BookID, PatronID, is_Returned) Values(?, ?, ?, ?, ?, ?)");
            
            stmt.setString(1, SHA1(transactionID));
            stmt.setString(2, currentTime);
            stmt.setString(3, librarian.getID());
            stmt.setString(4, book.getBookID());
            stmt.setString(5, patron.getId());
            stmt.setBoolean(6, false);
            
            stmt.execute();
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);

        }
    }
    
    public void registerReturnTransaction(Librarian librarian, Book book, Patron patron)
    {
        try
        {
            String currentTime = getCurrentDate();
            
            PreparedStatement stmt = con.prepareStatement("Update Rent_Transaction set is_Returned = ?, Return_Date = ? where BookID = ? AND LibrarianID = ? AND PatronID = ? AND is_Returned = ?");
            stmt.setBoolean(1, true);
            stmt.setString(2, currentTime);
            stmt.setString(3, book.getBookID());
            stmt.setString(4, librarian.getID()); 
            stmt.setString(5, patron.getId());
            stmt.setBoolean(6, false);
            
            
            stmt.execute();
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);

        }
    }
    
    public void registerBookAddTransaction(Book book, Librarian librarian)
    {
        try
        {
            String currentTime = getCurrentDate();
            String transactionID = librarian.getID() + book.getBookID() + currentTime;
            
            PreparedStatement stmt = con.prepareStatement("Insert into Book_Transaction values (?, ?, ?, ?, ?)");
            stmt.setString(1, SHA1(transactionID));
            stmt.setInt(2, Transaction.BOOK_ADDED);
            stmt.setString(3, currentTime);
            stmt.setString(4, book.getBookID());
            stmt.setString(5, librarian.getID());
            
            stmt.execute();
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void registerBookRemoveTransaction(Book book, Librarian librarian)
    {
        try
        {
            String currentTime = getCurrentDate();
            String transactionID = librarian.getID() + book.getBookID() + currentTime;
            
            PreparedStatement stmt = con.prepareStatement("Insert into Book_Transaction values (?, ?, ?, ?, ?)");
            stmt.setString(1, SHA1(transactionID));
            stmt.setInt(2, Transaction.BOOK_REMOVED);
            stmt.setString(3, currentTime);
            stmt.setString(4, book.getBookID());
            stmt.setString(5, librarian.getID());
            
            stmt.execute();
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void registerPatronAddTransaction(Patron patron, Administrator administrator)
    {
        try
        {
            String currentTime = getCurrentDate();
            String transactionID = administrator.getID() + patron.getId() + currentTime;
            
            PreparedStatement stmt = con.prepareStatement("Insert into Patron_Transaction values (?, ?, ?, ?, ?)");
            stmt.setString(1, SHA1(transactionID));
            stmt.setInt(2, Transaction.PATRON_ADDED);
            stmt.setString(3, currentTime);
            stmt.setString(4, patron.getId());
            stmt.setString(5, administrator.getID());
            
            stmt.execute();
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void registerPatronRemoveTransaction(Patron patron, Administrator administrator)
    {
        try
        {
            String currentTime = getCurrentDate();
            String transactionID = administrator.getID() + patron.getId() + currentTime;
            
            PreparedStatement stmt = con.prepareStatement("Insert into Patron_Transaction values (?, ?, ?, ?, ?)");
            stmt.setString(1, SHA1(transactionID));
            stmt.setInt(2, Transaction.PATRON_REMOVED);
            stmt.setString(3, currentTime);
            stmt.setString(4, patron.getId());
            stmt.setString(5, administrator.getID());
            
            stmt.execute();
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void registerLibrarianAddTransaction(Librarian librarian, Administrator administrator)
    {
        try
        {
            String currentTime = getCurrentDate();
            String transactionID = administrator.getID() + librarian.getID() + currentTime;
            
            PreparedStatement stmt = con.prepareStatement("Insert into Librarian_Transaction values (?, ?, ?, ?, ?)");
            stmt.setString(1, SHA1(transactionID));
            stmt.setInt(2, Transaction.LIBRARIAN_ADDED);
            stmt.setString(3, currentTime);
            stmt.setString(4, librarian.getID());
            stmt.setString(5, administrator.getID());
            
            stmt.execute();
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void registerLibrarianRemoveTransaction(Librarian librarian, Administrator administrator)
    {
        try
        {
            String currentTime = getCurrentDate();
            String transactionID = administrator.getID() + librarian.getID() + currentTime;
            
            PreparedStatement stmt = con.prepareStatement("Insert into Librarian_Transaction values (?, ?, ?, ?, ?)");
            stmt.setString(1, SHA1(transactionID));
            stmt.setInt(2, Transaction.LIBRARIAN_REMOVED);
            stmt.setString(3, currentTime);
            stmt.setString(4, librarian.getID());
            stmt.setString(5, administrator.getID());
            
            stmt.execute();
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public ArrayList<String[]> getAllRentReport()
    {
        ArrayList<String[]> result = new ArrayList<>();
        
        try
        {
            PreparedStatement stmt = con.prepareStatement("Select TransactionDate, LibrarianID, BookID, PatronID from Rent_Transaction");
            rs = stmt.executeQuery();
            
            int counter = 1;
            
            while (rs.next())
            {
                String[] row = new String[5];
                row[0] = String.valueOf(counter);
                row[1] = rs.getString("TransactionDate");
                row[2] = rs.getString("LibrarianID");
                row[3] = rs.getString("BookID");
                row[4] = rs.getString("PatronID");
                
                counter++;
                result.add(row);
                
            }
            return result;     
        }   
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
        public ResultSet getAllRentReportRS()
    {
        
        try
        {
            PreparedStatement stmt = con.prepareStatement("Select TransactionDate, LibrarianID, BookID, PatronID from Rent_Transaction");
            rs = stmt.executeQuery();
            return rs;  
        }   
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ArrayList<String[]> getAddBookTransaction()
    {
        ArrayList<String[]> result = new ArrayList<>();
        
        try
        {
            PreparedStatement stmt = con.prepareStatement("Select TransactionDate, LibrarianID, BookID from Book_Transaction");
            rs = stmt.executeQuery();
            
            int counter = 1;
            
            while (rs.next())
            {
                String[] row = new String[4];
                row[0] = String.valueOf(counter);
                row[1] = rs.getString("TransactionDate");
                row[2] = rs.getString("LibrarianID");
                row[3] = rs.getString("BookID");
                
                counter++;
                result.add(row);
                
            }
            return result;     
        }   
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ResultSet getAddBookTransactionRS()
    {   
        try
        {
            PreparedStatement stmt = con.prepareStatement("Select  TransactionDate, LibrarianID, BookID from Book_Transaction");
            rs = stmt.executeQuery();
            return rs;
        }   
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ArrayList<String[]> getAddedPatronTransaction()
    {
        ArrayList<String[]> result = new ArrayList<>();
        
        try
        {
            PreparedStatement stmt = con.prepareStatement("Select  TransactionDate, PatronID, AdministratorID from Patron_Transaction");
            rs = stmt.executeQuery();
            
            int counter = 1;
            
            while (rs.next())
            {
                String[] row = new String[4];
                row[0] = String.valueOf(counter);
                row[1] = rs.getString("TransactionDate");
                row[2] = rs.getString("PatronID");
                row[3] = rs.getString("AdministratorID");
                
                counter++;
                result.add(row);
                
            }
            return result;     
        }   
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ResultSet getAddedPatronTransactionRS()
    {   
        try
        {
            PreparedStatement stmt = con.prepareStatement("Select  TransactionDate, PatronID, AdministratorID from Patron_Transaction");
            rs = stmt.executeQuery();
            return rs;    
        }   
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ArrayList<String[]> getAllUnreturnedBookTransaction()
    {
        ArrayList<String[]> result = new ArrayList<>();
        
        try
        {
            PreparedStatement stmt = con.prepareStatement("Select TransactionDate, LibrarianID, BookID, PatronID from Rent_Transaction where is_Returned = ?");
            stmt.setBoolean(1, false);
            rs = stmt.executeQuery();
            
            int counter = 1;
            
            while (rs.next())
            {
                String[] row = new String[5];
                row[0] = String.valueOf(counter);
                row[1] = rs.getString("TransactionDate");
                row[2] = rs.getString("LibrarianID");
                row[3] = rs.getString("BookID");
                row[4] = rs.getString("PatronID");
                
                counter++;
                result.add(row);
                
            }
            return result;     
        }   
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ResultSet getAllUnreturnedBookTransactionRS()
    {
        try
        {
            
            PreparedStatement stmt = con.prepareStatement("Select TransactionDate, LibrarianID, BookID, PatronID from Rent_Transaction where is_Returned = ?");
            stmt.setBoolean(1, false);
            rs = stmt.executeQuery();
            return rs;   
        }   
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ResultSet getAllUnreturnedBookTransactionRS(String bookID, String patronID)
    {
        try
        {
            
            PreparedStatement stmt = con.prepareStatement("Select TransactionDate, LibrarianID, BookID, PatronID from Rent_Transaction where is_Returned = ? AND BookID = ? AND PatronID = ?");
            stmt.setBoolean(1, false);
            stmt.setString(2, bookID);
            stmt.setString(3, patronID);
            rs = stmt.executeQuery();
            return rs;   
        }   
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ArrayList<String[]> generatePatronBookTransaction(String patronID)
    {
        ArrayList<String[]> result = new ArrayList<>();
        try
        {
            PreparedStatement stmt = con.prepareStatement("Select TransactionDate, LibrarianID, BookID, is_Returned, Return_Date from Rent_Transaction where PatronID = ?");
            stmt.setString(1, patronID);
            rs = stmt.executeQuery();
            
            int counter = 1;
            
            while (rs.next())
            {
                String[] row = new String[6];
                row[0] = String.valueOf(counter);
                row[1] = rs.getString("TransactionDate");
                row[2] = rs.getString("LibrarianID");
                row[3] = rs.getString("BookID");
                if (rs.getBoolean("is_Returned"))
                {
                    row[4] = "Returned";
                    row[5] = rs.getString("Return_Date");
                }
                else
                {
                    row[4] = "Not Returned";
                    row[5] = "";
                }
                                
                counter++;
                result.add(row);
                
            }
            return result;     
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    
    
    public ResultSet generatePatronBookTransactionRS(String patronID)
    {
        
        try
        {  
            PreparedStatement stmt = con.prepareStatement("Select TransactionDate, LibrarianID, BookID, is_Returned, Return_Date from Rent_Transaction where PatronID LIKE ?");
            stmt.setString(1, "%" + patronID + "%");
            rs = stmt.executeQuery();
            return rs;    
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ArrayList<String[]> generateLibrarianBookTransaction(String librarianID)
    {
        ArrayList<String[]> result = new ArrayList<>();
        
        try
        {
            PreparedStatement stmt = con.prepareStatement("Select TransactionDate, BookID, PatronID from Rent_Transaction where LibrarianID = ?");
            stmt.setString(1, librarianID);
            rs = stmt.executeQuery();
            
            int counter = 1;
            
            while (rs.next())
            {
                String[] row = new String[4];
                row[0] = String.valueOf(counter);
                row[1] = rs.getString("TransactionDate");
                row[2] = rs.getString("BookID");
                row[3] = rs.getString("PatronID");
             
                counter++;
                result.add(row);
                
            }
            return result;     
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    
    public ResultSet generateLibrarianBookTransactionRS(String librarianID)
    {
        
        try
        {
            PreparedStatement stmt = con.prepareStatement("Select TransactionDate, BookID, PatronID from Rent_Transaction where LibrarianID LIKE ?");
            stmt.setString(1, "%" + librarianID + "%");
            rs = stmt.executeQuery();
            return rs;    
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ArrayList<String[]> generateSpecificBookTransaction(String BookID)
    {
        ArrayList<String[]> result = new ArrayList<>();
        
        try
        {
            PreparedStatement stmt = con.prepareStatement("Select TransactionDate, LibrarianID, PatronID, is_Returned, Return_Date from Rent_Transaction where BookID = ?");
            stmt.setString(1, BookID);
            rs = stmt.executeQuery();
            
            int counter = 1;
            
            while (rs.next())
            {
                String[] row = new String[6];
                row[0] = String.valueOf(counter);
                row[1] = rs.getString("TransactionDate");
                row[2] = rs.getString("LibrarianID");
                row[3] = rs.getString("PatronID");
                if (rs.getBoolean("is_Returned"))
                {
                    row[4] = "Returned";
                    row[5] = rs.getString("Return_Date");
                }
                else
                {
                    row[4] = "Not Returned";
                    row[5] = "";
                }
                                
                counter++;
                result.add(row);
                
            }
            return result;     
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ResultSet generateSpecificBookTransactionRS(String BookID)
    {
        
        try
        {
            PreparedStatement stmt = con.prepareStatement("Select TransactionDate, LibrarianID, PatronID, is_Returned, Return_Date from Rent_Transaction where BookID LIKE ?");
            stmt.setString(1, "%" + BookID + "%");
            rs = stmt.executeQuery();
            return rs;     
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public String returnLibrarianEmail(String EmployeeID)
    {
        String email = null;
        try
        {
            PreparedStatement stmt = con.prepareStatement("Select * from Librarian where EmployeeID = ?");
            stmt.setString(1, EmployeeID);
            
            rs = stmt.executeQuery();
            
            while (rs.next())
            {     
                email = rs.getString("Email");
            }
            
            return email;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return email;
        }
    }
    
    
    public String returnAdministratorEmail(String EmployeeID)
    {
        String email = null;
        try
        {
            PreparedStatement stmt = con.prepareStatement("Select * from Administrator where EmployeeID = ?");
            stmt.setString(1, EmployeeID);
            
            rs = stmt.executeQuery();
            
            while (rs.next())
            {     
                email = rs.getString("Email");
            }
            
            return email;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return email;
        }
    }
    
    
    public String returnPatronEmail(String ID)
    {
        String email = null;
        try
        {
            PreparedStatement stmt = con.prepareStatement("Select * from Patron where ID = ?");
            stmt.setString(1, ID);
            
            rs = stmt.executeQuery();
            
            while (rs.next())
            {     
                email = rs.getString("Email");
            }
            
            return email;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return email;
        }
    }
    
    public String resetPasswordPatron(String ID)
    {
        try
        {
            String newPassword = generatePassword();
            Patron patron = returnPatron(ID);
            
            PreparedStatement stmt = con.prepareStatement("Update Patron set Password = ? where ID  = ?");
            stmt.setString(1, SHA1(newPassword));
            stmt.setString(2, patron.getId());
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Patron Password Reseted Successfully!", "Reset Password", JOptionPane.INFORMATION_MESSAGE);
        
            return newPassword;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Exceptions.getErrorMessage(e, "PasswordReset", new Patron()), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
            
    }
    
    public String resetPasswordLibrarian(String EmployeeID)
    {
        try
        {
            String newPassword = generatePassword();
            Librarian librarian = returnLibrarian(EmployeeID);
            
            PreparedStatement stmt = con.prepareStatement("Update Librarian set Password = ? where EmployeeID  = ?");
            stmt.setString(1, SHA1(newPassword));
            stmt.setString(2, librarian.getID());
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Librarian Password Reseted Successfully!", "Reset Password", JOptionPane.INFORMATION_MESSAGE);
        
            return newPassword;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Exceptions.getErrorMessage(e, "PasswordReset", new Librarian()), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
            
    }
    
    public String resetPasswordAdministrator(String EmployeeID)
    {
        try
        {
            String newPassword = generatePassword();
            
            Administrator administrator = returnAdministrator(EmployeeID);
            
            PreparedStatement stmt = con.prepareStatement("Update Administrator set Password = ? where EmployeeID  = ?");
            stmt.setString(1, SHA1(newPassword));
            stmt.setString(2, administrator.getID());
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Administrator Password Reseted Successfully!", "Reset Password", JOptionPane.INFORMATION_MESSAGE);
        
            return newPassword;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Exceptions.getErrorMessage(e, "PasswordReset", new Administrator()), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
            
    }
    
    public Object getEmailOwner(String ID, String email)
    {
        
        try
        {
            PreparedStatement stmt = con.prepareStatement("Select * from Administrator where Email = ? AND EmployeeID = ?");
            stmt.setString(1, email);
            stmt.setString(2, ID);
            
            rs = stmt.executeQuery();
            
            while(rs.next())
            {
                String locatedID = rs.getString("EmployeeID");
                
                if (locatedID.equals(ID))
                {
                    return returnAdministrator(ID);
                }
            }
            
            
            stmt = con.prepareStatement("Select * from Librarian where Email = ? AND EmployeeID = ?");
            stmt.setString(1, email);
            stmt.setString(2, ID);
            
            rs = stmt.executeQuery();
            
            while(rs.next())
            {
                String locatedID = rs.getString("EmployeeID");
                
                if (locatedID.equals(ID))
                {
                    return returnLibrarian(ID);
                }
            }
            
            return null;
           
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public Object login(String username, String password)
    {
        try
        {
            
            PreparedStatement stmt = con.prepareStatement("Select * from Administrator");
            rs = stmt.executeQuery();
            
            while (rs.next())
            {
                String name = rs.getString("Name");
                String pass = rs.getString("Password");
                String id = rs.getString("EmployeeID");
                
                if (username.equals(name) && SHA1(password).equals(pass))
                {
                    Administrator administrator = returnAdministrator(id);
                    return administrator;
                }
            }
            
            stmt = con.prepareStatement("Select * from Librarian");
            rs = stmt.executeQuery();
            
            while (rs.next())
            {
                String name = rs.getString("Name");
                String pass = rs.getString("Password");
                String id = rs.getString("EmployeeID");
                
                if (username.equals(name) && SHA1(password).equals(pass))
                {
                    Librarian librarian = returnLibrarian(id);
                    return librarian;
                }
            }
            
            return null;
            
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    
    public ResultSet searchBookByTitle(String searchTerm)
    {
        try
        {         
            PreparedStatement stmt = con.prepareStatement("Select BookID, ISBN, Title, Author, Publisher, Edition, Category, is_Rented, is_Reserved, Course, Department from Book where Title LIKE ?");
            stmt.setString(1, "%" + searchTerm + "%");
            
            rs = stmt.executeQuery();
            return rs;            
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ResultSet searchBookByAuthor(String searchTerm)
    {
        try
        {         
            PreparedStatement stmt = con.prepareStatement("Select BookID, ISBN, Title, Author, Publisher, Edition, Category, is_Rented, is_Reserved, Course, Department from Book where Author LIKE ?");
            stmt.setString(1, "%" + searchTerm + "%");
            
            rs = stmt.executeQuery();
            return rs;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ResultSet searchBookByEdition(String searchTerm)
    {
        try
        {         
            PreparedStatement stmt = con.prepareStatement("Select BookID, ISBN, Title, Author, Publisher, Edition, Category, is_Rented, is_Reserved, Course, Department from Book where Edition LIKE ?");
            stmt.setString(1, "%" + searchTerm + "%");
            
            rs = stmt.executeQuery();
            return rs;
            
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ResultSet searchBookByCategory(String searchTerm)
    {
        try
        {         
            PreparedStatement stmt = con.prepareStatement("Select BookID, ISBN, Title, Author, Publisher, Edition, Category, is_Rented, is_Reserved, Course, Department from Book where Course LIKE ?");
            stmt.setString(1, "%" + searchTerm + "%");
            
            rs = stmt.executeQuery();
            return rs;
            
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ResultSet searchBookByISBN(String searchTerm)
    {
        try
        {         
            PreparedStatement stmt = con.prepareStatement("Select BookID, ISBN, Title, Author, Publisher, Edition, Category, is_Rented, is_Reserved, Course, Department from Book where ISBN LIKE ?");
            stmt.setString(1, "%" + searchTerm + "%");
            
            rs = stmt.executeQuery();
            return rs;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ResultSet searchBook(String searchTerm)
    {
        try
        {   
            PreparedStatement stmt = con.prepareStatement("Select DISTINCT from Book where ISBN LIKE ? OR Category LIKE ? OR Edition LIKE ? OR Author LIKE ? OR Title LIKE ?");
            stmt.setString(1, "%" + searchTerm + "%");
            stmt.setString(2, "%" + searchTerm + "%");
            stmt.setString(3, "%" + searchTerm + "%");
            stmt.setString(4, "%" + searchTerm + "%");
            stmt.setString(5, "%" + searchTerm + "%");
            rs = stmt.executeQuery();
            return rs;
            
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ResultSet searchPatronByName(String searchTerm)
    {
        try
        {         
            PreparedStatement stmt = con.prepareStatement("Select * from Patron where Name LIKE ?");
            stmt.setString(1, "%" + searchTerm + "%");
            
            rs = stmt.executeQuery();
            return rs;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ResultSet searchPatronByDepartment(String searchTerm)
    {
        try
        {         
            
            PreparedStatement stmt = con.prepareStatement("Select * from Patron where Department LIKE ?");
            stmt.setString(1, "%" + searchTerm + "%");
            
            rs = stmt.executeQuery();
            return rs;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ResultSet searchPatronByID(String searchTerm)
    {
        try
        {         
            
            PreparedStatement stmt = con.prepareStatement("Select * from Patron where ID LIKE ?");
            stmt.setString(1, "%" + searchTerm + "%");
            
            rs = stmt.executeQuery();
            return rs;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    
    
    public ResultSet searchPatronByEmail(String searchTerm)
    {
        try
        {
            PreparedStatement stmt = con.prepareStatement("Select * from Patron where Email LIKE ?");
            stmt.setString(1, "%" + searchTerm + "%");
            
            rs = stmt.executeQuery();
            return rs;
            
        }
        
        catch (Exception e)
        {
            return null;
        }
    }
    
    
    public ResultSet searchLibrarianByID(String searchTerm)
    {
        try
        {         
            PreparedStatement stmt = con.prepareStatement("Select * from Librarian where EmployeeID LIKE ?");
            stmt.setString(1, "%" + searchTerm + "%");
            
            rs = stmt.executeQuery();
            return rs;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ResultSet searchLibrarianByName(String searchTerm)
    {
        try
        {         
            PreparedStatement stmt = con.prepareStatement("Select * from Librarian where Name LIKE ?");
            stmt.setString(1, "%" + searchTerm + "%");
            
            rs = stmt.executeQuery();
            return rs;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ResultSet searchLibrarianByEmail(String searchTerm)
    {
        try
        {         
            PreparedStatement stmt = con.prepareStatement("Select * from Librarian where Email LIKE ?");
            stmt.setString(1, "%" + searchTerm + "%");
            
            rs = stmt.executeQuery();
            return rs;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ArrayList searchAdministratorByID(String searchTerm)
    {
        try
        {         
            ArrayList adminList = new ArrayList();

            int index = 0;
            
            PreparedStatement stmt = con.prepareStatement("Select * from Administrator where EmployeeID LIKE ?");
            stmt.setString(1, "%" + searchTerm + "%");
            
            rs = stmt.executeQuery();
            
            while (rs.next())
            {
                adminList.add(rs.getString("EmployeeID"));
            }

            return adminList;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ArrayList searchAdminByName(String searchTerm)
    {
        try
        {         
            ArrayList adminList = new ArrayList();

            int index = 0;
            
            PreparedStatement stmt = con.prepareStatement("Select * from Administrator where Name LIKE ?");
            stmt.setString(1, "%" + searchTerm + "%");
            
            rs = stmt.executeQuery();
            
            while (rs.next())
            {
                adminList.add(rs.getString("EmployeeID"));
            }

            return adminList;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ArrayList searchAdmin(String searchTerm)
    {
        try
        {
            ArrayList adminList = new ArrayList();
            
            ArrayList searchedByID = searchAdministratorByID(searchTerm);
            ArrayList searchedByName = searchAdminByName(searchTerm);
            
            if (searchedByID.size() > 0)
            {
                for (int i = 0; i < searchedByID.size(); i++)
                {
                    adminList.add(searchedByID.get(i));
                }
            }
            
            if (searchedByName.size() > 0)
            {
                for (int i = 0; i < searchedByName.size(); i++)
                {
                    adminList.add(searchedByName.get(i));
                }
            }
            
            Set adminSet = new HashSet();
            adminSet.addAll(adminList);
            adminList.clear();
            adminList.addAll(adminSet);
            return adminList;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    

    public String generatePassword()
    {
        int PASSWORD_LENGTH = 8;
        String characters = "abcdefghijklmnopqrstuvxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String newPassword = "";
        
        Random random = new SecureRandom();
        
        for (int i = 0; i < PASSWORD_LENGTH ; i++)
        {
            int index = (int)(random.nextDouble()*characters.length());
            newPassword += characters.substring(index, index + 1);
        }
        
        return newPassword;
    }
    
    public byte[] getBookCover(String BookID)
    {
        try
        {
            String sql = "select BookCover from Book where BookID = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, BookID);
            
            rs = pst.executeQuery();
            
            while (rs.next())
            {
                byte[] imageData = rs.getBytes("BookCover");
                return imageData;
            }
            
            return null;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public void setBookCover(String BookID, byte[] imageData)
    {
        try
        {
            String sql = "update Book set BookCover = ? where BookID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setBytes(1, imageData);
            stmt.setString(2, BookID);
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Book Cover Updated Successfully!", "Update", JOptionPane.INFORMATION_MESSAGE);
            
            
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public byte[] getLibrarianImage(String employeeID)
    {
        try
        {
            String sql = "select Image from Librarian where EmployeeID = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, employeeID);
            
            rs = pst.executeQuery();
            
            while (rs.next())
            {
                byte[] imageData = rs.getBytes("Image");
                return imageData;
            }
            
            return null;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    
    public void setLibrarinImage(String EmployeeID, byte[] imageData)
    {
        try
        {
            String sql = "update Librarian set Image = ? where EmployeeID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setBytes(1, imageData);
            stmt.setString(2, EmployeeID);
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Librarian Image Updated Successfully!", "Update", JOptionPane.INFORMATION_MESSAGE);
            
            
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public byte[] getPatronImage(String ID)
    {
        try
        {
            String sql = "select PatronImage from Patron where ID = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, ID);
            
            rs = pst.executeQuery();
            
            while (rs.next())
            {
                byte[] imageData = rs.getBytes("PatronImage");
                return imageData;
            }
            
            return null;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ResultSet getAllBook()
    {
        try
        {
            String sql = "Select BookID, ISBN, Title, Author, Publisher, Edition, Category, is_Rented, is_Reserved, Course, Department from Book";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            rs = stmt.executeQuery();
            return rs;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public void changeAdminPassword(String employeeID, String password)
    {
        try
        {
            String sql = "Update Administrator set Password = ? where EmployeeID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, SHA1(password));
            stmt.setString(2, employeeID);
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Administrator Password Changed!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error : Password Not Changed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void changeLibrarianPassword(String employeeID, String password)
    {
        try
        {
            String sql = "Update Librarian set Password = ? where EmployeeID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, SHA1(password));
            stmt.setString(2, employeeID);
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Librarian Password Changed!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error : Password Not Changed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public void changePatronPassword(String ID, String password)
    {
        try
        {
            String sql = "Update Patron set Password = ? where ID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, SHA1(password));
            stmt.setString(2, ID);
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Patron Password Changed!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error : Password Not Changed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setPatronImage(String ID, byte[] imageData)
    {
        try
        {
            String sql = "update Patron set PatronImage = ? where ID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setBytes(1, imageData);
            stmt.setString(2, ID);
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Patron Image Updated Successfully!", "Update", JOptionPane.INFORMATION_MESSAGE);
            
            
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public String getCurrentDate()
    {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(calendar.getTime());
    }
    
    public String SHA1(String a)
    {
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            byte[] array = messageDigest.digest(a.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < array.length; ++i)
            {
                stringBuffer.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            
            return stringBuffer.toString();
        }
        
        catch (Exception e)
        {
            return null;
        }
    }
    
   
    
}





            
        
            