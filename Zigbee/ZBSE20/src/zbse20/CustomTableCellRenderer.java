/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zbse20;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author CONG HUY
 */
 public class CustomTableCellRenderer extends DefaultTableCellRenderer{
  public Component getTableCellRendererComponent (JTable table, 
Object obj, boolean isSelected, boolean hasFocus, int row, int column) {
  Component cell = super.getTableCellRendererComponent(
   table, obj, isSelected, hasFocus, row, column);
  
  if (isSelected) {
  cell.setBackground(Color.green);
  } 
  else {
  if (row % 2 == 0) {
  cell.setBackground(Color.cyan);
  }
  else {
  cell.setBackground(Color.lightGray);
  
  }
  }
  return cell;
  }
  }
