/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;


/**
 *
 * @author Khanh
 */
public class ImageComponent extends JPanel {
 
      private Image img;
 
 
      public ImageComponent(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
      }
 
      public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
      }
}

