/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.xml.sax.InputSource;
import java.util.List;
import javax.xml.transform.stream.StreamSource;
/**
 *
 * @author Khanh
 */
public class XmlSerialize implements Serializable
{
    
    public static Object convertXMLToObject(Class<?> cls, String xml)
    {  
        try
        {
            InputSource input = new InputSource(new StringReader(xml));
            //InputStream is = new FileInputStream( System.getProperty("user.dir")+"/myclass.xml" );
            JAXBContext jaxbContext = JAXBContext.newInstance(cls);        
            Unmarshaller jaxbUnmarshaller  = jaxbContext.createUnmarshaller();          
            return jaxbUnmarshaller.unmarshal(input);
        } catch (Exception ex) 
        {           
        }
        return null;        
    }
    
    public static List<Object> convertXMLToObjectList(Class<?> cls, String xml)
    {  
        try
        {
            JAXBContext jc = JAXBContext.newInstance(Wrapper.class, cls);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            return (List<Object>) unmarshal(unmarshaller, cls, xml, false);
        } catch (Exception ex) 
        {           
        }
        return null;        
    }
    
    public static Object convertXMLFileToObject(Class<?> cls, String fileName)
    {  
        try
        {            
            InputStream input = new FileInputStream(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(cls);        
            Unmarshaller jaxbUnmarshaller  = jaxbContext.createUnmarshaller();          
            return jaxbUnmarshaller.unmarshal(input);
        } catch (Exception ex) 
        {           
        }
        return null;        
    }
    
    public static List<Object> convertXMLFileToObjectList(Class<?> cls, String fileName)
    {  
        try
        {
            JAXBContext jc = JAXBContext.newInstance(Wrapper.class, cls);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            return (List<Object>) unmarshal(unmarshaller, cls, fileName ,true);
        } catch (Exception ex) 
        {           
        }
        return null;        
    }
    
    private static <T> List<T> unmarshal(Unmarshaller unmarshaller, Class<T> clazz, String xmlLocation, boolean isFile)
    {            
        try
        {
            StreamSource xml = new StreamSource(new StringReader(xmlLocation));  
            if(isFile)
                xml = new StreamSource(xmlLocation);
            Wrapper<T> wrapper = (Wrapper<T>) unmarshaller.unmarshal(xml,
                    Wrapper.class).getValue();
            return wrapper.getItems();
        }
        catch (Exception ex)
        {}
        return null;
    }
    
}
