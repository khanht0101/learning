/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zbse20;

import com.Types.mRIDType;
import com.PrimaryTypes.*;
import com.user.User;
//import com.PrimaryTypes.*;
import java.io.*;
import java.lang.reflect.*;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author CONG HUY
 */
public class Library implements Serializable
{
    public String path = System.getProperty("user.dir");
    public Library() 
    {
        strList += ",HexBinary8,HexBinary16,HexBinary32,HexBinary48,HexBinary128,HexBinary64,HexBinary160,";
        strList += "String,String6,String16,String20,String32,String42,String192,";
        strList += "UInt8,UInt16,UInt32,UInt40,UInt48,UInt64,";
        strList += "Long,long,int,Int,Int8,Int16,Int32,Int48,Int64,";
        strList += ",Metering,DeviceType,FieldRepository,Class,";
    }
    public User user;
    public String strList;
    public void setProperties(String attribute, String value)
    {
        Object obj = user;
        String attValue = attribute;
        if(attribute.indexOf("/")>0)
        {
            int i=0;
            String[] attr = attribute.split("/");
            
           do {
                if(obj == null)
                    return ;
                obj = getObjectProperty(obj, attr[i]);
                i++;
            }
            while(i< attr.length-1);           
            attValue = attr[attr.length-1];
        }
        
        setValueProperty(obj,attValue,value);
    }
    public String getProperties(String attribute)
    {
        Object obj = user;        
        if(attribute.indexOf("/")>0)
        {
            int i=0;
            String[] attr = attribute.split("/");
            do
            {
                if(obj == null)
                    return "Not found this attribute.";
                obj = getValueProperty(obj, attr[i]);
                i++;
            } while(i< attr.length);           
        }    
        else
        {
            obj = getValueProperty(obj, attribute);
        }
        if(obj != null)
           return getPropertyValue(obj);
        return "Not found this attribute.";
    }
    public Object getObjectProperty(Object object, String attribute)
    {      
            Method[] methods = object.getClass().getMethods();
            for(Method method:methods)
            {
                if(method.getName().equals("get"+attribute+"Object"))
                {
                    
                try {
                    return method.invoke(object,null);                    
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
                }                    
                }
                else
                {                    
                    Object rs =  getObjectProperty(object,attribute);
                    if(rs!=null)
                        return rs;
                }
            }
            return null;
    }
    public Object getValueProperty(Object object, String attribute)
    {
        Field [] attributes =  object.getClass().getDeclaredFields();
            for (Field field : attributes) 
            {
                if(field.getName().equals(attribute))
                {
                try {                    
                 return field.get(object);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
                }
                return "";
                }                
            }
          
            Method[] methods = object.getClass().getMethods();
            for(Method method:methods)
            {
                if(method.getName().indexOf("Object")>0)
                {
                    Object obj;
                try {
                    obj = method.invoke(object,null);
                    Object rs =  getValueProperty(obj,attribute);
                    if(rs!=null)
                        return rs;
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                }
            }
            return null;
    }
    public boolean setValueProperty(Object object, String attribute, String value)
    {
        Field [] attributes =  object.getClass().getDeclaredFields();

            for (Field field : attributes) 
            {
                String type = field.getType().toString().substring(field.getType().toString().lastIndexOf(".")+1);
                if(field.getName().equals(attribute))
                {
                    try {                    
                        field.set(object,getObjectValue(type,value));
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return true;
                }                
            }
          
            Method[] methods = object.getClass().getMethods();
            for(Method method:methods)
            {
                if(method.getName().indexOf("Object")>0)
                {
                    Object obj;
                try {
                    obj = method.invoke(object,null);
                   if(setValueProperty(obj,attribute,value))
                       return true;
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                }
            }
            
            return false;
    }
    
    public Object getObjectValue(String type, String value)
    {
        Object obj = null;
        switch (type)
        {
            case "Int8":
                obj = new Int8(Integer.parseInt(value));
                break;
            case "Int16":
                obj = new Int16(Integer.parseInt(value));
                break;
            case "Int32":
                obj = new Int32(Integer.parseInt(value));
                break;
            case "Int48":
                obj = new Int48(Integer.parseInt(value));
                break;
            case "Int64":
                obj = new Int64(Integer.parseInt(value));
                break;
            case "UInt8":
                obj = new UInt8(Integer.parseInt(value));
                break;
            case "UInt16":
                obj = new UInt16(Integer.parseInt(value));
                break;
            case "UInt32":
                obj = new UInt32(Integer.parseInt(value));
                break;
            case "UInt40":
                obj = new UInt40(Integer.parseInt(value));
                break;
            case "UInt48":
                obj = new UInt48(Integer.parseInt(value));
                break;
            case "UInt64":
                obj = new UInt64(Integer.parseInt(value));
                break;
            case "String6":
                obj = new String6(value);
                break;
            case "String16":
                obj = new String16(value);
                break;    
            case "String20":
                obj = new String20(value);
                break;
            case "String32":
                obj = new String32(value);
                break;        
             case "String42":
                obj = new String42(value);
                break;
            case "String192":
                obj = new String192(value);
                break; 
            case "HexBinary8":
                obj = new HexBinary8(Long.parseLong(value));
                break; 
            case "HexBinary16":
                obj = new HexBinary16(Long.parseLong(value));
                break;
            case "HexBinary32":
                obj = new HexBinary32(Long.parseLong(value));
                break;
            case "HexBinary48":
                obj = new HexBinary48(Long.parseLong(value));
                break;
            case "HexBinary64":
                obj = new HexBinary64(Long.parseLong(value));
                break;
            case "HexBinary128":
                obj = new HexBinary128(Long.parseLong(value));
                break;
            case "HexBinary160":
                obj = new HexBinary160(Long.parseLong(value));
                break;
            case "mRIDType":
                obj = new mRIDType(new HexBinary128(Long.parseLong(value)));
                break;
            default:
                obj = value;
                break;
                   
        }
        return obj;
        
    }
    
    public String getPropertyValue(Object obj)
    {
        String res = "";
       
        String type = obj.getClass().getName();
         type = type.substring(type.lastIndexOf(".")+1);
        
        switch (type)
        {
            case "Int8":
                res = ((Int8)obj).getValue() +"";  
                break;
            case "Int16":
                res = ((Int16)obj).getValue() +""; 
                break;
            case "Int32":
                res = ((Int32)obj).getValue() +"";
                break;
            case "Int48":
                res = ((Int48)obj).getValue() +"";
                break;
            case "Int64":
                res = ((Int64)obj).getValue() +"";
                break;
            case "UInt8":
                res = ((UInt8)obj).getValue() +""; 
                break;
            case "UInt16":
                res = ((UInt16)obj).getValue() +"";
                break;
            case "UInt32":
                res = ((UInt32)obj).getValue() +"";
                break;
            case "UInt40":
                res = ((UInt40)obj).getValue() +"";
                break;
            case "UInt48":
                res = ((UInt48)obj).getValue() +"";
                break;
            case "UInt64":
                res = ((UInt64)obj).getValue() +"";
                break;
            case "String6":
                res = ((String6)obj).getValue();
                break;
            case "String16":
                res = ((String16)obj).getValue();
                break;    
            case "String20":
                res = ((String20)obj).getValue();
                break;
            case "String32":
                res = ((String32)obj).getValue();
                break;        
             case "String42":
                res = ((String42)obj).getValue() ;
                break;
            case "String192":
                res = ((String192)obj).getValue() +""; 
                break; 
            case "HexBinary8":
                res = ((HexBinary8)obj).getValue() +"";
                break; 
            case "HexBinary16":
                res = ((HexBinary16)obj).getValue() +"";
                break;
            case "HexBinary32":
                res = ((HexBinary32)obj).getValue() +"";
                break;
            case "HexBinary48":
                res = ((HexBinary48)obj).getValue() +""; 
                break;
            case "HexBinary64":
                res = ((HexBinary64)obj).getValue() +"";
                break;
            case "HexBinary128":
                res = ((HexBinary128)obj).getValue() +""; 
                break;
            case "HexBinary160":
                res = ((HexBinary160)obj).getValue() +"";
                break;
            case "mRIDType": 
                res = ((mRIDType) obj).getValue().getValue() + "";
                break;
            default:
                res = obj.toString();
                break;
                   
        }
        return res;
        
    }
    
  private void SetClientXMLFile(StringBuilder sb, String ip, String port) 
  {
    sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
               + "<!DOCTYPE beans PUBLIC  \"-//SPRING//DTD BEAN//EN\" \"http://www.springframework.org/dtd/spring-beans.dtd\">\r\n"
               + "<beans>\r\n"  
               + "<bean id=\"RMIUserService\" class=\"org.springframework.remoting.rmi.RmiProxyFactoryBean\">\r\n"
               + "       <property name=\"serviceUrl\" value=\"rmi://"+ip+":"+port+"/RMIUserService\"/>\r\n"
               + "        <property name=\"serviceInterface\" value=\"com.server.IRMIUserService\"/>\r\n"
               + "       <property name=\"refreshStubOnConnectFailure\" value=\"true\"/>\r\n"
               + "        </bean>\r\n"
               + "</beans>");
}

public void UpdateClientConfigXmlFile(String ip, String port) {

    StringBuilder sb = new StringBuilder();
    SetClientXMLFile(sb,ip, port);
    String prolog = sb.toString();

    FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path + "/rmiClientAppContext.xml");
        } catch (FileNotFoundException ex) {
           System.out.println(ex.getMessage());
        }
    OutputStreamWriter osw = new OutputStreamWriter(fos, Charset.forName("UTF-8"));
        try {
            osw.write(prolog);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            osw.flush();
        } catch (IOException ex) {
           System.out.println(ex.getMessage());
        }
        try {
            osw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

}

private void SetServerXMLFile(StringBuilder sb, int port) 
  {
    sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE beans PUBLIC  \"-//SPRING//DTD BEAN//EN\" \"http://www.springframework.org/dtd/spring-beans.dtd\">\n" +
                "<beans>\n" +
                "	<bean id=\"UserMap\" class=\"java.util.concurrent.ConcurrentHashMap\" />\n" +
                "	<bean id=\"CacheService\" class=\"com.cache.service.CacheService\">\n" +
                "		<property name=\"userMap\" ref=\"UserMap\"/>\n" +
                "	</bean>\n" +
                "	<bean id=\"RMIUserService\" class=\"com.server.RMIUserService\" >\n" +
                " 		<property name=\"cacheService\" ref=\"CacheService\"/>\n" +
                "	</bean>\n" +
                "    <bean class=\"org.springframework.remoting.rmi.RmiServiceExporter\">\n" +
                "        <property name=\"serviceName\" value=\"RMIUserService\"/>\n" +
                "        <property name=\"service\" ref=\"RMIUserService\"/>\n" +
                "        <property name=\"serviceInterface\" value=\"com.server.IRMIUserService\"/>\n" +
                "    	<property name=\"registryPort\" value=\""+port+"\"/>    \n" +
                "    </bean>	\n" +
                "</beans>");
}
 
public void UpdateServerConfigXmlFile(int port) {

    StringBuilder sb = new StringBuilder();
    SetServerXMLFile(sb,port);
    String prolog = sb.toString();

    FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path + "/rmiServerAppContext.xml");
        } catch (FileNotFoundException ex) {
           System.out.println(ex.getMessage());
        }
    OutputStreamWriter osw = new OutputStreamWriter(fos, Charset.forName("UTF-8"));
        try {
            osw.write(prolog);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            osw.flush();
        } catch (IOException ex) {
           System.out.println(ex.getMessage());
        }
        try {
            osw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

}

