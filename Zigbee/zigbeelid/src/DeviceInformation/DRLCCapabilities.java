
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DeviceInformation;

import com.Types.RealEnergy;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CONG HUY
 */
@XmlRootElement(namespace="")
public class DRLCCapabilities implements Serializable
{
    public DRLCCapabilities () 
    {
        averageEnergy = new RealEnergy();
    }
    @XmlElement(name = "AverageEnergy")
    public RealEnergy averageEnergy;
    @XmlElement(name = "maxDemand")
    public int maxDemand;
    @XmlElement(name = "optionsImplemented")
    public String optionsImplemented;
    public String Response(String space)
    {
         String  response = space + "<maxDemand>"+maxDemand+"</maxDemand>\n";
                response += space + "<optionsImplemented>"+optionsImplemented+"</optionsImplemented>\n";
                response += space + "<AverageEnergy>\n";
                response += averageEnergy.Response(space+"    ");
                response += space + "</AverageEnergy>\n";
         return response;
    }
}
