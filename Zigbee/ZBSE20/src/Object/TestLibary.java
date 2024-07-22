/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.io.*;

/**
 *
 * @author Khanh
 */
public class TestLibary 
{
    public TestLibary()
    {
        String filename = System.getProperty("user.dir")+"/usagePoint.xml" ;
    }
   public String ReadxmlFile(String fileName)
    {
        String str="";

        //reading   
        try{
            InputStream ips=new FileInputStream(new File(fileName)); 
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);
            String line;
            while ((line=br.readLine())!=null){
                System.out.println(line);
                str +=line+"\n";
            }
            br.close(); 
        }       
        catch (Exception e){
           return "";
        }
        return  str;
    }
}
