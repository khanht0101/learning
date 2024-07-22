/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Time;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CONG HUY
 */
@XmlRootElement(namespace="")
public class Time implements Serializable
{
    public Time() {}
    
    @XmlElement(name = "currentTime")
    public int currentTime;
    @XmlElement(name = "dstEndTime")
    public int dstEndTime;
    @XmlElement(name = "dstOffset")
    public int dstOffset;
    @XmlElement(name = "dstStartTime")
    public int dstStartTime;
    @XmlElement(name = "localTime")
    public int localTime;
    @XmlElement(name = "quality")
    public int quality;
    @XmlElement(name = "tzOffset")
    public int tzOffset;
    
    public String Response(String space)
    {       
        String response = space + "<currentTime>"+currentTime+"</currentTime>\n";
                response +=space + "<dstEndTime>"+dstEndTime+"</dstEndTime>\n";
                response +=space + "<dstOffset>"+dstOffset+"</dstOffset>\n";
                response +=space + "<dstStartTime>"+dstStartTime+"</dstStartTime>\n";
                response +=space + "<localTime>"+localTime+"</localTime>\n";
                response +=space + "<quality>"+quality+"</quality>\n";
                response +=space + "<tzOffset>"+tzOffset+"</tzOffset>\n";                
         return response;
    }
    public String ResponseAll(String space)
    {       
        String response = space + "<Time>\n";
                response += this.Response(space+"    ");
                response +=space + "</Time>\n";                
         return response;
    }
}
