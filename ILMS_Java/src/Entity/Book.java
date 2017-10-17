package Entity;

import Controller.JDBC;

/**
 *
 * @author Amanuel
 */
public class Book {
    
    JDBC database;
    
    private String title;
    private String ISBN;
    private String bookID;
    private byte[] bookCover;
    private String publisher;
    private String author;
    private String edition;
    private String category;
    private int no_of_page;
    private float rate;
    private boolean reservation;
    private boolean rented;

    private String Course;
    private String Department;
    
    private String description;
    //private Patron[] renters;
    //private Patron currentRenter;
    
    public Book() {}
    
    public Book(String ISBN, String title, String author, String edition)
    {
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
        this.edition = edition;
        
        database = new JDBC();
    }
    
    public Book(String title, String ISBN, String bookID, byte[] bookCover, String publisher,
            String author, String edition, String category, int no_of_page, String Course, String Department, String Description)
    {
        this.title = title;
        this.ISBN = ISBN;
        this.bookID = bookID;
        this.bookCover = bookCover;
        this.publisher = publisher;
        this.author = author;
        this.edition = edition;
        this.category = category;
        this.no_of_page = no_of_page;
        this.Course = Course;
        this.Department = Department;
        this.description = description;
        
        
        this.rented = false;
        this.reservation = false;
        this.rate = 0;
        
        database = new JDBC();
    }
    
public Book(String title, String ISBN, String bookID, byte[] bookCover, String publisher,
            String author, String edition, String category, int no_of_page, boolean rented, boolean reservation, float rate,
            String Course, String Department, String description)
{
        this.title = title;
        this.ISBN = ISBN;
        this.bookID = bookID;
        this.bookCover = bookCover;
        this.publisher = publisher;
        this.author = author;
        this.edition = edition;
        this.category = category;
        this.no_of_page = no_of_page;
        this.Course = Course;
        this.Department = Department;
        
        this.rented = rented;
        this.reservation = reservation;
        this.rate = 0;
        
        this.description = description;
        
        database = new JDBC();
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public String getISBN()
    {
        return ISBN;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public String getDepartment()
    {
        return Department;
    }
    
    public String getCourse()
    {
        return Course;
    }
    
    public String getBookID()
    {
        return bookID;
    }
    
    public byte[] getBookCover()
    {
        return bookCover;
    }
    
    public String getPublisher()
    {
        return publisher;
    }
    
    public String getEdition()
    {
        return edition;
    }
    
    public String getCategory()
    {
        return category;
    }
    
    public int getNoOfPage()
    {
        return no_of_page;
    }
    
    public float getRate()
    {
        return rate;
    }
    
    public String getAuthor()
    {
        return author;
    }
     
    public void setTitle(String title)
    {
        this.title = title;
        
    }
    
    public void setISBN(String ISBN)
    {
        this.ISBN = ISBN;
    }
    
    public void setCourse(String Course)
    {
        this.Course = Course;
    }
    
    public void setDepartment(String Department)
    {
        this.Department = Department;
    }
    
    public void setBookID(String bookID)
    {
        this.bookID = bookID;
    }
    
    public void setCoveer(byte[] bookCover)
    {
        this.bookCover = bookCover;
    }
    
    public void setAuthor(String author)
    {
        this.author = author;
    }
    
    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }
    
    public void setEdition(String edition)
    {
        this.edition = edition;
    }
    
    public void setCategory(String category)
    {
        this.category = category;
    }
    
    public void setNoOfPage(int no_of_page)
    {
        this.no_of_page = no_of_page;
    }
    
    public void setRate(float rate)
    {
        this.rate = rate;
    }
    
    public boolean isRented()
    {
        return rented;
    }
    
    public boolean isAvailable()
    {
        return ((!(rented) && !(reservation)));
    }
    
    public boolean isReserved()
    {
        return reservation;
    }
    
    public void setRented()
    {
        rented = true;
    }
    
    public void setReturned()
    {
        rented = false;
    }
    
    public void setReserved()
    {
        reservation = true;
    }
    
    public void removeReservation()
    {
        reservation = false;
    }
            
            
    public boolean registerBook()
    {
        return database.addBook(this);
    }
    
    public boolean removeBook()
    {
        return database.removeBook(this.getBookID(), this.getISBN());
    }

          
    
}
