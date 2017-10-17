package Entity;

import Controller.Exceptions;
import Controller.JDBC;
import Tools.Tools;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Amanuel
 */

public class Administrator {
    
    private String employeeID;
    private String name;
    private String password;
    private String email;
    
    private JDBC conn;
    
    public Administrator(){}
        
    public Administrator(String employeID, String name, String password, String email)
    {
        this.employeeID = employeID;
        this.name = name;
        this.password = password;
        this.email = email;
        
        conn = new JDBC();
    }
    
    public void setID(String employeeID)
    {
        this.employeeID = employeeID;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getID()
    {
        return employeeID;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public void addPatron(String name, String ID, String password, byte[] patronImage, String email, String department)
    {
        try
        {
            Patron patron = new Patron(name, ID, password, patronImage, email, department);
            boolean status = patron.registerPatron();
            
            if (status)
                Transaction.addTransaction(Transaction.PATRON_ADDED, patron, this);
            
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void removePatron(String ID)
    {
        
                
        try
        {
            Patron patron = conn.returnPatron(ID);
            boolean status = patron.removePatron();
            
            if (status)
                Transaction.addTransaction(Transaction.PATRON_REMOVED, patron, this);
        }
        
        catch (Exception e)
        {
            Patron patron = new Patron();
            JOptionPane.showMessageDialog(null, Exceptions.getErrorMessage(e, "Remove",  patron), "Error", JOptionPane.ERROR_MESSAGE);;
        }
    }
    
    public void updatePatron(String name, String ID, byte[] patronImage, String email, String department)
    {
        try
        {
            Patron patron = conn.returnPatron(ID);
            conn.updatePatron(patron, name, email, department);
            
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void addLibrarian(String name, String employeeID, String password, String email)
    {
        try
        {
            Librarian librarian = new Librarian(name, employeeID, password, email);
            boolean status = librarian.registerLibrarian();
            
            if (status)
                Transaction.addTransaction(Transaction.LIBRARIAN_ADDED, librarian, this);
            
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
      
    public void removeLibrarian(String employeeID)
    {
        try
        {
            Librarian librarian = conn.returnLibrarian(employeeID);
            boolean status = librarian.removeLibrarian();
            
            if (status)
                Transaction.addTransaction(Transaction.LIBRARIAN_REMOVED, librarian, this);
        }
        
        catch (Exception e)
        {
            Librarian librarian = new Librarian();
            JOptionPane.showMessageDialog(null, Exceptions.getErrorMessage(e, "Remove", librarian), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void updateLibrarian(String name, String employeeID, String email)
    {
        try
        {
            Librarian librarian = conn.returnLibrarian(employeeID);
            conn.updateLibrarian(librarian, name, email);
            
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void addAdministrator(Administrator newAdmin)
    {
        try
        {
            conn.addAdministrator(newAdmin);
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void removeAdministrator(Administrator admin)
    {
        try
        {
            Administrator administrator = conn.returnAdministrator(admin.getID());
            conn.removeAdministrator(administrator.getID());
        }
        
        catch (Exception e)
        {
            Administrator administrator = new Administrator();
            JOptionPane.showMessageDialog(null, Exceptions.getErrorMessage(e, "Remove", administrator), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void updateAdministrator(String employeeID, String name, String email)
    {
        try
        {
            Administrator admin = conn.returnAdministrator(employeeID);
            conn.updateAdministrator(admin, name, email);
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }      
    }
    
    public void resetPassword()
    {
        try
        {
            if (Tools.isConnected())
            {
                String newPassword = conn.resetPasswordAdministrator(employeeID);
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
    
    public ResultSet seachLibrarianID(String searchTerm)
    {
        try
        {
            return conn.searchLibrarianByID(searchTerm); 
        }
        
        catch (Exception e)
        {
            return null;
        }
    }
    
    public ResultSet seachLibrarianName(String searchTerm)
    {
        try
        {
            return conn.searchLibrarianByName(searchTerm);            
        }
        
        catch (Exception e)
        {
            return null;
        }
    }
    
    public ResultSet seachLibrarianEmail(String searchTerm)
    {
        try
        {
            return conn.searchLibrarianByEmail(searchTerm);            
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
    
    public ResultSet searchPatronByDepartment(String searchTerm)
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
    
    
    public ResultSet searchPatronByEmail(String searchTerm)
    {
        try
        {
            return conn.searchPatronByEmail(searchTerm);            
        }
        
        catch (Exception e)
        {
            return null;
        }
    }
    
    public ResultSet searchPatronByID(String searchTerm)
    {
        try
        {
            return conn.searchPatronByID(searchTerm);            
        }
        
        catch (Exception e)
        {
            return null;
        }
    }

    public void changePassword(String password)
    {
        try
        {
            conn.changeAdminPassword(this.getID(), password);
        }
        
        catch (Exception e)
        {}
    }
    
    public void changePatronPassword(String ID, String password)
    {
        try
        {
            conn.changePatronPassword(ID, password);
        }
        
        catch (Exception e)
        {}
    }
    
}
