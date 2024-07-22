/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Metering;

import Common.LinkObject;
import FunctionSet.Metering.Mirror.MeterReadingBase;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CONG HUY
 */
 @XmlRootElement(namespace = "")
public class MeterReading implements Serializable
{   
    public MeterReading()
    {
        meterReadingBase = new MeterReadingBase();
        readingSetList = new ReadingSetList();
        readingType =  new ReadingType();
    }
    
    @XmlElement(name ="readingSetList")
    public ReadingSetList readingSetList;
    @XmlElement(name ="readingType")
    public ReadingType readingType;
    @XmlElement(name ="meterReadingBase")
    public MeterReadingBase meterReadingBase;    
    @XmlElement(name ="ReadingSetListLink")
    public LinkObject ReadingSetListLink;
    @XmlElement(name ="ReadingTypeLink")
    public LinkObject ReadingTypeLink;
    @XmlElement(name ="description")
    public String description;    
    @XmlElement(name ="mRID")
    public String mRID;
    @XmlElement(name ="version")
    public String version;
    
    public String Response(String pace, String href)
    {
        String response ="";
        response = pace +  "<MeterReading href =\""+href+"/rt\" >\n";     
        response += pace + "    <mRID>"+meterReadingBase.identifiedObject.mRID+"</mRID>\n";      
        response += pace + "    <description>"+meterReadingBase.identifiedObject.description+"</description>\n";        
        response += pace + "    <ReadingSetListLink all =\""+readingSetList.Length()+"\" href =\""+href+"/rs\" >\n";  
        response += pace + "    <ReadingTypeLink href =\""+href+"/rt\" />\n";  
        response += pace + "</MeterReading>\n"; 
        return response;
    }
}
