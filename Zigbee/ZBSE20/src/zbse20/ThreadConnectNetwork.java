/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zbse20;

import com.server.IRMIUserService;
import javax.swing.JTextArea;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author CONG HUY
 */
public class ThreadConnectNetwork extends Thread {
    
   public static JTextArea uiConsole;
    public ThreadConnectNetwork(JTextArea area)
    {
        uiConsole = area;
    }
     public void run() {
      try {
         
            Thread.sleep(500); 
            
            try
            {
            //    FileSystemXmlApplicationContext xml= new FileSystemXmlApplicationContext("D:/rmiServerAppContext.xml"); 
//                //ClassPathXmlApplicationContext xml= new ClassPathXmlApplicationContext();  
//                //xml.setConfigLocation("rmiServerAppContext.xml");                
//                //xml.refresh();
                
                String path = System.getProperty("user.dir");
                FileSystemXmlApplicationContext context= new FileSystemXmlApplicationContext(path + "/rmiServerAppContext.xml"); 
           
                IRMIUserService rmiClient = (IRMIUserService) context.getBean("RMIUserService");               
                uiConsole.append("Coordinator has established the network.\n");
                uiConsole.append("Waiting the Client join the network...\n");
            }
            catch (BeansException | IllegalStateException ex)
            {
               uiConsole.append("Error: "+ ex +"\n");
            }       
      } catch (InterruptedException e) {
        
      }    
   }
    
}
