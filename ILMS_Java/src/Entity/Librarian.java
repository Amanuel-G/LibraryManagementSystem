package Entity;

import Controller.Exceptions;
import Controller.JDBC;
import Tools.Tools;
import java.security.MessageDigest;
import javax.swing.JOptionPane;
import java.net.*;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Amanuel
 */

public class Librarian {
    
    private String name;
    private String employeeID;
    private String password;
    private String email;
    private JDBC conn;
    
    public Librarian(){}
    
    public Librarian(String name, String employeeID, String password, String email)
    {
        conn = new JDBC();
        
        this.name = name;
        this.employeeID = employeeID;
        this.password = password;
        this.email = email;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public String getID()
    {
        return employeeID;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setID(String employeeID)
    {
        this.employeeID = employeeID;
    }
    
    public boolean registerLibrarian()
    {
        return conn.addLibrarian(this);
    }
    
    public boolean removeLibrarian()
    {
        return conn.removeLibrarian(this.employeeID);
    }
    
    public void lendBook(Book book, Patron patron)
    {
        try
        {
            
            Patron currentPatron =conn.returnPatron(patron.getId());
            if (currentPatron == null)
            {
                throw new NullPointerException();
                
            }
            
            Book currentBook = conn.returnBook(book.getBookID());
            
            if (currentBook.isAvailable())
            {
                conn.setBookRented(currentBook.getBookID());
                //conn.registerRentTransaction(this, currentBook, patron);
                Transaction.addTransaction(Transaction.RENT, book, this, patron);

            }
            else
            {
                JOptionPane.showMessageDialog(null, "The Book is not Available!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Exceptions.getErrorMessage(e, "Rent", patron), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void returnBook(Book book, Patron patron)
    {
        try
        {
            Patron currentPatron =conn.returnPatron(patron.getId());
            if (currentPatron == null)
            {
                throw new NullPointerException();

            }


            Book currentBook = conn.returnBook(book.getBookID());
            if (currentBook.isRented())
            {
                conn.setBookReturned(currentBook.getBookID());
                //conn.registerReturnTransaction(this, book, patron);
                Transaction.addTransaction(Transaction.RETURN, book, this, patron);
            }

            else
            {
                JOptionPane.showMessageDialog(null, "No such transaction exists!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Exceptions.getErrorMessage(e, "Return", patron), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void addBook(String title, String ISBN, String BookID, byte[] bookCover, 
            String publisher, String author, String edition, String category, int no_of_Page, String Course, String Department, String description)
            
    {
        try
        {
            Book book = new Book(title, ISBN, BookID, bookCover, publisher, author, edition, category, no_of_Page, Course, Department, description);
            boolean status = book.registerBook();
            
            if (status)
                Transaction.addTransaction(Transaction.BOOK_ADDED, book, this);
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void removeBook(String BookID)
    {
        try
        {
            Book book = conn.returnBook(BookID);
            boolean status = book.removeBook();
            
            if (status)
                Transaction.addTransaction(Transaction.BOOK_REMOVED, book, this);
        }
        
        catch (Exception e)
        {
            Book book = new Book();
            JOptionPane.showMessageDialog(null, Exceptions.getErrorMessage(e, "Remove", book), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void updateBook(String title, String ISBN, String BookID, byte[] bookCover, 
            String publisher, String author, String edition, String category, int no_of_Page, String course, String Department, String description)
    {
        try
        {
            Book book = conn.returnBook(BookID);
            conn.updateBook(book, title, bookCover, author, publisher, edition, category, no_of_Page, course, Department, description);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public ResultSet searchBookAdvanced(String searchTerm)
    {
        try
        {
            return conn.searchBook(searchTerm);

        }
        
        catch (Exception e)
        {
            return null;
        }
    }
    
    public ResultSet searchBookTitle(String searchTerm)
    {
        try
        {
            return conn.searchBookByTitle(searchTerm);
        }
        
        catch (Exception e)
        {
            return null;
        }
    }
    
    public ResultSet searchBookAuthor(String searchTerm)
    {
        try
        {
            return conn.searchBookByAuthor(searchTerm);

        }
        
        catch (Exception e)
        {
            return null;
        }
    }
    
    public ResultSet searchBookISBN(String searchTerm)
    {
        try
        {
            return conn.searchBookByISBN(searchTerm);
        }
        
        catch (Exception e)
        {
            return null;
        }
    }
    
    public ResultSet searchBookCategory(String searchTerm)
    {
        try
        {
            return conn.searchBookByCategory(searchTerm);
        }
        
        catch (Exception e)
        {
            return null;
        }
    }
    
    public ResultSet searchBookEdition(String searchTerm)
    {
        try
        {
            return conn.searchBookByEdition(searchTerm);
        }
        
        catch (Exception e)
        {
            return null;
        }
    }
        
    public ResultSet searchPatronName(String searchTerm)
    {
        try
        {
            return conn.searchPatronByName(searchTerm);
        }
        
        catch (Exception e)
        {
            return null;
        }
    }
    
    public ResultSet searchPatronDepartment(String searchTerm)
    {
        try
        {
            return conn.searchPatronByDepartment(searchTerm);
        }
        
        catch (Exception e)
        {
            return null;
        }
    }
    
    public void resetPassword()
    {
        try
        {
            if (Tools.isConnected())
            {
                String newPassword = conn.resetPasswordLibrarian(employeeID);
                if (newPassword != null)
                {
                    Tools.sendPasswordResetEmail(email, newPassword);
                }
            }
            
            else
            {
                JOptionPane.showMessageDialog(null, "No Internet Connection", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }      

    }
    
    public void resetPatronPassword(String patronID)
    {
        try
        {
            if (Tools.isConnected())
            {
                String newPassword = conn.resetPasswordPatron(patronID);
                if (newPassword != null)
                {
                    Tools.sendPasswordResetEmail(email, newPassword);
                }
            }
            
            else
            {
                JOptionPane.showMessageDialog(null, "No Internet Connection", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }    
    }
    
    public void changePassword(String password)
    {
        try
        {
            conn.changeLibrarianPassword(this.getID(), password);
        }
        
        catch (Exception e) {}
    }
    
}
