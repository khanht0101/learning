/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DeviceInformation;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CONG HUY
 */
@XmlRootElement(namespace="")
public class SupportedLocale implements Serializable
{
    public SupportedLocale () {}
    @XmlElement(name = "averageEnergy")
    public String locale;
    
    public String Response(String _space, String href)
    {
        String space = _space;
        String response = _space + "<SupportedLocale href=\""+href+"\">\n";
              response += space + "<locale>"+locale+"</locale>\n";
        response += _space + "</SupportedLocale>\n";
         return response;
    }
}
