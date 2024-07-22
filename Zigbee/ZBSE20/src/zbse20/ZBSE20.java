/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zbse20;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *Ha Duong: 0902614388
 * @author CONG HUY
 */
public class ZBSE20 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
            NTSMain frm = new NTSMain();
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
                    // Determine the new location of the window
                    int w = frm.getSize().width;
                    int h = frm.getSize().height;
                    int x = (dim.width-w)/2;
                    int y = (dim.height-h)/2;        
                    // Move the window
                    frm.setLocation(x, y);
                    frm.show(); 
        
//      //        }     
    }
}
