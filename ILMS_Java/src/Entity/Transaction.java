package Entity;

import Controller.JDBC;
import java.util.Date;

/**
 *
 * @author Amanuel
 */
public class Transaction {
    
    public static final int RENT = 0;
    public static final int RETURN = 1;
    public static final int BOOK_ADDED = 2;
    public static final int BOOK_REMOVED = 3;
    public static final int PATRON_ADDED = 4;
    public static final int PATRON_REMOVED = 5;
    public static final int LIBRARIAN_ADDED = 6;
    public static final int LIBRARIAN_REMOVED = 7;
    
    private int transactionNumber;
    private Date transactionDate;
    private int transactionType;
    private String actorPatronID;
    private String transactedBookID;
    private String librarianID;
    private String administratorID;
    
    private static JDBC conn = new JDBC();;
   
    public static void addTransaction(int transactionType, Book book, Librarian librarian, Patron patron)
    {
        if (transactionType == Transaction.RENT)
        {
            conn.registerRentTransaction(librarian, book, patron);
        }
        
        else if (transactionType == Transaction.RETURN)
        {
            conn.registerReturnTransaction(librarian, book, patron);
        }
        
    }
    
    public static void addTransaction(int transactionType, Book book, Librarian librarian)
    {
        if (transactionType == Transaction.BOOK_ADDED)
        {
            conn.registerBookAddTransaction(book, librarian);
        }
        
        else if (transactionType == Transaction.BOOK_REMOVED)
        {
            conn.registerBookRemoveTransaction(book, librarian);
        }
    }
    
    public static void addTransaction(int transactionType, Patron patron, Administrator administrator)
    {
        if (transactionType == Transaction.PATRON_ADDED)
        {
            conn.registerPatronAddTransaction(patron, administrator);
        }
        
        else if (transactionType == Transaction.PATRON_REMOVED)
        {
            conn.registerPatronRemoveTransaction(patron, administrator);
        }
    }
    
    public static void addTransaction(int transactionType, Librarian librarian, Administrator administrator)
    {
        if (transactionType == Transaction.LIBRARIAN_ADDED)
        {
            conn.registerLibrarianAddTransaction(librarian, administrator);
        }
        
        else if (transactionType == Transaction.LIBRARIAN_REMOVED)
        {
            conn.registerLibrarianRemoveTransaction(librarian, administrator);
        }
    }
    
    
    
}
