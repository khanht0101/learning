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
public class PriceResponseCfg implements Serializable
{
    public PriceResponseCfg () {}
    @XmlElement(name = "consumeThreshold")
    public int consumeThreshold;
    @XmlElement(name = "maxReductionThreshold")
    public int maxReductionThreshold;
    
    public String Response(String space)
    {
         String  response = space + "<consumeThreshold>"+consumeThreshold+"</consumeThreshold>\n";
                response += space + "<maxReductionThreshold>"+maxReductionThreshold+"</maxReductionThreshold>\n";
         return response;
    }
    
}
