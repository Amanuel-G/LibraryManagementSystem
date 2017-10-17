package Controller;

import javax.swing.JOptionPane;

/**
 *
 * @author Helina
 */
public class Exceptions {
    
    
    public Exceptions(Exception e)
    {
        
    }
    
    public static String getErrorMessage(Exception e, String operationType, Object object)
    {
        System.out.print(e.getClass().toString());
           
        if ((e.getClass().toString().equals("class com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException")) && operationType.equals("Register"))
        {
            if (object.getClass().toString().equals("class Entity.Librarian"))
            {
                return "Librarian ID Duplicated : There exist a Librarian record with the same ID.";
            }
            
            else if (object.getClass().toString().equals("class Entity.Book"))
            {
                return "Book ID Duplicated : There exist a Book record with the same ID";
            }
            
            else if (object.getClass().toString().equals("class Entity.Patron"))
            {
                return "Patron ID Duplicated : There exist a Patron record with the same ID";
            }
            
            else if (object.getClass().toString().equals("class Entity.Administrator"))
            {
                return "Administrator ID Duplicated : There exist an Administrator record with the same ID";
            }
            
            else
            {
                return "ID Diplicated!";
            }
        }
        
        else if ((e.getClass().toString().equals("class java.lang.NullPointerException")) && operationType.equals("Remove"))
        {
            
            if (object.getClass().toString().equals("class Entity.Patron"))
            {
                return "Deletion Failed : The Patron Doesn't Exist In The Database!";
            }
            
            else if (object.getClass().toString().equals("class Entity.Librarian"))
            {
                return "Deletion Failed : The Librarian Doesn't Exist In The Database!";
            }
            
            else if (object.getClass().toString().equals("class Entity.Administrator"))
            {
                return "Deletion Failed : The Administrator Doesn't Exist In The Database!";
            }
            
            else if (object.getClass().toString().equals("class Entity.Book"))
            {
                return "Deletion Failed : The Book Doesn't Exist In The Database!";
            }
            
            else
            {
                return e.toString();
            }
        }
        
        else if ((e.getClass().toString().equals("class java.lang.NullPointerException")) && operationType.equals("PasswordReset"))
        {
            
            if (object.getClass().toString().equals("class Entity.Patron"))
            {
                return "Password Reset Failed : The Patron Doesn't Exist In The Database!";
            }
            
            else if (object.getClass().toString().equals("class Entity.Librarian"))
            {
                return "Password Reset Failed : The Librarian Doesn't Exist In The Database!";
            }
            
            else if (object.getClass().toString().equals("class Entity.Administrator"))
            {
                return "Password Reset Failed : The Administrator Doesn't Exist In The Database!";
            }
            
            else if (object.getClass().toString().equals("class Entity.Book"))
            {
                return "Password Reset Failed : The Book Doesn't Exist In The Database!";
            }
            
            else
            {
                return e.toString();
            }
        }
        
        else if ((e.getClass().toString().equals("class java.lang.NullPointerException")) && operationType.equals("Update"))
        {
            
            if (object.getClass().toString().equals("class Entity.Patron"))
            {
                return "Update Failed : The Patron Doesn't Exist In The Database!";
            }
            
            else if (object.getClass().toString().equals("class Entity.Librarian"))
            {
                return "Update Failed : The Librarian Doesn't Exist In The Database!";
            }
            
            else if (object.getClass().toString().equals("class Entity.Administrator"))
            {
                return "Update Failed : The Administrator Doesn't Exist In The Database!";
            }
            
            else if (object.getClass().toString().equals("class Entity.Book"))
            {
                return "Update Failed : The Book Doesn't Exist In The Database!";
            }
            
            else
            {
                return e.toString();
            }
        }
        
        else if ((e.getClass().toString().equals("class java.lang.NullPointerException")) && operationType.equals("Report"))
        {
            
            if (object.getClass().toString().equals("class Entity.Patron"))
            {
                return "Report Generation Failed : The Patron Doesn't Exist In The Database!";
            }
            
            else if (object.getClass().toString().equals("class Entity.Librarian"))
            {
                return "Report Generation Failed : The Librarian Doesn't Exist In The Database!";
            }
            
            else if (object.getClass().toString().equals("class Entity.Administrator"))
            {
                return "Report Generation Failed : The Administrator Doesn't Exist In The Database!";
            }
            
            else if (object.getClass().toString().equals("class Entity.Book"))
            {
                return "Report Generation Failed : The Book Doesn't Exist In The Database!";
            }
            
            else
            {
                return e.toString();
            }
        }
        
        else if ((e.getClass().toString().equals("class java.lang.NullPointerException")) && operationType.equals("Rent"))
        {
            
            if (object.getClass().toString().equals("class Entity.Patron"))
            {
                return "Book Rent Failed : The Patron / Book Doesn't Exist In The Database!";
            }
            
            
            else if (object.getClass().toString().equals("class Entity.Book"))
            {
                return "Book Rent Failed : The Book / Book Doesn't Exist In The Database!";
            }
            
            else
            {
                return e.toString();
            }
        }
        
        else if ((e.getClass().toString().equals("class java.lang.NullPointerException")) && operationType.equals("Return"))
        {
            
            if (object.getClass().toString().equals("class Entity.Patron"))
            {
                return "Book Return Failed : The Patron / Book Doesn't Exist In The Database!";
            }
            
            
            else if (object.getClass().toString().equals("class Entity.Book"))
            {
                return "Book Return Failed : The Book / Book Doesn't Exist In The Database!";
            }
            
            else
            {
                return e.toString();
            }
        }
        
        else 
        {
            return e.toString();
        }
    }
}
