/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSetAssignments;

import com.PrimaryTypes.String32;
import com.Types.VersionType;
import com.Types.mRIDType;
import com.Types.SubscribableType;
import com.PrimaryTypes.*;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CONG HUY
 */
@XmlRootElement(namespace="")
public class FunctionSetAssignments implements Serializable
{
    public FunctionSetAssignments (){}
    
    @XmlElement(name = "description")
    public String description;
    @XmlElement(name = "mRID")
    public String mRID;
    @XmlElement(name = "subscribable")
    public int subscribable;
    @XmlElement(name = "version")
    public int version;
    public String Response(String space)
    {       
        String response = space + "<description>"+description+"</description>\n";
                response +=space + "<mRID>"+mRID+"</mRID>\n";
                response +=space + "<subscribable>"+subscribable+"</subscribable>\n";
                response +=space + "<version>"+version+"</version>\n";        
         return response;
    }
    public String ResponseAll(String space, String href)
    {       
        String response = space + "<FunctionSetAssignments href =\""+href+"\">\n";
                response += this.Response(space+"    ");
                response +=space + "</FunctionSetAssignments>\n";                
         return response;
    }
}
