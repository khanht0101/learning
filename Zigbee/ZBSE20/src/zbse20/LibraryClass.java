/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zbse20;

import java.awt.Desktop;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableColumn;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import javax.swing.JTextPane;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 *
 * @author CONG HUY
 */
public class LibraryClass 
{
    
 public static JTextArea txtArea;   
 String   uiPath =  System.getProperty("user.dir");
 ObjectClasses object = new ObjectClasses();
 public LibraryClass()
 {
 }
 public LibraryClass(JTextArea area)
 {
     txtArea = area;
 }
 public void SetColumnTableWidth(TableColumn column,int width)
 {
     column.setMinWidth(width);
     column.setMaxWidth(width);
     column.setWidth(width);     
     column.setPreferredWidth(width);     
 }
 public void HideColumnTable(TableColumn colum)
 {
     SetColumnTableWidth(colum, 0);
 }
 
 public void SetTableFunctionSetLayout(JTable table)
 {
     HideColumnTable(table.getColumnModel().getColumn(0));
     SetColumnTableWidth(table.getColumnModel().getColumn(2),40);
     SetColumnTableWidth(table.getColumnModel().getColumn(3),40);
 }
 
 public void SetTableTestSuitedLayout(JTable table)
 {
     HideColumnTable(table.getColumnModel().getColumn(0));
     SetColumnTableWidth(table.getColumnModel().getColumn(1),40);
 }
 
  public void SetTableTestCaseLayout(JTable table)
 {
     HideColumnTable(table.getColumnModel().getColumn(0));
     HideColumnTable(table.getColumnModel().getColumn(1));
     SetColumnTableWidth(table.getColumnModel().getColumn(2),55);
     SetColumnTableWidth(table.getColumnModel().getColumn(3),55);
     SetColumnTableWidth(table.getColumnModel().getColumn(4),200);
     SetColumnTableWidth(table.getColumnModel().getColumn(5),80);
 }
  
  public void OpenDocumentFile(String fileName)
  {
       try {
      Desktop desktop = null;
      if (Desktop.isDesktopSupported()) {
        desktop = Desktop.getDesktop();
      }

       desktop.open(new File(fileName));
    } catch (IOException ioe) {
    
    }
  }
  public void OpenFile(String fileName)
  {
       try {
      Desktop desktop = null;
      if (Desktop.isDesktopSupported()) {
        desktop = Desktop.getDesktop();
      }

       desktop.open(new File(uiPath+"//"+fileName));
    } catch (IOException ioe) {
    
    }
  }
  
 public void CreateExcelFile( ArrayList<String[]> arraylist)
 {
    try{        
        String filename=uiPath+"//testcases.xls" ;
        HSSFWorkbook hwb=new HSSFWorkbook();
        HSSFSheet sheet =  hwb.createSheet("testcase");
        
        HSSFRow row=   sheet.createRow((short)0);        
        row.createCell((short) 0).setCellValue("SuiteID");
        row.createCell((short) 1).setCellValue("suite");
        row.createCell((short) 2).setCellValue("testname");
        row.createCell((short) 3).setCellValue("required");
        row.createCell((short) 4).setCellValue("passed");
        row.createCell((short) 5).setCellValue("failed");        
        row.createCell((short) 6).setCellValue("filename");             
        row.createCell((short) 7).setCellValue("spec");             
        row.createCell((short) 8).setCellValue("description");
        for(int i=0;i<arraylist.size();i++)
        {
            String[] elem = arraylist.get(i);
            HSSFRow newrow =   sheet.createRow((short)i+1);        
            newrow.createCell((short) 0).setCellValue(Integer.parseInt(elem[0]));
            newrow.createCell((short) 1).setCellValue(elem[1]);
            newrow.createCell((short) 2).setCellValue(elem[2]);
            newrow.createCell((short) 3).setCellValue(elem[3]);
            newrow.createCell((short) 4).setCellValue(0);
            newrow.createCell((short) 5).setCellValue(0);        
            newrow.createCell((short) 6).setCellValue(elem[4]);             
            newrow.createCell((short) 7).setCellValue(elem[5]);             
            newrow.createCell((short) 8).setCellValue(elem[6]);
        }

        FileOutputStream fileOut =  new FileOutputStream(filename);
        hwb.write(fileOut);
        fileOut.close();

        } catch (Exception ex ) {
                txtArea.append(ex.getMessage()+"\n");
        }    
 }
 
 public void SaveFile(JTextPane uiTxtArea, String logfile)
 {
    FileWriter  writer = null; 
    BufferedWriter bwriter = null;
    try {
    writer = new FileWriter(uiPath + "//"+logfile);
    bwriter  = new BufferedWriter(writer);   
    String[] content = uiTxtArea.getText().split("\n");
    for(int i = 0;i<content.length; i++)
    {
        bwriter.write(content[i]);
        bwriter.newLine();
    }    
    bwriter.close();
    } catch (Exception e) {
    }
 }
 public void SaveFile(JTextArea uiTxtArea, String logfile)
 {
    FileWriter  writer = null; 
    BufferedWriter bwriter = null;
    try {
    writer = new FileWriter(uiPath + "//"+logfile);
    bwriter  = new BufferedWriter(writer);   
    String[] content = uiTxtArea.getText().split("\n");
    for(int i = 0;i<content.length; i++)
    {
        bwriter.write(content[i]);
        bwriter.newLine();
    }    
    bwriter.close();
    } catch (Exception e) {
    }
 }
 
