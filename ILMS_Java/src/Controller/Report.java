/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Book;
import Entity.Librarian;
import Entity.Patron;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Amanuel
 */
public class Report {
    
    private static JDBC conn = new JDBC();;
    

    /*
    Generate a Report for All Rented Books 
    */
    public static void generateRentReport()
    {
        try
        {
            String currentDate = Report.getCurrentDate();
            float[] columnWidths = {2, 8, 5, 5, 5};
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Reports/"+currentDate+".pdf"));
            
            document.open();
            Image image = Image.getInstance("Images/logo.png");
            document.add(image);
            
            document.add(new Paragraph("Book Report", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.DARK_GRAY)));
            document.add(new Paragraph("Date : " + new Date().toString(), FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.NORMAL, BaseColor.RED)));

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            ArrayList<String[]> result = conn.getAllRentReport();

            PdfPTable table = new PdfPTable(columnWidths);
            table.setWidthPercentage(100);

            int size = result.size();
            int count = 0;
            
            PdfPCell cell = new PdfPCell(new Paragraph("Book Transactions"));
            cell.setColspan(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBackgroundColor(BaseColor.GREEN);
            cell.setFixedHeight(36f);
            cell.setMinimumHeight(36f);
            table.addCell(cell);
            
            table.addCell("No");
            table.addCell("Date");
            table.addCell("Librarian ID");
            table.addCell("Book ID");
            table.addCell("Patron ID");

            while (count < size)
            {
                table.addCell(result.get(count)[0].toString());
                table.addCell(result.get(count)[1].toString());
                table.addCell(result.get(count)[2].toString());
                table.addCell(result.get(count)[3].toString());
                table.addCell(result.get(count)[4].toString());

                count += 1;
            }

            document.add(table);
            
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            document.add(new Paragraph("General Comment : ", FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.NORMAL, BaseColor.DARK_GRAY)));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            document.add(new Paragraph("Administrator Comment : ", FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.NORMAL, BaseColor.DARK_GRAY)));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            document.add(new Paragraph("Signature : ", FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.NORMAL, BaseColor.DARK_GRAY)));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            

            JOptionPane.showMessageDialog(null, "Report Generated!");

            document.close();
            }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    /*
    Generate a Report about Recently Added Books
    */
    
    public static void generateAddBookTransactionReport()
    {
        try
        {
            String currentDate = Report.getCurrentDate();
            float[] columnWidths = {2, 8, 5, 5};
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Reports/" + currentDate + ".pdf"));
            
            document.open();
            Image image = Image.getInstance("Images/logo.png");
            document.add(image);
            
            document.add(new Paragraph("Book Report", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.DARK_GRAY)));
            document.add(new Paragraph("Date : " + new Date().toString(), FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.NORMAL, BaseColor.RED)));
            
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            ArrayList<String[]> result = conn.getAddBookTransaction();

            PdfPTable table = new PdfPTable(columnWidths);
            table.setWidthPercentage(100);

            int size = result.size();
            int count = 0;
            
            PdfPCell cell = new PdfPCell(new Paragraph("Registered Books"));
            cell.setColspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBackgroundColor(BaseColor.GREEN);
            cell.setFixedHeight(36f);
            cell.setMinimumHeight(36f);
            table.addCell(cell);
            
            table.addCell("No");
            table.addCell("Date");
            table.addCell("Librarian ID");
            table.addCell("Book ID");
            
            
            while (count < size)
            {
                table.addCell(result.get(count)[0].toString());
                table.addCell(result.get(count)[1].toString());
                table.addCell(result.get(count)[2].toString());
                table.addCell(result.get(count)[3].toString());

                count += 1;
            }

            document.add(table);
            
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            document.add(new Paragraph("General Comment : ", FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.NORMAL, BaseColor.DARK_GRAY)));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            document.add(new Paragraph("Administrator Comment : ", FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.NORMAL, BaseColor.DARK_GRAY)));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            document.add(new Paragraph("Signature : ", FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.NORMAL, BaseColor.DARK_GRAY)));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            

            JOptionPane.showMessageDialog(null, "Report Generated!");

            document.close();
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    /*
    Generate a Report about Recently Registered Members(Patrons)
    */
    
    public static void generateAddMemberTransactionReport()
    {
        try
        {
            String currentDate = Report.getCurrentDate();
            float[] columnWidths = {2, 8, 5, 5};
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Reports/" + currentDate + ".pdf"));
            
            document.open();
            Image image = Image.getInstance("Images/logo.png");
            document.add(image);
            
            document.add(new Paragraph("Member Report", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.DARK_GRAY)));
            document.add(new Paragraph("Date : " + new Date().toString(), FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.NORMAL, BaseColor.RED)));
            
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            ArrayList<String[]> result = conn.getAddedPatronTransaction();

            PdfPTable table = new PdfPTable(columnWidths);
            table.setWidthPercentage(100);

            int size = result.size();
            int count = 0;
            
            PdfPCell cell = new PdfPCell(new Paragraph("Registered Members"));
            cell.setColspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBackgroundColor(BaseColor.GREEN);
            cell.setFixedHeight(36f);
            cell.setMinimumHeight(36f);
            table.addCell(cell);
            
            table.addCell("No");
            table.addCell("Date");
            table.addCell("Patron ID");
            table.addCell("Administrator ID");
            
            
            while (count < size)
            {
                table.addCell(result.get(count)[0].toString());
                table.addCell(result.get(count)[1].toString());
                table.addCell(result.get(count)[2].toString());
                table.addCell(result.get(count)[3].toString());

                count += 1;
            }

            document.add(table);
            
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            document.add(new Paragraph("General Comment : ", FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.NORMAL, BaseColor.DARK_GRAY)));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            document.add(new Paragraph("Administrator Comment : ", FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.NORMAL, BaseColor.DARK_GRAY)));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            document.add(new Paragraph("Signature : ", FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.NORMAL, BaseColor.DARK_GRAY)));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            

            JOptionPane.showMessageDialog(null, "Report Generated!");

            document.close();
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    /*
    Generate a Report about all Unreturned Books
    */
    
    public static void generateUnreturnedBookReport()
    {
        try
        {
            String currentDate = Report.getCurrentDate();
            float[] columnWidths = {2, 8, 5, 5, 5};
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Reports/" + currentDate + ".pdf"));
            
            document.open();
            Image image = Image.getInstance("Images/logo.png");
            document.add(image);
            
            document.add(new Paragraph("Book Report", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.DARK_GRAY)));
            document.add(new Paragraph("Date : " + new Date().toString(), FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.NORMAL, BaseColor.RED)));
            
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            ArrayList<String[]> result = conn.getAllUnreturnedBookTransaction();

            PdfPTable table = new PdfPTable(columnWidths);
            table.setWidthPercentage(100);

            int size = result.size();
            int count = 0;
            
            PdfPCell cell = new PdfPCell(new Paragraph("Rented Books"));
            cell.setColspan(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBackgroundColor(BaseColor.GREEN);
            cell.setFixedHeight(36f);
            cell.setMinimumHeight(36f);
            table.addCell(cell);
            
            table.addCell("No");
            table.addCell("Date");
            table.addCell("Librarian ID");
            table.addCell("Book ID");
            table.addCell("Patron ID");

            while (count < size)
            {
                table.addCell(result.get(count)[0].toString());
                table.addCell(result.get(count)[1].toString());
                table.addCell(result.get(count)[2].toString());
                table.addCell(result.get(count)[3].toString());
                table.addCell(result.get(count)[4].toString());

                count += 1;
            }

            document.add(table);
            
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            document.add(new Paragraph("General Comment : ", FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.NORMAL, BaseColor.DARK_GRAY)));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            document.add(new Paragraph("Administrator Comment : ", FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.NORMAL, BaseColor.DARK_GRAY)));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            document.add(new Paragraph("Signature : ", FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.NORMAL, BaseColor.DARK_GRAY)));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            

            JOptionPane.showMessageDialog(null, "Report Generated!");

            document.close();
            }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    /*
    Generate a Report about book transactions done by a given Patron.
    */
    public static void generatePatronBookTransaction(String patronID)
    {
        try
        {
            String currentDate = Report.getCurrentDate();
            float[] columnWidths = {2, 8, 5, 5, 5, 8};
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Reports/" + currentDate + ".pdf"));
            
            document.open();
            Image image = Image.getInstance("Images/logo.png");
            document.add(image);
            
            Patron patron = conn.returnPatron(patronID);
            
            document.add(new Paragraph(patron.getName(), FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.DARK_GRAY)));
            document.add(new Paragraph("Date : " + new Date().toString(), FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.NORMAL, BaseColor.RED)));
            
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            ArrayList<String[]> result = conn.generatePatronBookTransaction(patronID);

            PdfPTable table = new PdfPTable(columnWidths);
            table.setWidthPercentage(100);

            int size = result.size();
            int count = 0;
            
            PdfPCell cell = new PdfPCell(new Paragraph(patron.getName() + "'s Book Transactions"));
            cell.setColspan(6);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBackgroundColor(BaseColor.GREEN);
            cell.setFixedHeight(36f);
            cell.setMinimumHeight(36f);
            table.addCell(cell);
            
            table.addCell("No");
            table.addCell("Date");
            table.addCell("Librarian ID");
            table.addCell("Book ID");
            table.addCell("Status");
            table.addCell("Return Date");
            

            while (count < size)
            {
                table.addCell(result.get(count)[0].toString());
                table.addCell(result.get(count)[1].toString());
                table.addCell(result.get(count)[2].toString());
                table.addCell(result.get(count)[3].toString());
                table.addCell(result.get(count)[4].toString());
                table.addCell(result.get(count)[5].toString());

                count += 1;
            }

            document.add(table);
            
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            document.add(new Paragraph("General Comment : ", FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.NORMAL, BaseColor.DARK_GRAY)));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            document.add(new Paragraph("Administrator Comment : ", FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.NORMAL, BaseColor.DARK_GRAY)));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            document.add(new Paragraph("Signature : ", FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.NORMAL, BaseColor.DARK_GRAY)));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            

            JOptionPane.showMessageDialog(null, "Report Generated!");

            document.close();
            }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Exceptions.getErrorMessage(e, "Report", new Patron()), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /*
    Generate a Report about book transactions handeled by a given Librarian
    */
    public static void generateLibrarianBookTransaction(String librarianID)
    {
        try
        {
            String currentDate = Report.getCurrentDate();
            float[] columnWidths = {2, 8, 5, 5};
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Reports/" + currentDate + ".pdf"));
            
            document.open();
            Image image = Image.getInstance("Images/logo.png");
            document.add(image);
            
            Librarian librarian = conn.returnLibrarian(librarianID);
            
            document.add(new Paragraph(librarian.getName(), FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.DARK_GRAY)));
            document.add(new Paragraph("Date : " + new Date().toString(), FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.NORMAL, BaseColor.RED)));
            
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            ArrayList<String[]> result = conn.generateLibrarianBookTransaction(librarianID);

            PdfPTable table = new PdfPTable(columnWidths);
            table.setWidthPercentage(100);

            int size = result.size();
            int count = 0;
            
            PdfPCell cell = new PdfPCell(new Paragraph(librarian.getName() + "'s Book Transactions"));
            cell.setColspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBackgroundColor(BaseColor.GREEN);
            cell.setFixedHeight(36f);
            cell.setMinimumHeight(36f);
            table.addCell(cell);
            
            table.addCell("No");
            table.addCell("Date");
            table.addCell("Book ID");
            table.addCell("Patron ID");
            
            

            while (count < size)
            {
                table.addCell(result.get(count)[0].toString());
                table.addCell(result.get(count)[1].toString());
                table.addCell(result.get(count)[2].toString());
                table.addCell(result.get(count)[3].toString());

                count += 1;
            }

            document.add(table);
            
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            document.add(new Paragraph("General Comment : ", FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.NORMAL, BaseColor.DARK_GRAY)));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            document.add(new Paragraph("Administrator Comment : ", FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.NORMAL, BaseColor.DARK_GRAY)));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            document.add(new Paragraph("Signature : ", FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.NORMAL, BaseColor.DARK_GRAY)));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            

            JOptionPane.showMessageDialog(null, "Report Generated!");

            document.close();
            }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Exceptions.getErrorMessage(e, "Report", new Librarian()), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /*
    Generate Reports concerning a Book that includes when and who rented it, and also whether it was returned or not.
    */
    
    public static void generateSpecificBookTransaction(String bookID)
    {
        try
        {
            String currentDate = Report.getCurrentDate();
            float[] columnWidths = {2, 8, 5, 5, 5, 8};
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Reports/" + currentDate + ".pdf"));
            
            document.open();
            Image image = Image.getInstance("Images/logo.png");
            document.add(image);
            
            Book book = conn.returnBook(bookID);
            
            document.add(new Paragraph(book.getTitle() + " by " + book.getAuthor() , FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.DARK_GRAY)));
            document.add(new Paragraph("Date : " + new Date().toString(), FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.NORMAL, BaseColor.RED)));
            
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            ArrayList<String[]> result = conn.generateSpecificBookTransaction(bookID);

            PdfPTable table = new PdfPTable(columnWidths);
            table.setWidthPercentage(100);

            int size = result.size();
            int count = 0;
            
            PdfPCell cell = new PdfPCell(new Paragraph(book.getTitle() + " -  " + book.getAuthor() + " Transactions"));
            cell.setColspan(6);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBackgroundColor(BaseColor.GREEN);
            cell.setFixedHeight(36f);
            cell.setMinimumHeight(36f);
            table.addCell(cell);
            
            table.addCell("No");
            table.addCell("Date");
            table.addCell("Librarian ID");
            table.addCell("Patron ID");
            table.addCell("Status");
            table.addCell("Return Date");
            

            while (count < size)
            {
                table.addCell(result.get(count)[0].toString());
                table.addCell(result.get(count)[1].toString());
                table.addCell(result.get(count)[2].toString());
                table.addCell(result.get(count)[3].toString());
                table.addCell(result.get(count)[4].toString());
                table.addCell(result.get(count)[5].toString());

                count += 1;
            }

            document.add(table);
            
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            document.add(new Paragraph("General Comment : ", FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.NORMAL, BaseColor.DARK_GRAY)));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            document.add(new Paragraph("Administrator Comment : ", FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.NORMAL, BaseColor.DARK_GRAY)));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            document.add(new Paragraph("Signature : ", FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.NORMAL, BaseColor.DARK_GRAY)));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            
            

            JOptionPane.showMessageDialog(null, "Report Generated!");

            document.close();
            }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Exceptions.getErrorMessage(e, "Report", new Book()), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static String getCurrentDate()
    {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        return simpleDateFormat.format(calendar.getTime()).toString();
    }
}
