/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Identification;

import com.Types.VersionType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CONG HUY
 */
@XmlRootElement(namespace = "")
public class IdentifiedObject implements Serializable
{
    public IdentifiedObject() 
    {
    }
    @XmlElement(name ="description")
    public String description;
    
    @XmlElement(name ="mRID")
    public String mRID;
    @XmlElement(name ="version")
    public String version;
    
    public String Response(String pace)
    {
        String response = pace + "<mRID>"+mRID+"</mRID>\n";      
                response += pace + "<description>"+description+"</description>\n";
                response += pace + "<version>"+version+"</version>\n";
        return response;
    }
    
}
