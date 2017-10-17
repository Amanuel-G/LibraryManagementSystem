package Boundary;

import Controller.JDBC;
import Entity.Book;
import Entity.Librarian;
import Entity.Patron;
import com.itextpdf.text.pdf.fonts.otf.TableHeader;
import static java.lang.Thread.sleep;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author biruk
 */
public class Transaction extends javax.swing.JFrame {

    Librarian librarian;
    String[] bookInfo = new String[9];
    
    Object[] rowTitle = {"Book ID", "ISBN", "Title", "Author", "Publisher", "Edition", "Category", "Course", "Department", "Page Number"};
    
    DefaultTableModel cart;
    
    int i = 0;
    
    JDBC conn = new JDBC();
    
    private ImageIcon picture = null;
    byte[] imageData = null;
    
    public Transaction() {
        initComponents();
        
        JDBC conn = new JDBC();
        librarian = conn.returnLibrarian("123");
        
        populateTable();
        
        cart = (DefaultTableModel) CartTable.getModel();
        cart.setColumnIdentifiers(rowTitle);
        
        for (int i = cart.getRowCount() - 1;i >= 0 ;i--)
        {
            cart.removeRow(i);
        }
        
        displayTimeDate();
        
    }
    
    public Transaction(Librarian librarian)
    {
        initComponents();
        this.librarian = librarian;
        
        populateTable();
        
        cart = (DefaultTableModel) CartTable.getModel();
        cart.setColumnIdentifiers(rowTitle);
        
        for (int i = cart.getRowCount() - 1;i >= 0 ;i--)
        {
            cart.removeRow(i);
        }
        
        displayTimeDate();
    }
    
