/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Metering;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CONG HUY
 */
@XmlRootElement(namespace="")
public class UsagePointBase implements Serializable
{
    public UsagePointBase()
    {
        //Random random = new Random();
        //roleFlags = new RoleFlagsType(random.nextInt());
        //serviceCategoryKind = new ServiceKind(new UInt8(random.nextInt(8)));
        //status = new UInt8(1);
        roleFlags = 3;
        serviceCategoryKind = 2;
        status = 1;
    }
//    public RoleFlagsType  roleFlags;
//    public ServiceKind  serviceCategoryKind;
//    public UInt8 status;   
    
    @XmlElement(name = "roleFlags")
    public int  roleFlags;
    @XmlElement(name = "serviceCategoryKind")
    public int  serviceCategoryKind;
    @XmlElement(name = "status")
    public int status;  
    public String xmlResponse(String space)
    {
        String  response = space + "<roleFlags>"+roleFlags+"</roleFlags>\n";
                response += space + "<serviceCategoryKind>"+serviceCategoryKind+"</serviceCategoryKind>\n";
                response += space + "<status>"+status+"</status>\n";
                return response;
    }
}
