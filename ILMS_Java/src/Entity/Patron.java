package Entity;

import Controller.JDBC;

/**
 *
 * @author Amanuel
 */
public class Patron {
    
    private String name;
    private String ID;
    private byte[] patronImage;
    private String password;
    private String email;
    private String department;
    
    private boolean rented;
    private boolean reserved;
    
    JDBC database;
    
    public Patron()
    {
        
    }
    
    public Patron(String name, String ID, String password, byte[] patronImage, String email, String department)
    {
        this.name = name;
        this.ID = ID;
        this.password = password;
        this.patronImage = patronImage;
        this.email = email;
        
        this.department = department;
        
        database = new JDBC();
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
    
    public String getId()
    {
        return ID;
    }
    
    public String getDepartment()
    {
        return department;
    }
    
    public byte[] getPatronImage()
    {
        return patronImage;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setDepartment(String department)
    {
        this.department = department;
    }
    public void setID(String ID)
    {
        this.ID = ID;
    }
    
    public void setPatronImage(byte[] patronImage)
    {
        this.patronImage = patronImage;
    }
    
    public boolean hasReserved()
    {
        return reserved;
    }
    
    public boolean hasRented()
    {
        return rented;
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
        reserved = true;
    }
    
    public void removeReservation()
    {
        reserved = false;
    }
    
    public boolean registerPatron()
    {
        return database.addPatron(this);
    }
    
    public boolean removePatron()
    {
        return database.removePatron(this.getId());
    }
    
}
