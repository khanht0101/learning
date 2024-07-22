/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zbse20;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CONG HUY
 */
public class ObjectClasses 
{
    private DeviceClass deviceClass = new DeviceClass();
    private TestCaseClass testCase = new TestCaseClass();
    public ObjectClasses()
    {}
    public void ShowAllDevice(JComboBox combo, JTextArea uiTxtArea)
    {
        try {
            List<List> rs = deviceClass.getAllDevice();
            if(rs !=null || rs.size()>1)
                for(int i = 1; i< rs.size(); i++)
                {
                    String device = rs.get(i).get(1).toString();
                    combo.addItem(device);
                    uiTxtArea.append("Added to " +device +"\n");
                } 
            }
        catch (Exception ex)
        {
            uiTxtArea.append(ex.getMessage()+"\n");
        }
    }
    
    
    
    
    public void ShowFunctionSet(DefaultTableModel tableModel, JTextArea uiTxtArea)
    {     
       List<List> rs = deviceClass.getAllFunction();
       
        if(rs != null && rs.size()>1)
       {
           for(int i=1;i< rs.size(); i++)
           {
               String function = rs.get(i).get(1).toString();
               String id = rs.get(i).get(0).toString();
               if(id.indexOf(".")>0)
               {
                   id = id.substring(0, id.indexOf("."));
               }
               tableModel.insertRow(i-1, new Object[]{id,function,false,false});
               uiTxtArea.append("Added to "+function+"\n");
           }           
       }
    }
    
     
     public ArrayList<String> ChangeFunctionSetSelected(JTable table, int DeviceID)
     {
         ArrayList<String> arrayList = new ArrayList<String>();         
         if(DeviceID != 0)
             arrayList =  DefaultSelectFunctionSet(DeviceID);         
         UpdateDefaultFunctionSet(table,arrayList);
        return arrayList;
     }
     
     public void UpdateDefaultFunctionSet(JTable table, ArrayList<String> arrayList)
     {
         for(int i =0; i<table.getModel().getRowCount(); i++)
         {
             table.getModel().setValueAt(false, i, 2);
             table.getModel().setValueAt(false, i, 3);
         }
         if(arrayList.size()>0)
         ResetSelectedFunctionSet(table,arrayList);
     }
     
     public void ResetSelectedFunctionSet(JTable table, ArrayList<String> arrayList)
     {
          for(int i =0; i<table.getModel().getRowCount(); i++)
         {
             for(int x =0; x < arrayList.size(); x++)
             {
               String  elem = arrayList.get(x);
               if(table.getModel().getValueAt(i,0).toString().equals(elem.substring(0,elem.indexOf("-"))))
                {
                 String  site = elem.substring(elem.indexOf("-")+1);
                    if(site.equals("2"))
                    {
                        table.getModel().setValueAt(true, i, 2);
                        table.getModel().setValueAt(true, i, 3);
                    }
                    else if(site.equals("1"))
                        table.getModel().setValueAt(true, i, 2);
                    else 
                        table.getModel().setValueAt(true, i, 3);
                }
             }
         }
     }
     
     public ArrayList<String> DefaultSelectFunctionSet(int DeviceID)
     {
         List<List> rs = deviceClass.getSelectedFunctionSet(DeviceID+"");
         ArrayList<String> tempList = new ArrayList<String>();
         if(rs !=null && rs.size()>0)
         {
             for(int i = 0; i< rs.size(); i++)
             {
                    String funct = rs.get(i).get(0).toString();
                    String server = rs.get(i).get(2).toString();
                    String client = rs.get(i).get(1).toString();
                    if(!server.equals("")&& !client.equals(""))
                    {
                        tempList.add(funct + "-2");
                    }
                    else if(!server.equals(""))
                        tempList.add(funct + "-1");
                    else 
                        tempList.add(funct + "-0");
             }
         }
         return tempList;
     }
     
     public List<List> FilterTestCase(List<List> rs, FunctionSet  func)
     {
         List<List> rowList = new ArrayList<List>();
         for(int i = 1; i< rs.size(); i++)
         {
             String funName = rs.get(i).get(0).toString();
             if(funName.toLowerCase().equals(func.functionsetName.toLowerCase()))
             {
                 if(rs.get(i).get(1).toString().toLowerCase().equals(func.server.toLowerCase()))
                 {
                     rowList.add(rs.get(i));
                 }
             }
         }
         return rowList;
     }
     public void AddNewTestCase( DefaultTableModel tableModel, ArrayList<FunctionSet>  funcList)
     {
         List<List> rs = testCase.getTestCase();
        for(int i =0 ; i<funcList.size(); i++)
        {
           List<List> nextList = FilterTestCase(rs, funcList.get(i));
            if(nextList != null && nextList.size()>0)
            {
                for(int y = 0; y < nextList.size(); y++)
                {                 
                    List cellList = nextList.get(y);
                    String status = cellList.get(4).toString(); // passed
                    status = status.equals("P") ? "Passed" : status.equals("F")? "Failed" : "New" ;
                    tableModel.insertRow(tableModel.getRowCount(), new Object[]{cellList.get(5).toString(), // filename
                                                                                cellList.get(6).toString(), // Spec
                                                                                true,
                                                                                status,
                                                                                cellList.get(2).toString(),// filename
                                                                                cellList.get(3).toString(), // required
                                                                                cellList.get(7).toString()});// Description
                }
            } 
            
        }
     }   
     
     public void UpdateTestCases(List<List> list)
     {
         testCase.UpdateTestCase(list);
     }
}
