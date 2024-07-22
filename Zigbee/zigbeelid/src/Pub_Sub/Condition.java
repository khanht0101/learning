/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pub_Sub;

import com.PrimaryTypes.Int48;
import com.PrimaryTypes.UInt8;
import com.PrimaryTypes.*;
import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class Condition implements Serializable
{
    public Condition() {}
    public UInt8 attributeIdentifier;
    public Int48 lowerThreshold;
    public Int48 upperThreshold;
    
}