    public void displayTimeDate()
    {
        Thread clock = new Thread()
        {
                    @Override
                    public void run()
                    {
                        for(;;)
                        {
                            Calendar cal = new GregorianCalendar();
                            int month = cal.get(Calendar.MONTH) + 1;
                            int year = cal.get(Calendar.YEAR);
                            int day = cal.get(Calendar.DAY_OF_MONTH);
        
                            Date.setText("Current Date : " + year + " / " + month + " / " + day);
        
                            int second = cal.get(Calendar.SECOND);
                            int minute = cal.get(Calendar.MINUTE);
                            int hour = cal.get(Calendar.HOUR);
        
                            Time.setText("Current Time : " + hour + " : " + minute + " : " + second);
                            try {
                                sleep(1000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(BookManagment.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                };
                clock.start();
    }
    
    public void populateTable()
    {
        ResultSet rs = librarian.searchBookTitle("");
        BookTable.setModel(DbUtils.resultSetToTableModel(rs));
        
        ResultSet rsUnreturned = conn.getAllUnreturnedBookTransactionRS();
        ReturnTable.setModel(DbUtils.resultSetToTableModel(rsUnreturned));
    }
    
      public void getArrayValues()
    {
        bookInfo[0] = BookID.getText();
        bookInfo[1] = BookISBN.getText();
        bookInfo[2] = BookTitle.getText();
        bookInfo[3] = Author.getText();
        bookInfo[4] = Publisher.getText();
        bookInfo[5] = Edition.getText();
        bookInfo[6] = Category.getText();
        bookInfo[7] = Course.getText();
        bookInfo[8] = Department.getText();
    }
     
      
    public boolean rowDuplicated(String bookID, DefaultTableModel cart)
    {
        try
        {
            for (int i = cart.getRowCount() - 1;i >= 0 ;i--)
            {
                if (bookID.equals(CartTable.getModel().getValueAt(i, 0)))
                    return true;
            }
            
            return false;
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
    public void loadImage(String bookID)
    {
        byte[] bookCover = conn.getBookCover(bookID);
        
        if (bookCover != null)
        {
            picture = new ImageIcon(bookCover);
                    
            BookCover.setIcon(picture);
            BookCover.setText("");
        }
        
        else
        {
            BookCover.setIcon(null);
            BookCover.setText("No Image Found!");
            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jToolBar1 = new javax.swing.JToolBar();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        BookTable = new javax.swing.JTable();
        SearchTerm = new javax.swing.JTextField();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        BookID = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        BookISBN = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        BookTitle = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        Author = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        Publisher = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        Edition = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        Category = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        Course = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        Department = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        Number_of_page = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        BookCover = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Description = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        CartTable = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        PatronID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ReturnBookID = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        ReturnTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem3 = new javax.swing.JCheckBoxMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jCheckBoxMenuItem4 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem5 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem6 = new javax.swing.JCheckBoxMenuItem();
        Date = new javax.swing.JMenu();
        Time = new javax.swing.JMenu();

        jMenu4.setText("jMenu4");

        jMenu5.setText("jMenu5");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1330, 700));
        setMinimumSize(new java.awt.Dimension(1330, 700));
        setPreferredSize(new java.awt.Dimension(1330, 700));

        jToolBar1.setRollover(true);

        jButton8.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Boundary/Apps-Home-icon.png"))); // NOI18N
        jButton8.setText("Home");
        jButton8.setFocusable(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton8);

        jButton10.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Boundary/Backup-iconSmall.png"))); // NOI18N
        jButton10.setText("Backup");
        jButton10.setFocusable(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton10);

        jButton11.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Boundary/settings-iconSmall.png"))); // NOI18N
        jButton11.setText("General Setting");
        jButton11.setFocusable(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton11);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Commands", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Abyssinica SIL", 1, 18))); // NOI18N

        jButton2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Boundary/add-1-icon.png"))); // NOI18N
        jButton2.setText("Add To Cart");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Boundary/Close-2-icon.png"))); // NOI18N
        jButton3.setText("Remove From Cart");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Boundary/Google-Play-Books-icon.png"))); // NOI18N
        jButton4.setText("Rent Books");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Book", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Abyssinica SIL", 1, 18))); // NOI18N

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Boundary/Search-icon.png"))); // NOI18N

        BookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        BookTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BookTableMouseClicked(evt);
            }
        });
        BookTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BookTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(BookTable);

        SearchTerm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchTermKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(SearchTerm)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SearchTerm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTabbedPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel8.setText("Book ID");

        BookID.setEditable(false);

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel9.setText("Book ISBN");

        BookISBN.setEditable(false);

        jLabel10.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel10.setText("Title");

        BookTitle.setEditable(false);
        BookTitle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BookTitleKeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel11.setText("Author");

        Author.setEditable(false);
        Author.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AuthorKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel12.setText("Publisher");

        Publisher.setEditable(false);
        Publisher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PublisherKeyTyped(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel13.setText("Edition");

        Edition.setEditable(false);

        jLabel14.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel14.setText("Category");

        Category.setEditable(false);

        jLabel15.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel15.setText("Course");

        Course.setEditable(false);

        jLabel16.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel16.setText("Department");

        Department.setEditable(false);
        Department.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                DepartmentKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("Page Number");

        Number_of_page.setEditable(false);
        Number_of_page.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Number_of_pageKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Number_of_page, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                    .addComponent(Department)
                    .addComponent(Course)
                    .addComponent(BookISBN, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                    .addComponent(BookID, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                    .addComponent(BookTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                    .addComponent(Author, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                    .addComponent(Publisher, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                    .addComponent(Edition, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                    .addComponent(Category, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BookID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BookISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BookTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Author, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Publisher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Edition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Course, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Department, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Number_of_page, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Book Information", jPanel5);

        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Book Cover", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Abyssinica SIL", 1, 14))); // NOI18N

        BookCover.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BookCover.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BookCover.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BookCover.setInheritsPopupMenu(false);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BookCover, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BookCover, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Book Cover", jPanel6);

        jLabel17.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel17.setText("Book Description");

        Description.setEditable(false);
        Description.setColumns(20);
        Description.setRows(5);
        jScrollPane3.setViewportView(Description);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane5.addTab("Description", jPanel7);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cart", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Abyssinica SIL", 1, 18))); // NOI18N

        CartTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(CartTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(59, 59, 59)))
                .addComponent(jTabbedPane5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane5)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(24, 24, 24)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Rent Books", jPanel4);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setText("Patron ID");

        PatronID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PatronIDKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setText("Book ID");

        ReturnBookID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ReturnBookIDKeyReleased(evt);
            }
        });

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jButton5.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Boundary/Ecommerce-Return-Purchase-icon.png"))); // NOI18N
        jButton5.setText("Return Book");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Boundary/report-icon.png"))); // NOI18N
        jButton6.setText("Generate Report");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PatronID)
                    .addComponent(ReturnBookID, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PatronID)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ReturnBookID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Unreturned Books", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Abyssinica SIL", 1, 18))); // NOI18N

        ReturnTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(ReturnTable);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Return Books", jPanel8);

        jMenu1.setForeground(new java.awt.Color(28, 98, 166));
        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        jCheckBoxMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK));
        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("New Window");
        jCheckBoxMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Boundary/Files-New-File-icon.png"))); // NOI18N
        jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jCheckBoxMenuItem1);

        jCheckBoxMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK));
        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setText("Logout");
        jCheckBoxMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Boundary/logout-icon.png"))); // NOI18N
        jCheckBoxMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jCheckBoxMenuItem2);

        jCheckBoxMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.ALT_MASK));
        jCheckBoxMenuItem3.setSelected(true);
        jCheckBoxMenuItem3.setText("Exit");
        jCheckBoxMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Boundary/Close-2-icon_menu.png"))); // NOI18N
        jCheckBoxMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jCheckBoxMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setForeground(new java.awt.Color(28, 98, 166));
        jMenu2.setText("Edit");
        jMenu2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        jCheckBoxMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        jCheckBoxMenuItem4.setSelected(true);
        jCheckBoxMenuItem4.setText("Add to Chart");
        jCheckBoxMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Boundary/add-1-icon_menu.png"))); // NOI18N
        jCheckBoxMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jCheckBoxMenuItem4);

        jCheckBoxMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        jCheckBoxMenuItem5.setSelected(true);
        jCheckBoxMenuItem5.setText("Remove from Chart");
        jCheckBoxMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Boundary/Close-2-icon_menu.png"))); // NOI18N
        jCheckBoxMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jCheckBoxMenuItem5);

        jCheckBoxMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        jCheckBoxMenuItem6.setSelected(true);
        jCheckBoxMenuItem6.setText("Rent Book");
        jCheckBoxMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Boundary/Google-Play-Books-icon_menu.png"))); // NOI18N
        jCheckBoxMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jCheckBoxMenuItem6);

        jMenuBar1.add(jMenu2);

        Date.setForeground(new java.awt.Color(28, 98, 166));
        Date.setText("Date : ");
        Date.setEnabled(false);
        Date.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jMenuBar1.add(Date);

        Time.setForeground(new java.awt.Color(28, 98, 166));
        Time.setText("Time : ");
        Time.setEnabled(false);
        Time.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jMenuBar1.add(Time);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BookTitleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BookTitleKeyTyped

    }//GEN-LAST:event_BookTitleKeyTyped

    private void AuthorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AuthorKeyTyped

    }//GEN-LAST:event_AuthorKeyTyped

    private void PublisherKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PublisherKeyTyped

    }//GEN-LAST:event_PublisherKeyTyped

    private void DepartmentKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DepartmentKeyTyped

    }//GEN-LAST:event_DepartmentKeyTyped

    private void Number_of_pageKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Number_of_pageKeyTyped

    }//GEN-LAST:event_Number_of_pageKeyTyped

    private void SearchTermKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchTermKeyReleased
        try
        {
            ResultSet rs = librarian.searchBookTitle(SearchTerm.getText());
            BookTable.setModel(DbUtils.resultSetToTableModel(rs));
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_SearchTermKeyReleased

    private void BookTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BookTableKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_BookTableKeyReleased

    private void BookTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BookTableMouseClicked
        try
        {
            int row = BookTable.getSelectedRow();
            
            if (row == -1)
            {
                
            }
            
            else
            {
                String ID = (BookTable.getModel().getValueAt(row, 0)).toString();  

                JDBC conn = new JDBC();
                Book book = conn.returnBook(ID);

                BookID.setText(book.getBookID());
                BookISBN.setText(book.getISBN());
                BookTitle.setText(book.getTitle());
                Author.setText(book.getAuthor());
                Publisher.setText(book.getPublisher());
                Edition.setText(book.getEdition());
                Category.setText(book.getCategory());
                Course.setText(book.getCourse());
                Department.setText(book.getDepartment());
                Number_of_page.setText(String.valueOf(book.getNoOfPage()));

                Description.setText(book.getDescription());

                loadImage(book.getBookID());
            }
 
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);  
        }
    }//GEN-LAST:event_BookTableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try
        {
            
            getArrayValues();
            
            if (Tools.Tools.isSelected(bookInfo))
            {
                String ID = BookID.getText();
                
                JDBC conn = new JDBC();
                Book book = conn.returnBook(ID);
                
                String[] data = new String[10];
                
                data[0] = book.getBookID();
                data[1] = book.getISBN();
                data[2] = book.getTitle();
                data[3] = book.getAuthor();
                data[4] = book.getPublisher();
                data[5] = book.getEdition();
                data[6] = book.getCategory();
                data[7] = book.getCourse();
                data[8] = book.getDepartment();
                data[9] = String.valueOf(book.getNoOfPage());
                
                if (!rowDuplicated(book.getBookID(), cart))
                {
                    cart.addRow(data);
                }
                
                else
                {
                    JOptionPane.showMessageDialog(null, "Warning : The book you selected is already found in your Cart", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                
                
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);  
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try
        {
            JDBC conn = new JDBC();
            
            ArrayList<Book> booksInCart = new ArrayList<>();
            
            for (int i = 0; i < cart.getRowCount() ;i++)
            {
                String bookID = (String) CartTable.getModel().getValueAt(i, 0); 
                Book book = conn.returnBook(bookID);

                if (book.isAvailable())
                {
                    booksInCart.add(book);
                }   
                else
                {
                    continue;
                }
                                
            }
            
            
            
            
            Rent rentWindow = new Rent(librarian, booksInCart);
           
            rentWindow.setVisible(true);
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);  
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void PatronIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PatronIDKeyReleased
        try
        {
            if (!(PatronID.getText().equals("") && ReturnBookID.getText().equals("")))
            {
                ResultSet result = conn.getAllUnreturnedBookTransactionRS(ReturnBookID.getText(), PatronID.getText());
                ResultSet rsUnreturned = conn.getAllUnreturnedBookTransactionRS();
                
                if (result.isBeforeFirst())
                {
                    ReturnTable.setModel(DbUtils.resultSetToTableModel(result));
                }
                
                else
                {
                    ReturnTable.setModel(DbUtils.resultSetToTableModel(rsUnreturned));
                }
            }
            
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);  
        }
        
    }//GEN-LAST:event_PatronIDKeyReleased

    private void ReturnBookIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ReturnBookIDKeyReleased
        try
        {
            if (!(PatronID.getText().equals("") && ReturnBookID.getText().equals("")))
            {
                ResultSet result = conn.getAllUnreturnedBookTransactionRS(ReturnBookID.getText(), PatronID.getText());
                ResultSet rsUnreturned = conn.getAllUnreturnedBookTransactionRS();
                
                if (result.isBeforeFirst())
                {
                    ReturnTable.setModel(DbUtils.resultSetToTableModel(result));
                }
                
                else
                {
                    ReturnTable.setModel(DbUtils.resultSetToTableModel(rsUnreturned));
                }
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);  
        }
    }//GEN-LAST:event_ReturnBookIDKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try
        {
            if (!(PatronID.getText().equals("") && ReturnBookID.getText().equals("")))
            {
                Book book = conn.returnBook(ReturnBookID.getText());
                Patron patron = conn.returnPatron(PatronID.getText());
                
                librarian.returnBook(book, patron);
            }
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);  
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try
        {
            int row = CartTable.getSelectedRow();
            cart.removeRow(row);
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);  
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jCheckBoxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ActionPerformed
        Transaction newWIndow = new Transaction(librarian);
        newWIndow.setVisible(true);
    }//GEN-LAST:event_jCheckBoxMenuItem1ActionPerformed

    private void jCheckBoxMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem4ActionPerformed
        try
        {
            
            getArrayValues();
            
            if (Tools.Tools.isSelected(bookInfo))
            {
                String ID = BookID.getText();
                
                JDBC conn = new JDBC();
                Book book = conn.returnBook(ID);
                
                String[] data = new String[10];
                
                data[0] = book.getBookID();
                data[1] = book.getISBN();
                data[2] = book.getTitle();
                data[3] = book.getAuthor();
                data[4] = book.getPublisher();
                data[5] = book.getEdition();
                data[6] = book.getCategory();
                data[7] = book.getCourse();
                data[8] = book.getDepartment();
                data[9] = String.valueOf(book.getNoOfPage());
                
                if (!rowDuplicated(book.getBookID(), cart))
                {
                    cart.addRow(data);
                }
                
                else
                {
                    JOptionPane.showMessageDialog(null, "Warning : The book you selected is already found in your Cart", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                
                
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);  
        }
    }//GEN-LAST:event_jCheckBoxMenuItem4ActionPerformed

    private void jCheckBoxMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem5ActionPerformed
        try
        {
            int row = CartTable.getSelectedRow();
            cart.removeRow(row);
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);  
        }
    }//GEN-LAST:event_jCheckBoxMenuItem5ActionPerformed

    private void jCheckBoxMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem6ActionPerformed
        try
        {
            JDBC conn = new JDBC();
            
            ArrayList<Book> booksInCart = new ArrayList<>();
            
            for (int i = 0; i < cart.getRowCount() ;i++)
            {
                String bookID = (String) CartTable.getModel().getValueAt(i, 0); 
                Book book = conn.returnBook(bookID);

                if (book.isAvailable())
                {
                    booksInCart.add(book);
                }   
                else
                {
                    continue;
                }
                                
            }
            
            
            
            
            Rent rentWindow = new Rent(librarian, booksInCart);
           
            rentWindow.setVisible(true);
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);  
        }
    }//GEN-LAST:event_jCheckBoxMenuItem6ActionPerformed

    private void jCheckBoxMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem2ActionPerformed
        Login login = new Login();
        login.setVisible(true);
        dispose();
    }//GEN-LAST:event_jCheckBoxMenuItem2ActionPerformed

    private void jCheckBoxMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem3ActionPerformed
        dispose();
    }//GEN-LAST:event_jCheckBoxMenuItem3ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        BackupRestore backupRestore = new BackupRestore();
        backupRestore.setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        LibrarianSetting librarianSetting = new LibrarianSetting(librarian);
        librarianSetting.setVisible(true);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        LibrarianHomePage home = new LibrarianHomePage(librarian);
        home.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaction().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Author;
    private javax.swing.JLabel BookCover;
    private javax.swing.JTextField BookID;
    private javax.swing.JTextField BookISBN;
    private javax.swing.JTable BookTable;
    private javax.swing.JTextField BookTitle;
    private javax.swing.JTable CartTable;
    private javax.swing.JTextField Category;
    private javax.swing.JTextField Course;
    private javax.swing.JMenu Date;
    private javax.swing.JTextField Department;
    private javax.swing.JTextArea Description;
    private javax.swing.JTextField Edition;
    private javax.swing.JTextField Number_of_page;
    private javax.swing.JTextField PatronID;
    private javax.swing.JTextField Publisher;
    private javax.swing.JTextField ReturnBookID;
    private javax.swing.JTable ReturnTable;
    private javax.swing.JTextField SearchTerm;
    private javax.swing.JMenu Time;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem3;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem4;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem5;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
