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
public class PowerConfiguration implements Serializable
{
    public PowerConfiguration() {}
    @XmlElement(name = "batteryInstallTime")
    public int batteryInstallTime;
    @XmlElement(name = "lowChargeThreshold")
    public int lowChargeThreshold;
    public String Response(String space)
    {
         String  response = space + "<batteryInstallTime>"+batteryInstallTime+"</batteryInstallTime>\n";
                response += space + "<lowChargeThreshold>"+lowChargeThreshold+"</lowChargeThreshold>\n";
                return response;
    }
    
}