private void SetConfigureProfileXMLFile(StringBuilder sb, ConfigureFile config) 
  {
    sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
    sb.append("<configurations>\r\n");
    sb.append("    <deviceType>"+config.deviceType+"</deviceType>\r\n");
    sb.append("    <functionSets>\r\n");
    for(FunctionSet func: config.FunctionSets)
    {
        sb.append("        <functionSet>\r\n");
        sb.append("            <Name>"+func.functionsetName+"</Name>\r\n");
        sb.append("            <Server>"+func.server+"</Server>\r\n");
        sb.append("            <Client>"+func.client+"</Client>\r\n");
        sb.append("        </functionSet>\r\n");
    }
    sb.append("    </functionSets>\r\n");
    sb.append("    <ConfigureSystemService>"+config.ConfigureSystemService+"</ConfigureSystemService>\r\n");
    sb.append("    <ConfigureNetWorkProtocol>"+config.ConfigureNetWorkProtocol+"</ConfigureNetWorkProtocol>\r\n");
    sb.append("    <ConfigurSimulatiedDevice>"+config.ConfigurSimulatiedDevice+"</ConfigurSimulatiedDevice>\r\n");
    sb.append("</configurations>");
}
public void UpdateConfigProfileXmlFile(ConfigureFile config) {

    StringBuilder sb = new StringBuilder();
    SetConfigureProfileXMLFile(sb,config);
    String prolog = sb.toString();

    FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path + "/configProfile.xml");
        } catch (FileNotFoundException ex) {
           System.out.println(ex.getMessage());
        }
    OutputStreamWriter osw = new OutputStreamWriter(fos, Charset.forName("UTF-8"));
        try {
            osw.write(prolog);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            osw.flush();
        } catch (IOException ex) {
           System.out.println(ex.getMessage());
        }
        try {
            osw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

}
}


