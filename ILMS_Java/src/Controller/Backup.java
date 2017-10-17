/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.swing.JOptionPane;

/**
 *
 * @author Helina
 */
public class Backup {
    
    public static void backupDatabase(String filepath, String filename)
    {
        Process p = null;
            try {
                Runtime runtime = Runtime.getRuntime();
                p = runtime.exec("mysqldump -uroot -p  --add-drop-database -B ilms -r C:/Users/AbeloMan/asasasasasas.sql");
                int processComplete = p.waitFor();

                if (processComplete == 0) {

                    JOptionPane.showMessageDialog(null, "Backup created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(null, "Could not create the backup", "Error", JOptionPane.ERROR_MESSAGE);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
    public static void restoreDatabase(String username, String password, String filepath)
    {
        String databaseName = "PRACTICE";
        String[] restoreCmd = new String[]{"mysql", databaseName, "- -user=root", "--password=B@rcelon@10", "-e", "source /home/biruk/Downloads/PRACTICE.sql"  };

        Process runtimeProcess;
        try {

            runtimeProcess = Runtime.getRuntime().exec(restoreCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                JOptionPane.showMessageDialog(null, "Restored successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                
                JOptionPane.showMessageDialog(null, "Could not restore the backup!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    
}
