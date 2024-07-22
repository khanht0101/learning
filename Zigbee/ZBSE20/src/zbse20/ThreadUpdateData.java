/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zbse20;

import com.server.IRMIUserService;
import javax.swing.JTextArea;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import static zbse20.ThreadConnectNetwork.uiConsole;

/**
 *
 * @author Khanh
 */
public class ThreadUpdateData extends Thread
{
    public NTSMain form;
    public ThreadUpdateData(NTSMain frm)
    {
       form = frm;
    }
     public void run() {
      try {
         
            Thread.sleep(2000); 
            form.UpdateDataForUser();            
      } catch (InterruptedException e) {
        
      }    
   }    
}

