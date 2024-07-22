/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Types;

import com.PrimaryTypes.UInt48;
import com.PrimaryTypes.*;
import java.io.Serializable;
import java.util.Random;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CONG HUY
 */
@XmlRootElement(namespace="")
public class RealEnergy implements Serializable
{
    public RealEnergy() 
    {
    }
    @XmlElement(name = "multiplier")
    public int multiplier;
    @XmlElement(name = "value")
    public int value;
    public String Response(String space)
    {
         String  response = space + "<multiplier>"+multiplier+"</multiplier>\n";
                response += space + "<value>"+value+"</value>\n";
         return response;
    }
    
}
