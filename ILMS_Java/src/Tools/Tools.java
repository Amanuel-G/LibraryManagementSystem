package Tools;

import Controller.JDBC;
import Entity.Administrator;
import Entity.Librarian;
import Entity.Patron;
import com.sun.xml.internal.org.jvnet.mimepull.MIMEMessage;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author biruk
 */

public class Tools {
    
    
    public static boolean checkEmailFormat(String email)
    {
        String EMAIL_PATTER = "^[a-zA-Z0-9]{3,25}@[a-zA-Z0-9]{3,10}.[a-zA-Z]{2,3}$";
        Pattern pattern = Pattern.compile(EMAIL_PATTER);
        Matcher regexMather = pattern.matcher(email);
        
        if (!regexMather.matches())
        {
            return false;
        } 
        else
        {
            return true;
        }
    }
    
    public static void sendPasswordResetEmail(String recipentEmail, String password)
    {
        Properties properties = new Properties(); 
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        
        Session session = Session.getDefaultInstance(properties, 
                new javax.mail.Authenticator()
                {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication("ilms.aait@gmail.com", "B@rcelon@10");
                    }
                }
                );
        
        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ilms.aait@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipentEmail));
            message.setSubject("ILMS Password Reset");
            message.setText("Your new Password is : " + password);
            
            Transport.send(message);
            
            JOptionPane.showMessageDialog(null, "Email Sent");
            
        }
        
        catch (javax.mail.MessagingException e)
        {
            JOptionPane.showMessageDialog(null, "Email Not Sent : No Internet Connection.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
         
    }
    
    public static boolean isConnected()
    {
        Socket socket = new Socket();
        InetSocketAddress address = new InetSocketAddress("www.gmail.com", 80);
        
        try
        {
            socket.connect(address, 3000);
            return true;
        }
        
        catch (Exception e)
        {
            return false;
        }
        
        finally
        {
            try
            {
                socket.close();
            }
            
            catch (Exception e) {}
        }
        
        
    }
    
    public static void resetPassword(String ID, String email)
    {
        if (checkEmailFormat(email))
        {
            JDBC conn = new JDBC();
            Object person = conn.getEmailOwner(ID, email);
            
            if (person == null)
            {
                JOptionPane.showMessageDialog(null, "Password Reset Failed : Invalid ID/Email Entered.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if (person.getClass().toString().equals("class Entity.Patron"))
            {
                Patron patron = (Patron) person;

            }

            else if ((person.getClass().toString().equals("class Entity.Librarian")))
            {
                Librarian librarian = (Librarian) person;
                librarian.resetPassword();
            }

            else if ((person.getClass().toString().equals("class Entity.Administrator")))
            {
                Administrator administrator = (Administrator) person;
                administrator.resetPassword();
            }

            else {}
        }
        
        else
        {
            JOptionPane.showMessageDialog(null, "Invalid Email Format!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static int isFilled(String[] fields)
    {
        for (int i = 0; i < fields.length; i++)
        {
            if (fields[i].equals("") || fields[i] == null)
                return i;
        }
        
        return -1;
    }
    
    public static boolean isSelected(String[] fields)
    {
        for (int i = 0; i < fields.length; i++)
        {
            if (fields[i].equals("") || fields[i] == null)
                return false;
        }
        
        return true;
    }
    
}
