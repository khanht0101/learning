/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zbse20;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JTextArea;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
/**
 *
 * @author Khanh
 */
public class ReadExcelFile 
{
    protected String uiPath ="";
    public static JTextArea uiConsole;
    public ReadExcelFile() 
    {
        uiPath =  System.getProperty("user.dir");
    }
    
    public List<List> ReadFile(String inputName, String sheetName)
    {
        
    String filename= uiPath + "/"+inputName +".xls";
    HSSFWorkbook hwb = null;  
    POIFSFileSystem fs = null;  
    HSSFSheet sheet = null; 
    List<List> rowlist = new ArrayList<List>();
    try 
    {  
        fs = new POIFSFileSystem(new FileInputStream(filename));  
        hwb = new HSSFWorkbook(fs);  
        sheet = hwb.getSheet(sheetName);  
        int rows = sheet.getPhysicalNumberOfRows(); 
        
        for (int i = 0; i < rows; i++) 
        {  
            List<String> cellList = new  ArrayList<String>();
            HSSFRow row = sheet.getRow((short) i); 
            boolean rear = true;
            int y = 0;
            while(rear)
            {
                if(row.getCell(y) == null || y>10)
                {
                    rear = false;
                }
                else
                {
                    cellList.add(row.getCell(y).toString());
                    y++;
                }                
            }
            rowlist.add(cellList);
        } 
    }
    catch (Exception ex)
    {
        uiConsole.append(ex.getMessage()+"\n");
    }
      return rowlist;
    } 
    public void UpdateStatusFile(List<List> list, String inputName, String sheetName)
    {
        
    String filename= uiPath + "/"+inputName +".xls";
    HSSFWorkbook hwb = null;  
    POIFSFileSystem fs = null;  
    HSSFSheet sheet = null; 
    try 
    {  
        fs = new POIFSFileSystem(new FileInputStream(filename));  
        hwb = new HSSFWorkbook(fs);  
        sheet = hwb.getSheet(sheetName);  
        int rows = sheet.getPhysicalNumberOfRows(); 
        
        for (int i = 0; i < rows; i++) 
        {              
            HSSFRow row = sheet.getRow((short) i); 
            for(List elem: list)
            {
                if(row.getCell(5).toString().equals(elem.get(1).toString()))
                {
                    row.getCell(4).setCellValue(new HSSFRichTextString(elem.get(0).toString()));
                }
            }
        } 
        FileOutputStream fileOut =  new FileOutputStream(filename);    
        hwb.write(fileOut);
        fileOut.close();
    }
    catch (Exception ex)
    {
        uiConsole.append(ex.getMessage()+"\n");
    }
      
    } 
}
