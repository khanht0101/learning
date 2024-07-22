/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DeviceStatus;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CONG HUY
 */
@XmlRootElement(namespace="")
public class Registration implements Serializable
{
    public Registration () {}
    @XmlElement(name = "pIN")
    public int pIN;
    @XmlElement(name = "dateTimeRegistered")
    public int dateTimeRegistered;
    public String Response(String space)
    {       
        String response = space + "<pIN>"+pIN+"</pIN>\n";
                response +=space + "<pIN>"+pIN+"</pIN>\n";            
        return response;
    }
    public String ResponseAll(String space)
    {       
        String response = space + "<Registration>\n";
                response += this.Response(space+"    ");
                response +=space + "</Registration>\n";                
         return response;
    }
}
