/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuration;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CONG HUY
 */
@XmlRootElement(namespace="")
public class TimeConfiguration implements Serializable
{
    public TimeConfiguration() {}
    @XmlElement(name = "dstEndRule")
    public String dstEndRule;
    @XmlElement(name = "dstOffset")
    public int dstOffset;
    @XmlElement(name = "dstStartRult")
    public String dstStartRult;
    @XmlElement(name = "tzOffset")
    public int tzOffset;
    
    public String Response(String space)
    {
         String  response = space + "<dstEndRule>"+dstEndRule+"</dstEndRule>\n";
                response += space + "<dstOffset>"+dstOffset+"</dstOffset>\n";
                response += space + "<dstStartRult>"+dstStartRult+"</dstStartRult>\n";
                response += space + "<tzOffset>"+tzOffset+"</tzOffset>\n";
                return response;
    }
}
