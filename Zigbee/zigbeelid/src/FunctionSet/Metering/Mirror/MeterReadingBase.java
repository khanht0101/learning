/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Metering.Mirror;

import com.Identification.IdentifiedObject;
import java.io.Serializable;

/**
 *
 * @author Khanh
 */
public class MeterReadingBase implements Serializable
{
    public MeterReadingBase ()
    {
        identifiedObject = new IdentifiedObject();
    }
    public IdentifiedObject identifiedObject;
    
}
