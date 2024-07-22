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
public class DeviceStatus implements Serializable
{
    
    public DeviceStatus() {}
    @XmlElement(name = "changedTime")
    public int  changedTime;
    @XmlElement(name = "onCount")
    public int onCount;
    @XmlElement(name = "opState")
    public int opState;
    @XmlElement(name = "opTime")
    public int opTime;
    @XmlElement(name = "Temperature")
    public Temperature temperature;
    @XmlElement(name = "Time")
    public Time.Time time;
    public String Response(String space)
    {       
        String response = space + "<changedTime>"+changedTime+"</changedTime>\n";
                response +=space + "<onCount>"+onCount+"</onCount>\n";
                response +=space + "<opState>"+opState+"</opState>\n";
                response +=space + "<opTime>"+opTime+"</opTime>\n";
                response +=space + "<temperature>"+temperature+"</temperature>\n";
                response +=space + "<time>"+time+"</time>\n";               
         return response;
    }
    public String ResponseAll(String space)
    {       
        String response = space + "<DeviceStatus>\n";
                response += this.Response(space+"    ");
                response +=space + "</DeviceStatus>\n";                
         return response;
    }
}
