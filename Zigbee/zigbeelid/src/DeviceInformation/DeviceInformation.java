/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DeviceInformation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CONG HUY
 */
@XmlRootElement(namespace="")
public class DeviceInformation implements Serializable
{
    public DeviceInformation() 
    {
        dRLCCapabilities = new DRLCCapabilities();
        supportedLocaleList = new ArrayList<SupportedLocale>();
    }
    
    public String configureNetworkProtocal;
    public String configureSimulatedDevice;
    public String configureSystemService;
    
    @XmlElement(name = "functionsImplemented")
    public String  functionsImplemented;
    @XmlElement(name = "iFDI")
    public String iFDI;
    @XmlElement(name = "mfDate")
    public int mfDate;
    @XmlElement(name = "mfHwVer")
    public String mfHwVer;
    @XmlElement(name = "mfID")
    public int mfID;
    @XmlElement(name = "mfInfo")
    public String mfInfo;
    @XmlElement(name = "mfModel")
    public String mfModel;
    @XmlElement(name = "mfSerNum")
    public String mfSerNum;
    @XmlElement(name = "primaryPower")
    public int primaryPower;
    @XmlElement(name = "secondaryPower")
    public int secondaryPower;
    @XmlElement(name = "swActTime")
    public int swActTime;
    @XmlElement(name = "swVer")
    public String swVer;    
    @XmlElement(name = "DRLCCapabilities")
    public DRLCCapabilities dRLCCapabilities;
    @XmlElementWrapper(name = "SupportedLocaleList")
    @XmlElement(name = "SupportedLocale")
    public List<SupportedLocale> supportedLocaleList;
    public String Response(String _space, String href)
    {
        String space = _space +"    ";
         String  response = space + "<DeviceInformation href=\""+href+"\">\n";
                response += space + "<functionsImplemented>"+functionsImplemented+"</functionsImplemented>\n";
                response += space + "<iFDI>"+iFDI+"</iFDI>\n";
                response += space + "<mfDate>"+mfDate+"</mfDate>\n";
                response += space + "<mfHwVer>"+mfHwVer+"</mfHwVer>\n";
                response += space + "<mfID>"+mfID+"</mfID>\n";
                response += space + "<mfInfo>"+mfInfo+"</mfInfo>\n";
                response += space + "<mfModel>"+mfModel+"</mfModel>\n";
                response += space + "<mfSerNum>"+mfSerNum+"</mfSerNum>\n";
                response += space + "<primaryPower>"+primaryPower+"</primaryPower>\n";
                response += space + "<secondaryPower>"+secondaryPower+"</secondaryPower>\n";
                response += space + "<secondaryPower>"+secondaryPower+"</secondaryPower>\n";
                response += space + "<swActTime>"+swActTime+"</swActTime>\n";
                response += space + "<swVer>"+swVer+"</swVer>\n";
                response += space + "<DRLCCapabilities>\n";
                response += dRLCCapabilities.Response(space+"    ");
                response += space + "</DRLCCapabilities>\n";
                response += space + "<SupportedLocaleList href=\""+href+"/loc\" />\n";
                response += space + "</DeviceInformation>\n";
         return response;
    }
    public String GetSupportedLocaleListResponse(String space, String href)
    {
        String  response = space + "<DeviceInformation all =\""+supportedLocaleList.size()+"\" href=\""+href+"\">\n";
                for(SupportedLocale sup: supportedLocaleList)
                {
                   response += sup.Response(space+"    ", href+"/loc");                   
                }
                response += space + "</DeviceInformation>\n";
         return response;
    }
}
