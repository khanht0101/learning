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
public class Temperature implements Serializable
{
    public Temperature  () {}
    @XmlElement(name = "multiplier")
    public int multiplier;
    @XmlElement(name = "subject")
    public int subject;
    @XmlElement(name = "value")
    public int value;
    public String Response(String space)
    {       
        String response = space + "<multiplier>"+multiplier+"</multiplier>\n";
                response +=space + "<subject>"+subject+"</subject>\n";
                response +=space + "<value>"+value+"</value>\n";            
        return response;
    }
    public String ResponseAll(String space)
    {       
        String response = space + "<Temperature>\n";
                response += this.Response(space+"    ");
                response +=space + "</Temperature>\n";                
         return response;
    }
}
