/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuration;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CONG HUY
 */
@XmlRootElement(namespace="")
public class Configuration implements Serializable
{
    public Configuration() 
    {
        powerConfiguration = new PowerConfiguration();
        timeConfiguration = new TimeConfiguration();
        priceResponseCfg = new ArrayList<PriceResponseCfg>();
    }
    @XmlElement(name = "currentLocale")
    public String currentLocale;
    @XmlElement(name = "userDeviceName")
    public String userDeviceName;
    
    @XmlElementWrapper(name = "PriceResponseCfgList")
    @XmlElement(name = "PriceResponseCfg")
    public List<PriceResponseCfg> priceResponseCfg; 
    @XmlElement(name = "PowerConfiguration")
    public PowerConfiguration  powerConfiguration ;
    @XmlElement(name = "TimeConfiguration")
    public TimeConfiguration timeConfiguration;
    
    public String Response(String _space, String href)
    {
        String space = _space +"    ";
         String  response = _space + "<Configuration href=\""+href+"\">\n";
                response += space + "<currentLocale>"+currentLocale+"</currentLocale>\n";
                response += space + "<userDeviceName>"+userDeviceName+"</userDeviceName>\n";
                response += space + "<PowerConfiguration>\n";
                response += powerConfiguration.Response(space+"    ") ;
                response += space + "</PowerConfiguration>\n";
                response += space + "<TimeConfiguration>\n";
                response += timeConfiguration.Response(space+"    ") ;
                response += space + "</TimeConfiguration>\n";
                response += space + "<priceResponseCfg href=\""+href.replace("cfg", "prcfg")+"\"/>\n";
                response += _space + "</Configuration>\n";
         return response;
    }
}