 public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return  date.toString();//dateFormat.format(date);
    }
 
 
 public Testcase ReadScriptlFile(String fileName)
 {
     Testcase testcase = new Testcase(); 
     BufferedReader br = null;  
     try
     {
            String sCurrentLine;
            String testDesc ="";
            br = new BufferedReader(new FileReader(uiPath + "/Scripts/"+fileName));
            while ((sCurrentLine = br.readLine()) != null) 
            {    
                if(sCurrentLine.indexOf("caseDesc")>0)
                {
                 testDesc = sCurrentLine;
                 break;
                }
            }
            testDesc = testDesc.substring(testDesc.indexOf("\"")+1);
            testDesc = testDesc.substring(0, testDesc.indexOf("\""));
            String[] header =  testDesc.split(",");
            testcase.functionset = header[0].trim();
            testcase.server = header[3].trim().equals("S")?"T":"F";
            testcase.required = header[2].trim();
            testcase.name = header[1].trim();
            testcase.description = header[5].trim();
            testcase.fileName = fileName;
          
     } catch (Exception ex) 
        {
            testcase = null;
        } finally 
        {
            try 
            {
                if (br != null)br.close();
            } catch (Exception e) 
            {
                testcase = null;
            }
        }
     return testcase;
 } 
 
 public void ReLoadTestCase()
 {     
    String filename= uiPath + "/testcases.xls" ;
    HSSFWorkbook hwb = null;  
    POIFSFileSystem fs = null;  
    HSSFSheet sheet = null;  
    try 
    {  
        fs = new POIFSFileSystem(new FileInputStream(filename));  
        hwb = new HSSFWorkbook(fs);  
        sheet = hwb.getSheetAt(0);  
        int rows = sheet.getPhysicalNumberOfRows();             
        for (int i = 0; i < rows; i++) 
        {  
            if(i>0)
            {              
               HSSFRow row = sheet.getRow((short) i); 
               sheet.removeRow(row);
            }             
    }       
    File folder = new File(uiPath + "/Scripts");
    int i = 1;
    for (File fileEntry : folder.listFiles()) {
        {             
            Testcase testcase = ReadScriptlFile(fileEntry.getName());
            if(testcase != null )
            {
                HSSFRow row=   sheet.createRow((short)i);
                String specFile = testcase.fileName.toLowerCase().replace(".xml", ".doc");
                row.createCell((short) 0).setCellValue(testcase.functionset);
                row.createCell((short) 1).setCellValue(testcase.server);
                row.createCell((short) 2).setCellValue(testcase.name);
                row.createCell((short) 3).setCellValue(testcase.required);
                row.createCell((short) 4).setCellValue(testcase.status);
                row.createCell((short) 5).setCellValue(testcase.fileName);
                row.createCell((short) 6).setCellValue(specFile);
                row.createCell((short) 7).setCellValue(testcase.description);
                i++;
            }
        }
    } 
    FileOutputStream fileOut =  new FileOutputStream(filename);
    hwb.write(fileOut);
    fileOut.close();
    } catch ( Exception ex ) {
        txtArea.append(ex.getMessage() +"\n");

    }    
 }
 public ConfigureFile LoadConfigFile(String fileName)
 {
     ConfigureFile config = new ConfigureFile();
     try
     {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        File file = new File(uiPath + "/"+fileName);
        if(file.exists())
        {
            
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();           
            NodeList root = doc.getElementsByTagName("configurations");
            
            Element eElement = (Element) root.item(0);

            config.deviceType = eElement.getElementsByTagName("deviceType").item(0).getTextContent();
            config.ConfigureSystemService = eElement.getElementsByTagName("ConfigureSystemService").item(0).getTextContent();
            config.ConfigureNetWorkProtocol = eElement.getElementsByTagName("ConfigureNetWorkProtocol").item(0).getTextContent();
            config.ConfigurSimulatiedDevice = eElement.getElementsByTagName("ConfigurSimulatiedDevice").item(0).getTextContent();
            
            NodeList funcs = ((Element)eElement.getElementsByTagName("functionSets").item(0)).getElementsByTagName("functionSet");
            for(int i = 0; i< funcs.getLength(); i++)
            {
                Element elem = (Element) funcs.item(i);
                FunctionSet funcSet = new FunctionSet();
                funcSet.functionsetName = elem.getElementsByTagName("Name").item(0).getTextContent();
                funcSet.server = elem.getElementsByTagName("Server").item(0).getTextContent();
                funcSet.client = elem.getElementsByTagName("Client").item(0).getTextContent();
                config.FunctionSets.add(funcSet);
            }
        }        
     }
     catch (Exception ex) 
     {
         txtArea.append(ex.getMessage()+"\n");
        return null;
     }
     return config;
 } 
}