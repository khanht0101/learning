/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zbse20;

import javax.swing.JTable;

/**
 *
 * @author CONG HUY
 */
public class ThreadRunTestCase extends Thread {
    public  static JTable table;
    public NTSMain mainfrm;
    public int index;
   ThreadRunTestCase(NTSMain frm, int row) 
   {
       mainfrm = frm;
       index = row;
   }

   // This is the entry point for the second thread.
   public void run() {
      try {
         
            Thread.sleep(5000);  
            mainfrm.ExcutiveTestCase(index);
            mainfrm.RunTestCase(index+1);
      } catch (InterruptedException e) {
        
      }    
   }
}