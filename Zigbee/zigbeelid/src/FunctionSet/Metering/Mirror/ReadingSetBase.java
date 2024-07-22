/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionSet.Metering.Mirror;

import com.Identification.IdentifiedObject;
import com.Types.DateTimeInterval;
import java.io.Serializable;

/**
 *
 * @author Khanh
 */
public class ReadingSetBase implements Serializable
{
    public ReadingSetBase ()
    {
        timePeriod = new DateTimeInterval();
        identifiedObject = new IdentifiedObject();
    }
    public DateTimeInterval timePeriod;
    public IdentifiedObject identifiedObject;
    
}
