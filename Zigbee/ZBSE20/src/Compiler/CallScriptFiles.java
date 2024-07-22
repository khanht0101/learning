/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Compiler;
import bsh.Interpreter;
import java.io.BufferedReader;
import java.io.FileReader;
import zbse20.NTSMain;

/**
 *
 * @author Khanh
 */

public class CallScriptFiles 
{
    private String path = System.getProperty("user.dir");   
    private NTSMain Frm;
    public CallScriptFiles(NTSMain frm)
    {
        //CompileScripts compiler = new CompileScripts();
        //compiler.RunScripts();
        try
        {
            Frm = frm;
            LoadLibray(Frm.inter);   
           
        }
        catch (Exception ex)
        {
            Frm.PrintCommandConsole(ex.getMessage(), true);
        }
    }
     
    public void ExecutiveCommandLine(Interpreter inter, String cmd)
    {
        try
        {
        inter.eval(cmd);
        }catch(Exception ex)
        {
            Frm.PrintCommandConsole(ex.getMessage().replace("Sourced file: inline evaluation of:", ""), true);
        }
    }
    public void LoadLibray(Interpreter inter)
    {
        String  initailLoad = " import java.util.*;  \n"+                            
                                "   public void Print(Object obj, boolean newLine)\n" +
                                "   {\n" +
                                "      NTSSE.Print(obj.toString(), newLine);\n" +
                                "   }\n" +
                                "   public void NTSPrint(Object obj, boolean newLine)\n" +
                                "   {\n" +
                                "      NTSSE.NTSPrint(obj.toString(), newLine);\n" +
                                "   }\n" +
                                "   public void LOG(boolean status, String msg)\n" +
                                "   {\n" +
                                "       NTSSE.SetLog(status, msg);\n" +
                                "   }\n" +
                                "   public void NTSSendCommand(String cmd)\n" +
                                "   {\n" +
                                "       NTSSE.NTSSendCommand(cmd);\n" +
                                "   }\n"+
                                "   public void NTSSendCommand(String cmd, String context)\n" +
                                "   {\n" +
                                "       NTSSE.NTSSendCommand(cmd, context);\n" +
                                "   }\n"+
                                "   public void SendCommand(String cmd, String context)\n" +
                                "   {\n" +
                                "       NTSSE.SendCommand(cmd, context);\n" +
                                "   }\n"+
                                "   public void SendCommand(String cmd)\n" +
                                "   {\n" +
                                "       NTSSE.SendCommand(cmd);\n" +
                                "   }\n" +
                                "   public Object convertXMLToObject(Object obj, String xml)\n" +
                                "    {\n" +
                                "       return  NTSSE.convertXMLToObject(obj, xml);\n" +
                                "    }\n" +
                                "    \n" +
                                "    public List convertXMLToObjectList(Object obj, String xml)\n" +
                                "    {\n" +
                                "        return  NTSSE.convertXMLToObjectList(obj, xml);\n" +
                                "    }\n" +
                                "    \n" +
                                "    public Object convertXMLFileToObject(Object obj, String fileName)\n" +
                                "    {\n" +
                                "        return NTSSE.convertXMLFileToObject(obj, fileName);\n" +
                                "    }\n" +
                                "    public List convertXMLFileToObjectList(Object obj, String fileName)\n" +
                                "    {\n" +
                                "        return NTSSE.convertXMLFileToObjectList(obj, fileName);\n" +
                                "    }";        
        BufferedReader br = null;        
        try 
        {            
            inter.eval(initailLoad);
            String sCurrentLine;
            StringBuilder libStr = new StringBuilder();
            br = new BufferedReader(new FileReader( path +"\\lib\\lib.scr"));
            while ((sCurrentLine = br.readLine()) != null) 
            {                    
                libStr.append(sCurrentLine +"\n");
            }
            inter.eval(libStr.toString());
        } catch (Exception ex) 
        {
               Frm.PrintCommandConsole(ex.getMessage().replace("Sourced file: inline evaluation of:", ""), true);
        } finally 
        {
            try 
            {
                if (br != null)br.close();
            } catch (Exception e) 
            {
                Frm.PrintCommandConsole(e.getMessage().replace("Sourced file: inline evaluation of:", ""), true);
            }
        }
    }
    
    public void ExcutiveTestCase(Interpreter inter, String filename)
    {
        BufferedReader br = null;        
        try 
        {            
            String sCurrentLine;
            StringBuilder libStr = new StringBuilder();
            libStr.append("public void excutiveTestCasse(){ ");
            br = new BufferedReader(new FileReader( path +"\\scripts\\"+filename));
            while ((sCurrentLine = br.readLine()) != null) 
            {                    
                libStr.append(sCurrentLine+"\n");
            }
            libStr.append("}");
            inter.eval(libStr.toString());
            inter.eval("excutiveTestCasse();");
        } catch (Exception ex) 
        {
               Frm.PrintCommandConsole(ex.getMessage(), true);
        } finally 
        {
            try 
            {
                if (br != null)br.close();
            } catch (Exception e) 
            {
                Frm.PrintCommandConsole(e.getMessage(), true);
            }
        }
    }
}
