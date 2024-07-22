/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Metering;

import Common.LinkObject;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CONG HUY
 */
@XmlRootElement(namespace="", name = "UsagePoint")
public class UsagePoint implements Serializable
{
    public UsagePoint()
    {              
        usagePoinitBase = new UsagePointBase();
        meterReadingList = new MeterReadingList();
    };
    public UsagePointBase usagePoinitBase;
    public MeterReadingList meterReadingList;
    
    @XmlElement(name = "MeterReadingListLink")
    public LinkObject MeterReadingListLink;
    @XmlElement(name = "roleFlags")
    public int  roleFlags;
    @XmlElement(name = "serviceCategoryKind")
    public int  serviceCategoryKind;
    @XmlElement(name = "status")
    public int status;   
    @XmlAttribute(name ="href")
    public String Href;
    
    public String xmlResponse(String url)
    {
        String  response = "<UsagePoint href=\""+url+"\">\n";
                response += "	<roleFlags>"+usagePoinitBase.roleFlags+"</roleFlags>\n";
                response += "	<serviceCategoryKind>"+usagePoinitBase.serviceCategoryKind+"</serviceCategoryKind>\n";
                response += "	<status>"+usagePoinitBase.status+"</status>\n";
                response += "	<MeterReadingListLink all=\"6\" href=\""+url+"/mr\"/>\n";
                response += "</UsagePoint>\n"; 
                return response;
    }
}
